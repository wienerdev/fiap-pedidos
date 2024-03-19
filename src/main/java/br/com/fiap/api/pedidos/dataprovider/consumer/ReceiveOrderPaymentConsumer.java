package br.com.fiap.api.pedidos.dataprovider.consumer;

import br.com.fiap.api.pedidos.core.usecase.OrderUseCase;
import br.com.fiap.api.pedidos.dataprovider.configuration.message.OrderMessage;
import br.com.fiap.api.pedidos.dataprovider.configuration.message.PaymentMessage;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.OrderRepositoryImpl;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.SendCreatedOrderImpl;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.OrderSagaMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReceiveOrderPaymentConsumer {

    public static final Logger LOG = LogManager.getLogger(OrderMessage.class);
    private final OrderRepositoryImpl orderUseCase;

    private final SendCreatedOrderImpl sendCreatedOrder;

    @Autowired
    private KafkaTemplate<String, OrderMessage> kafkaOrder;

    public ReceiveOrderPaymentConsumer( OrderRepositoryImpl orderUseCase, SendCreatedOrderImpl sendCreatedOrder) {
        this.orderUseCase = orderUseCase;
        this.sendCreatedOrder = sendCreatedOrder;
    }

    @KafkaListener(topics ="tp-saga-payment",groupId = "order")
    public void receive(PaymentMessage paymentMessage){
        if(OrderEvent.SUCCESS_PAYMENT.equals(paymentMessage.getOrderEvent())) {
            var order = orderUseCase.getById(paymentMessage.getOrderId());
            orderUseCase.updateByOrderStatusAndOrderId(OrderStatusEnum.PREPARING, paymentMessage.getOrderId());
            var  orderMessage = OrderSagaMessage.toOrderMessage(order);
            Hibernate.initialize(orderMessage.getProductId());
            sendCreatedOrder.send(orderMessage, OrderEvent.ORDER_STARTED);
            LOG.info("CREATE ORDER TOPIC:[{}] AND MESSAGE:[{}]","tp-saga-payment", orderMessage);
        }
    }}

