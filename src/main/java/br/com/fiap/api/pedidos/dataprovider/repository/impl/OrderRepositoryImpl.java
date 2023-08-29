package br.com.fiap.api.pedidos.dataprovider.repository.impl;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.dataprovider.repository.OrderRepository;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.dataprovider.repository.OrderRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderRepositoryJpa orderRepository;

    public OrderRepositoryImpl(OrderRepositoryJpa orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAll() {
        List<OrderEntity> entities = this.orderRepository.findAll();
        return entities.stream().map(OrderEntity::toOrder).collect(Collectors.toList());
    }

    @Override
    public Order getById(UUID id) {
        Optional<OrderEntity> orderEntity = this.orderRepository.findById(id);
        if (orderEntity.isPresent())
            return orderEntity.get().toOrder();
        throw new RuntimeException("Pedido não encontrado");
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = new OrderEntity(order);
        return orderRepository.save(entity).toOrder();
    }


    @Override
    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void updateByOrderStatusAndOrderId(OrderStatusEnum orderStatus, UUID orderId) {
        orderRepository.updateByOrderStatusAndOrderId(orderStatus,orderId);
    }

}
