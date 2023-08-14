package br.com.fiap.api.pedidos.domain.dto.response;

import br.com.fiap.api.pedidos.domain.Order;
import br.com.fiap.api.pedidos.domain.dto.ClientDto;
import br.com.fiap.api.pedidos.infra.adapters.entity.ClientEntity;
import br.com.fiap.api.pedidos.infra.adapters.entity.ProductEntity;
import br.com.fiap.api.pedidos.infra.enumeration.OrderStatusEnum;

import java.util.List;
import java.util.UUID;



    public record OrderResponse(UUID orderId, String customerOrder, Boolean active, OrderStatusEnum orderStatus,
                                List<ProductEntity> orderProducts, Double orderPrice, ClientEntity client) {

    public static OrderResponse fromEntityToResponse(Order order) {
        return new OrderResponse(order.getOrderId(), order.getCustomerOrder(), order.getActive(), order.getOrderStatus(),
                order.getOrderProducts(), order.getOrderPrice(), order.getClient() != null ?  ClientDto.fromClientToClientEntity(order.getClient()) : null);
    }
}
