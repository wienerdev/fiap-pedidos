package br.com.fiap.api.pedidos.entrypoint.controller.dto.request;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(String customerOrder, List<UUID> orderProductIds, Client client) {

    public static Order fromResponseToOrder(CreateOrderRequest response) {
        return new Order(UUID.randomUUID(), response.customerOrder, true, OrderStatusEnum.CREATED,
                null,response.orderProductIds, null,response.client);
    }
}