package com.imooc.service.impl;

import com.imooc.Dto.CartDto;
import com.imooc.dao.ProductDao;
import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productDao.findOne(productId);
    }

    //枚举解决在架商品
    @Override
    public List<ProductInfo> findUpAll() {
        return productDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productDao.save(productInfo);
    }

    @Override
    public void increase(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = productDao.findOne(cartDto.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(result);
            productDao.save(productInfo);
        }
    }

    /**
     * 扣库存
     */
    @Override
    public void decrease(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = productDao.findOne(cartDto.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productDao.save(productInfo);
        }
    }

    /*
    上架
     */
    @Override
    public ProductInfo on_sell(String productId) {
        ProductInfo productInfo = productDao.findOne(productId);
        if (productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatus()==ProductStatusEnum.UP.getCode()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productDao.save(productInfo);
        return productInfo;
    }

    /*
    下架
     */
    @Override
    public ProductInfo off_sell(String productId) {
        ProductInfo productInfo = productDao.findOne(productId);
        if (productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatus()==ProductStatusEnum.DOWN.getCode()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productDao.save(productInfo);
        return productInfo;
    }
}
