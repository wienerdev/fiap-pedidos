package br.com.fiap.api.pedidos.dataprovider.repository.impl;


import br.com.fiap.api.pedidos.dataprovider.configuration.message.OrderMessage;
import br.com.fiap.api.pedidos.dataprovider.configuration.message.PaymentMessage;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;
import br.com.fiap.api.pedidos.dataprovider.repository.SendCreatedOrderOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class SendCreatedOrderImpl implements SendCreatedOrderOutputPort {

    @Autowired
    private KafkaTemplate<String, OrderMessage> kafkaOrder;

    @Override
    public void send(OrderMessage order, OrderEvent event) {
        var orderMessage = new OrderMessage(order.getProductionId(), order.getClientCpf(), order.getOrderId(), order.getIsPaymentReceived(),
                order.getOrderPrice(), order.getProductId(), event);
        kafkaOrder.send("tp-saga-orders", orderMessage);
    }


}