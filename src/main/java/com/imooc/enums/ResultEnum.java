package com.imooc.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "参数错误"),
    PRODUCT_NOT_EXIST(10, "不存在"),
    PRODUCT_STOCK_ERROR(11, "库存不足"),
    ORDERDETAIL_NOT_EXIST(12, "订单详情不存在"),
    ORDERMASTER_NOT_EXIST(13, "订单不存在"),
    ORDER_STATUS_ERROR(14, "订单状态错误"),
    ORDER_UPDATE_ERROR(15, "订单取消失败"),
    ORDER_NOT_EXIST(16,"订单不存在"),

    ORDER_SUCCESS(17,"取消订单成功"),
    ORDER_FINISHED(18,"完结订单成功");
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
