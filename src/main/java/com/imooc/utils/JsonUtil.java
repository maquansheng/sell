package com.imooc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
*@Author:ma
*@Data:16:17 2018/11/29
 * 转化成json工具
*/
public class JsonUtil {
    public static String toJson(Object object){
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson=new GsonBuilder().create();
        String json = gson.toJson(object);
        return json;
    }
}
