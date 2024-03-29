<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link rel="stylesheet" href="/sell/css/style.css"/>
</head>

<body>
<div id="wrapper" class="toggled">
<#--边栏sidebar-->
<#include "../common/nav.ftl">
        <table border="1"  class="t1">
            <div>
            <tr>
                <th>订单id</th>
                <th>姓名</th>
                <th>手机号</th>
                <th>地址</th>
                <th>金额</th>
                <th>订单状态</th>
                <th>支付方式</th>
                <th>创建时间</th>
                <th colspan="2">操作</th>
            </tr>
            <#list orderDtoPage.content as orderDto>
            <tr>
                <td>${orderDto.orderId}</td>
                <td>${orderDto.buyerName}</td>
                <td>${orderDto.buyerPhone}</td>
                <td>${orderDto.buyerAddress}</td>
                <td>${orderDto.orderAmount}</td>
                <td>${orderDto.orderStatusEnum.msg}</td>
                <td>${orderDto.payStatusEnum.msg}</td>
                <td>${orderDto.updateTime}</td>
                <td>
                    <a href="/sell/seller/order/detail?orderId=${orderDto.getOrderId()}">详情</a>
                </td>
                <td>
                    <#if orderDto.getOrderStatusEnum().msg=="新订单">
                    <a href="/sell/seller/order/cancel?orderId=${orderDto.getOrderId()}">取消</a>
                    </#if>
                </td>
            </tr>
            </#list>
            </div>
            <div class="col-md-12 column">
                <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a> </li>
                    </#if>

                    <#list 1..orderDtoPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte orderDtoPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else >
                        <li><a href="/sell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a> </li>
                    </#if>



                </ul>
            </div>
        </table>
</div>
</body>
</html>
