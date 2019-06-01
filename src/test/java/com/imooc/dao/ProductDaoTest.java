package com.imooc.dao;

import com.imooc.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest {
    @Autowired
    private ProductDao productDao;

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("米饭");
        productInfo.setCategoryType(1);
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductDescription("aa");
        productInfo.setProductIcon("aa");
        productInfo.setProductStatus(0);
        productInfo.setCreateTime(new Date());
        productInfo.setUpdateTime(new Date());
        productInfo.setProductStock(2);
        ProductInfo result = productDao.save(productInfo);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> result = productDao.findByProductStatus(0);
        Assert.assertNotEquals(0, result.size());

    }

}