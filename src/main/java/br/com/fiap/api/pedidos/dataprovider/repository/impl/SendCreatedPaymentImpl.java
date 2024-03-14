package br.com.fiap.api.pedidos.dataprovider.repository.impl;


import br.com.fiap.api.pedidos.dataprovider.configuration.message.OrderMessage;
import br.com.fiap.api.pedidos.dataprovider.configuration.message.PaymentMessage;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;
import br.com.fiap.api.pedidos.dataprovider.repository.SendCreatedOrderOutputPort;
import br.com.fiap.api.pedidos.dataprovider.repository.SendCreatedPaymentOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class SendCreatedPaymentImpl implements SendCreatedPaymentOutputPort {

    @Autowired
    private KafkaTemplate<String, PaymentMessage> kafkaPayment;


    @Override
    public void send(PaymentMessage payment, OrderEvent event) {
        payment.setOrderEvent(OrderEvent.PROCESSING_PAYMENT);
        payment.setPaymentMethod("Débito");
        kafkaPayment.send("tp-saga-payment", payment);
    }

}