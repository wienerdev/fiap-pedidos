package br.com.fiap.api.pedidos.infra.adapters.repository;

import br.com.fiap.api.pedidos.domain.Order;
import br.com.fiap.api.pedidos.domain.port.repository.OrderRepositoryPort;
import br.com.fiap.api.pedidos.infra.adapters.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderRepository orderRepository;

    public OrderRepositoryAdapter(OrderRepository orderRepository) {
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
    public Order update(Order order) {
        OrderEntity entity = new OrderEntity(order);
        return orderRepository.save(entity).toOrder();
    }

    @Override
    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }
}
