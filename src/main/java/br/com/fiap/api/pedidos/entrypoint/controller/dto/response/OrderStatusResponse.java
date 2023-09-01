package br.com.fiap.api.pedidos.entrypoint.controller.dto.response;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;

import java.util.UUID;

public record OrderStatusResponse(UUID orderId, OrderStatusEnum orderStatus) {

    public static OrderStatusResponse fromOrderStatusToResponse(Order order){
        return new OrderStatusResponse(order.getOrderId(),order.getOrderStatus());
    }
}
