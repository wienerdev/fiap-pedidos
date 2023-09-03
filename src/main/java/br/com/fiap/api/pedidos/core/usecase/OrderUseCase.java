package br.com.fiap.api.pedidos.core.usecase;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.OrderResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderUseCase {

    List<Order> getAllOrders();

    Optional<Order> getOrderById(UUID id);

    Order saveOrder(Order order);

    void updateOrder(OrderStatusEnum status, UUID id);

    void deleteOrder(UUID id);

}
