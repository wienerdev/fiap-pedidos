package br.com.fiap.api.pedidos.order.out;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.dataprovider.configuration.message.OrderMessage;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;
import br.com.fiap.api.pedidos.dataprovider.repository.SendCreatedOrderOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SendCreatedOrder implements SendCreatedOrderOutputPort {

    @Autowired
    private KafkaTemplate<String, OrderMessage> kafkaTemplate;

    @Override
    public void send(OrderMessage order, OrderEvent event) {
//        var orderMessage = new OrderMessage(UUID.randomUUID().toString(), order.getClient().getClientCpf(),
//                order.getOrderId(), true, order.getOrderPrice(), order.getOrderProductIds(), event);
        kafkaTemplate.send("tp-saga-order", order);
    }
}