package br.com.fiap.api.pedidos.order.out;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.dataprovider.configuration.message.OrderMessage;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;
import br.com.fiap.api.pedidos.dataprovider.repository.SendCreatedOrderOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendCreatedOrder implements SendCreatedOrderOutputPort {

    @Autowired
    private KafkaTemplate<String, OrderMessage> kafkaTemplate;

    @Override
    public void send(OrderMessage order, OrderEvent event) {
        var orderMessage = new OrderMessage();
        kafkaTemplate.send("tp-saga-order", orderMessage);
    }
}