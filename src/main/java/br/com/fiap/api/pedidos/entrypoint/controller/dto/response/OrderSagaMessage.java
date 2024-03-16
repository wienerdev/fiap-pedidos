package br.com.fiap.api.pedidos.entrypoint.controller.dto.response;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.dataprovider.configuration.message.OrderMessage;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.ClientDto;

import java.util.List;
import java.util.UUID;

public record OrderSagaMessage(String productionId, String clientCpf, UUID orderId,
                               Boolean isPaymentReceived, Double orderPrice,  List<UUID> productId) {

    public static OrderMessage toOrderMessage(Order order) {
        return new OrderMessage("teste", order.getClient().getClientCpf(),order.getOrderId(), order.getPaymentReceived(), order.getOrderPrice(),order.getOrderProductIds(), OrderEvent.CREATE_ORDER);
    }
}