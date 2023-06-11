package br.com.fiap.api.pedidos.domain.usecase;

import br.com.fiap.api.pedidos.domain.Order;
import br.com.fiap.api.pedidos.domain.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.response.OrderResponse;
import br.com.fiap.api.pedidos.domain.port.repository.OrderRepositoryPort;
import br.com.fiap.api.pedidos.domain.port.usecase.OrderUseCasePort;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;

public class OrderUseCase implements OrderUseCasePort {

    private final OrderRepositoryPort orderRepository;

    private final ModelMapper mapper;

    public OrderUseCase(OrderRepositoryPort orderRepository, ModelMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }


    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.getAll().stream().map(order -> mapper.map(order, OrderResponse.class)).toList();
    }

    @Override
    public OrderResponse getOrderById(UUID id) {
        return mapper.map(orderRepository.getById(id), OrderResponse.class);
    }

    @Override
    public OrderResponse saveOrder(CreateOrderRequest request) {
        Order entity = mapper.map(request, Order.class);
        entity.setOrderId(UUID.randomUUID());
        orderRepository.save(entity);
        return mapper.map(entity, OrderResponse.class);
    }

    @Override
    public OrderResponse updateOrder(UpdateOrderRequest request) {
        Order entity = mapper.map(request, Order.class);
        orderRepository.save(entity);
        return mapper.map(entity, OrderResponse.class);
    }

    @Override
    public void deleteOrder(UUID id) {
        orderRepository.delete(id);
    }
}
