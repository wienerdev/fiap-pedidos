package br.com.fiap.api.pedidos.domain.dto.request;

import br.com.fiap.api.pedidos.domain.Order;
import br.com.fiap.api.pedidos.infra.adapters.entity.ClientEntity;
import br.com.fiap.api.pedidos.infra.adapters.entity.ProductEntity;
import br.com.fiap.api.pedidos.infra.enumeration.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

public record UpdateOrderRequest(UUID orderId, String customerOrder, Boolean active, OrderStatusEnum orderStatus,
                                 List<ProductEntity> orderProducts, Double orderPrice, ClientEntity clientEntity) {
    public static Order fromResponseToOrder(UpdateOrderRequest response) {
        return new Order(response.orderId, response.customerOrder, response.active, response.orderStatus,
                response.orderProducts, response.orderPrice, response.clientEntity.toClient());
    }
}
