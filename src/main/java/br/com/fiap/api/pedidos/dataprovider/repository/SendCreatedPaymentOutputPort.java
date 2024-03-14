package br.com.fiap.api.pedidos.dataprovider.repository;

import br.com.fiap.api.pedidos.dataprovider.configuration.message.PaymentMessage;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;


public interface SendCreatedPaymentOutputPort {
    void send(PaymentMessage order, OrderEvent event);

}
