package br.com.fiap.api.pedidos.domain.port.repository;

import br.com.fiap.api.pedidos.domain.Order;
import br.com.fiap.api.pedidos.infra.adapters.entity.OrderEntity;
import br.com.fiap.api.pedidos.infra.enumeration.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

public interface OrderRepositoryPort {

    List<Order> getAll();

    Order getById(UUID id);

    Order save(Order order);

    Order update(Order order);

    void delete(UUID id);

    void updateByOrderStatusAndOrderId(OrderStatusEnum orderStatus, UUID orderId);
}

