package br.com.fiap.api.pedidos.core.dataprovider.repository;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {

    List<Order> getAll();

    Order getById(UUID id);

    Order save(Order order);

    void delete(UUID id);

    void updateByOrderStatusAndOrderId(OrderStatusEnum orderStatus, UUID orderId);

    List<Order> getAllByClientCpf(String cpf);

    Boolean isPaymentReceivedByOrderId(UUID orderId);
}

