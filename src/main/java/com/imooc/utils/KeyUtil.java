package com.imooc.utils;

import java.util.Random;

/**
 * @Author:ma
 * @Data:22:22 2018/11/15
 * 订单随机数
 */
public class KeyUtil {
    public static String getKeyUtil() {
        Random random = new Random();
        int number = random.nextInt(9000) + 1000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
