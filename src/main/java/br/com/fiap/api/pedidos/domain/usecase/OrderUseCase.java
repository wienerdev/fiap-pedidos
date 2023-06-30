package br.com.fiap.api.pedidos.domain.usecase;

import br.com.fiap.api.pedidos.domain.Order;
import br.com.fiap.api.pedidos.domain.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.response.OrderResponse;
import br.com.fiap.api.pedidos.domain.port.repository.OrderRepositoryPort;
import br.com.fiap.api.pedidos.domain.port.usecase.OrderUseCasePort;

import java.util.List;
import java.util.UUID;

public class OrderUseCase implements OrderUseCasePort {

    private final OrderRepositoryPort orderRepository;


    public OrderUseCase(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.getAll().stream().map(order -> OrderResponse.fromEntityToResponse(order)).toList();
    }

    @Override
    public OrderResponse getOrderById(UUID id) {
        return OrderResponse.fromEntityToResponse(orderRepository.getById(id));
    }

    @Override
    public OrderResponse saveOrder(CreateOrderRequest request) {
        Order entity = CreateOrderRequest.fromResponseToOrder(request);
        return OrderResponse.fromEntityToResponse(orderRepository.save(entity));
    }

    @Override
    public OrderResponse updateOrder(UpdateOrderRequest request) {
        Order entity = UpdateOrderRequest.fromResponseToOrder(request);
        return OrderResponse.fromEntityToResponse(orderRepository.save(entity));
    }

    @Override
    public void deleteOrder(UUID id) {
        orderRepository.delete(id);
    }
}
