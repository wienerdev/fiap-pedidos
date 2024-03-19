package br.com.fiap.api.pedidos.dataprovider.repository.impl;


import br.com.fiap.api.pedidos.dataprovider.configuration.message.OrderMessage;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;
import br.com.fiap.api.pedidos.dataprovider.repository.SendCreatedOrderOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class SendCreatedOrderImpl implements SendCreatedOrderOutputPort {

    public static final Logger LOG = LogManager.getLogger(OrderMessage.class);
    @Autowired
    private KafkaTemplate<String, OrderMessage> kafkaOrder;

    @Override
    public void send(OrderMessage order, OrderEvent event) {
        var orderMessage = new OrderMessage(order.getProductionId(), order.getClientCpf(), order.getOrderId(), order.getIsPaymentReceived(),
                order.getOrderPrice(), order.getProductId(), event);
        kafkaOrder.send("tp-saga-orders", orderMessage);
        LOG.info("CREATE ORDER TOPIC:[{}] AND MESSAGE:[{}]","tp-saga-orders", orderMessage);
    }


}