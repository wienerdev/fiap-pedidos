package br.com.fiap.api.pedidos.domain.dto.request;

import br.com.fiap.api.pedidos.domain.Order;
import br.com.fiap.api.pedidos.infra.adapters.entity.ClientEntity;
import br.com.fiap.api.pedidos.infra.adapters.entity.ProductEntity;
import br.com.fiap.api.pedidos.infra.enumeration.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(String customerOrder, List<UUID> orderProductIds, String clientCpf) {

    public static Order fromResponseToOrder(CreateOrderRequest response) {
        return new Order(UUID.randomUUID(), response.customerOrder, true, OrderStatusEnum.CREATED,
                null, null, null);
    }
}