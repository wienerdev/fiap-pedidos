package br.com.fiap.api.pedidos.dataprovider.consumer;

import br.com.fiap.api.pedidos.core.usecase.OrderUseCase;
import br.com.fiap.api.pedidos.dataprovider.configuration.message.OrderMessage;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReceiveOrderPaymentConsumer {

    private final OrderUseCase orderUseCase;

    public ReceiveOrderPaymentConsumer(OrderUseCase orderGateway) {
        this.orderUseCase = orderGateway;
    }

    @KafkaListener(topics ="tp_saga_order",groupId = "payment")
    public void receive(OrderMessage orderMessage){
        if(OrderEvent.SUCCESS_PAYMENT.equals(orderMessage.getOrderEvent())){
                orderUseCase.updateOrder(OrderStatusEnum.PREPARING,orderMessage.getOrder().getOrderId());
            }
        }

    }

