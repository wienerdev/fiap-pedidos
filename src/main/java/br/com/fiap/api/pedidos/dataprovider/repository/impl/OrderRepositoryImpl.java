package br.com.fiap.api.pedidos.dataprovider.repository.impl;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.dataprovider.repository.OrderRepository;
import br.com.fiap.api.pedidos.dataprovider.configuration.message.PaymentMessage;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.dataprovider.repository.OrderRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.OrderSagaMessage;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderRepositoryJpa orderRepository;

    private final SendCreatedOrderImpl sendCreatedOrder;

    private final SendCreatedPaymentImpl sendCreatedPayment;


    public OrderRepositoryImpl(OrderRepositoryJpa orderRepository, SendCreatedOrderImpl sendCreatedOrder, SendCreatedPaymentImpl sendCreatedPayment) {
        this.orderRepository = orderRepository;
        this.sendCreatedOrder = sendCreatedOrder;
        this.sendCreatedPayment = sendCreatedPayment;
    }


    @Override
    public List<Order> getAll() {
        List<OrderEntity> entities = this.orderRepository.findAll();
        return entities.stream().map(OrderEntity::toOrder).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Order getById(UUID id) {
        Optional<OrderEntity> orderEntity = this.orderRepository.findById(id);
        if (orderEntity.isPresent())
            return orderEntity.get().toOrder();
        throw new RuntimeException("Pedido não encontrado");
    }

    @Override
    @Transactional
    public Order save(Order order) {
        OrderEntity entity = new OrderEntity(order);
        Order oderSave = orderRepository.save(entity).toOrder();
        sendCreatedOrder.send(OrderSagaMessage.toOrderMessage(oderSave), OrderEvent.CREATE_ORDER);
        sendCreatedPayment.send(new PaymentMessage(oderSave.getOrderId(),"Débito",OrderEvent.PROCESSING_PAYMENT), OrderEvent.PROCESSING_PAYMENT);
        return oderSave;
    }


    @Override
    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateByOrderStatusAndOrderId(OrderStatusEnum orderStatus, UUID orderId) {
        OrderEntity order = getById(orderId).toEntity();
        if (orderStatus == OrderStatusEnum.PAID) {
            order.setIsPaymentReceived(true);
        }
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllByClientCpf(String cpf) {
        List<OrderEntity> entities = orderRepository.getAllByClientEntityClientCpf(cpf);
        return entities.stream().map(OrderEntity::toOrder).collect(Collectors.toList());
    }

    @Override
    public Boolean isPaymentReceivedByOrderId(UUID orderId) {
        Order order = getById(orderId);
        return order.getPaymentReceived();
    }

}
