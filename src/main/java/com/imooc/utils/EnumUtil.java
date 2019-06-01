package com.imooc.utils;

import com.imooc.enums.CodeEnum;

/**
*@Author:ma
*@Data:21:00 2018/12/9
*/
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
        for (T each:enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }

}
