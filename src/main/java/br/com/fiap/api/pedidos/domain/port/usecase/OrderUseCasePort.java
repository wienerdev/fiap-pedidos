package br.com.fiap.api.pedidos.domain.port.usecase;

import br.com.fiap.api.pedidos.domain.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.domain.dto.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderUseCasePort {

    BaseResponse<Iterable<OrderResponse>> getAllOrders();

    BaseResponse<OrderResponse> getOrderById(UUID id);

    BaseResponse<OrderResponse> saveOrder(CreateOrderRequest request);

    BaseResponse updateOrder(UpdateOrderRequest request);

    BaseResponse deleteOrder(UUID id);

}
