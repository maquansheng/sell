package com.imooc.dataobject.mapper;

import com.imooc.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper mapper;
    @Test
    public void insert() {
        Map<String ,Object> map=new HashMap<>();
        map.put("category_name","老人的");
        map.put("category_type",5);
        mapper.insert(map);
    }

    @Test
    public void insertByObject() {
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("小孩的");
        productCategory.setCategoryType(6);
        mapper.insertByObject(productCategory);
    }

    @Test
    public void findByCategoryType() {
        mapper.findByCategoryType(5);
    }

    @Test
    public void findByCategoryName() {
        mapper.findByCategoryName("小孩的");
    }

    @Test
    public void update() {
        mapper.update("打野",5);
    }

    @Test
    public void findByType() {
        mapper.findByType(5);
    }
}