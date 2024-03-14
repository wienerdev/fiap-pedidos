package br.com.fiap.api.pedidos.dataprovider.repository;

import br.com.fiap.api.pedidos.dataprovider.configuration.message.OrderMessage;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;


public interface SendCreatedOrderOutputPort {
    void send(OrderMessage order, OrderEvent event);

}
