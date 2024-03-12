package br.com.fiap.api.pedidos.dataprovider.repository;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;

import java.util.UUID;

public interface SendCreatedOrderOutputPort {
    void send(Order order, OrderEvent event);
}
