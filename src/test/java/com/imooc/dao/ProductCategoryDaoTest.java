package com.imooc.dao;

import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao categoryDao;

    @Test
    public void testFindOne() {
        ProductCategory productCategory = categoryDao.findOne(1);
    }

    @Test
    @Transactional //事物回滚
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory(1, "女生最爱", 1);
        ProductCategory result = categoryDao.save(productCategory);
        System.out.print(result.getCategoryName());
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(3);
        List<ProductCategory> result = categoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}