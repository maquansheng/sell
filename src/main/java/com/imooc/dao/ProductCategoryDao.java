package com.imooc.dao;

import com.imooc.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
类目
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {
    //通过类目编号查
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
