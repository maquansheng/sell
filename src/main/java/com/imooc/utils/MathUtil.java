package com.imooc.utils;
/**
*@Author:ma
*@Data:12:55 2018/12/2
 * 判断金额工具
*/
public class MathUtil {
    private static final Double MONEY_RESULT=0.01;
    public static boolean equals(Double d1,Double d2){
        double result = Math.abs(d1 - d2);
        if (result<MONEY_RESULT){
            return true;
        }
        return false;
    }
}
