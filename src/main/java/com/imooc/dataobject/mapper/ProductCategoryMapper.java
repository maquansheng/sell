package com.imooc.dataobject.mapper;

import com.imooc.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface ProductCategoryMapper {
    @Insert("insert into product_category(category_name,category_type) " +
            "values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insert(Map<String,Object> map);

    @Insert("insert into product_category(category_name,category_type) " +
            "values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory ProductCategory);

    @Select("select * from product_category where category_type=#{category_type}")
    @Results({
            @Result(column = "category_type",property = "categoryType"),
            @Result(column = "category_name",property = "categoryName"),
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select * from product_category where category_name=#{category_name}")
    @Results({
            @Result(column = "category_type",property = "categoryType"),
            @Result(column = "category_name",property = "categoryName"),
    })
    List<ProductCategory> findByCategoryName(String  categoryName);

    @Update("update product_category set category_name = #{categoryName} where " +
            "category_type=#{categoryType}")
    int update(@Param("categoryName") String categoryName,@Param("categoryType") Integer categoryType);

    @Update("update product_category set category_name = #{categoryName} where " +
            "category_type=#{categoryType}")
    int updateByObject( ProductCategory productCategory);

    @Delete("delete from productCategory where category_type=#{categoryType}")
    int delete(Integer categoryType);

    //xml注解方式
    ProductCategory findByType(Integer categoryType);
}
