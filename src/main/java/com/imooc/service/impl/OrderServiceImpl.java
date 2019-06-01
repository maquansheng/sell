package com.imooc.service.impl;

import com.imooc.Dto.CartDto;
import com.imooc.Dto.OrderDto;
import com.imooc.converter.OrderMaster2OrderDto;
import com.imooc.dao.OrderDetailReponsitory;
import com.imooc.dao.OrderMasterRepository;
import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.OrderMaster;
import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.ProductService;
import com.imooc.service.PushMessage;
import com.imooc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailReponsitory orderDetailReponsitory;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PushMessage pushMessage;

    //创建订单
    @Override
    @Transactional
    public OrderDto creat(OrderDto orderDto) {
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtil.getKeyUtil();
        List<CartDto> cartDtoList = new ArrayList<>();
        //1.查询商品（数量，价格）
        List<OrderDetail> orderDetailList = orderDto.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            //3.订单商品写入数据库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.getKeyUtil());
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailReponsitory.save(orderDetail);

            //4.扣库存,第一种方法
            CartDto cartDto = new CartDto(orderDetail.getProductId(), orderDetail.getProductQuantity());
            cartDtoList.add(cartDto);
        }
        //写入买家订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderMasterRepository.save(orderMaster);

        //4.扣库存
        productService.decrease(cartDtoList);
        return orderDto;
    }
    //查询订单
    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDERMASTER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailReponsitory.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> ordermasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDto> orderDtoList = OrderMaster2OrderDto.convert(ordermasterPage.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<OrderDto>(orderDtoList, pageable, ordermasterPage.getTotalElements());
        return orderDtoPage;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();

        //1.查看订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.info("【取消订单】 订单状态不正确");
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.取消订单
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updataResult = orderMasterRepository.save(orderMaster);
        if (updataResult == null) {
            log.info("【取消订单】取消订单失败");
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        //3.返回库存
        List<OrderDetail> orderDetailList = orderDto.getOrderDetailList();
        if (CollectionUtils.isEmpty(orderDetailList)) {
            log.info("【取消订单】 订单中无商品");
        }
        List<CartDto> cartDtoList = orderDetailList.stream()
                .map(e -> new CartDto(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increase(cartDtoList);

        //4.退款
        if (orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【已完结订单】");
            throw new SellException(ResultEnum.ORDERMASTER_NOT_EXIST);
        }
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null) {
            log.info("【订单完结失败】");
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //微信推送消息
        pushMessage.orderStatus(orderDto);
        return orderDto;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.info("【订单已完结】");
            throw new SellException(ResultEnum.ORDERMASTER_NOT_EXIST);
        }
        //判断支付状态
        if (orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS)) {
            log.info("【已支付完成】");
        }
        //修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMasterRepository.save(orderMaster);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDto.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDto>(orderDtoList,pageable,orderMasterPage.getTotalElements());
    }
}
