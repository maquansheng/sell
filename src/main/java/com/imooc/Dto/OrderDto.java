package com.imooc.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.dataobject.OrderDetail;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.utils.EnumUtil;
import lombok.Data;
import org.apache.commons.lang3.EnumUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author:ma
 * @Data:17:30 2018/11/15
 * 数据传输对象
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 买家名字
     */
    private String buyerName;
    /**
     * 买家电话
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家微信id
     */
    private String buyerOpenid;
    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态，默认0新下单
     */
    private Integer orderStatus;
    /**
     * 支付状态
     */
    private Integer payStatus;

    private Date createTime;
    private Date updateTime;

    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
