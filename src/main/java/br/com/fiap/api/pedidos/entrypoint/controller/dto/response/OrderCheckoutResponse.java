package br.com.fiap.api.pedidos.entrypoint.controller.dto.response;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;

import java.util.List;
import java.util.UUID;

public record OrderCheckoutResponse(UUID orderId, List<Product> orderProducts, Double orderPrice) {

    public static OrderCheckoutResponse fromEntityToResponse(Order order) {
        return new OrderCheckoutResponse(order.getOrderId(), order.getOrderProducts(), order.getOrderPrice());
    }
}