package br.com.fiap.api.pedidos.domain.port.usecase;

import br.com.fiap.api.pedidos.domain.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderUseCasePort {

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(UUID id);

    OrderResponse saveOrder(CreateOrderRequest request);

    OrderResponse updateOrder(UpdateOrderRequest request);

    void deleteOrder(UUID id);

}
