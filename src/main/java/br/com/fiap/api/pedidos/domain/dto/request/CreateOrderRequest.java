package br.com.fiap.api.pedidos.domain.dto.request;

import br.com.fiap.api.pedidos.domain.Order;
import br.com.fiap.api.pedidos.infra.adapters.entity.ClientEntity;
import br.com.fiap.api.pedidos.infra.adapters.entity.ProductEntity;
import br.com.fiap.api.pedidos.infra.enumeration.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(String customerOrder, Boolean active, OrderStatusEnum orderStatus,
                                  List<ProductEntity> orderProducts, Double orderPrice, ClientEntity clientEntity) {

    public static Order fromResponseToOrder(CreateOrderRequest response) {
        return new Order(UUID.randomUUID(), response.customerOrder, response.active, response.orderStatus,
                response.orderProducts, response.orderPrice,response.clientEntity != null ? response.clientEntity.toClient() : null);
    }
}