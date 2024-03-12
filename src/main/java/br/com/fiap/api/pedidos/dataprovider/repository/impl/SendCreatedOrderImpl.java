package br.com.fiap.api.pedidos.dataprovider.repository.impl;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.dataprovider.configuration.message.OrderMessage;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;
import br.com.fiap.api.pedidos.dataprovider.repository.SendCreatedOrderOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
    public class SendCreatedOrderImpl implements SendCreatedOrderOutputPort {

        @Autowired
        private KafkaTemplate<String, OrderMessage> kafkaTemplate;

        @Override
        public void send( Order order, OrderEvent event) {
            var orderMessage = new OrderMessage(order, event);
            kafkaTemplate.send("tp-saga-order", orderMessage);
        }

    }