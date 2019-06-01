<h1>测试专用</h1>

<#list orderDtoPage.content as orderDto>
    ${orderDto.orderId}<br>
    <td>${orderDto.orderId}</td>
    <td>${orderDto.buyerName}</td>
                                <td>${orderDto.buyerPhone}</td>
                                <td>${orderDto.buyerAddress}</td>
                                <td>${orderDto.orderAmount}</td>
                                <td>${orderDto.getOrderStatusEnum().message}</td>
                                <td>${orderDto.getPayStatusEnum().message}</td>
                                <td>${orderDto.createTime}</td>
</#list>