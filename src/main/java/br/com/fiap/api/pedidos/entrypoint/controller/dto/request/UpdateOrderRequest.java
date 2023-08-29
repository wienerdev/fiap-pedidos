package br.com.fiap.api.pedidos.entrypoint.controller.dto.request;

import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;

import java.util.UUID;

public record UpdateOrderRequest(UUID orderId, OrderStatusEnum orderStatus) {

}
