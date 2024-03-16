package br.com.fiap.api.pedidos.client;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.dataprovider.repository.OrderRepository;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.dataprovider.repository.OrderRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.OrderRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderRepositoryImplTests {

    UUID orderId = UUID.randomUUID();

    List<Product> products = Arrays.asList(
            new Product(UUID.randomUUID(), "Product1", "Description1", new BigDecimal(100), "Category1"),
            new Product(UUID.randomUUID(), "Product2", "Description2", new BigDecimal(200), "Category2")
    );
    List<UUID> productIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
    Client client = new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br", "Street 01", "92389382938");
    Order order = new Order(
            orderId,
            false,
            OrderStatusEnum.RECEIVED,
            products,
            productIds,
            10.00,
            client);
    /*@Test
    void testGetAll() {
        // Given
        OrderRepositoryJpa orderRepository = mock(OrderRepositoryJpa.class);
        List<OrderEntity> orderEntities = Collections.singletonList(order.toEntity());
        when(orderRepository.findAll()).thenReturn(orderEntities);

        OrderRepository orderRepositoryImpl = new OrderRepositoryImpl(orderRepository, sendCreatedOrderOutputPort, sendCreatedPayment);

        // When
        List<Order> result = orderRepositoryImpl.getAll();

        // Then
        assertEquals(orderEntities.size(), result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetById_OrderFound() {
        // Given
        UUID orderId = UUID.randomUUID();
        OrderRepositoryJpa orderRepository = mock(OrderRepositoryJpa.class);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order.toEntity()));

        OrderRepository orderRepositoryImpl = new OrderRepositoryImpl(orderRepository, sendCreatedOrderOutputPort, sendCreatedPayment);

        // When
        Order result = orderRepositoryImpl.getById(orderId);

        // Then
        assertEquals(order.getOrderId(), result.getOrderId());
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void testGetById_OrderNotFound() {
        // Given
        UUID orderId = UUID.randomUUID();
        OrderRepositoryJpa orderRepository = mock(OrderRepositoryJpa.class);
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        OrderRepository orderRepositoryImpl = new OrderRepositoryImpl(orderRepository, sendCreatedOrderOutputPort, sendCreatedPayment);

        // When & Then
        assertThrows(RuntimeException.class, () -> orderRepositoryImpl.getById(orderId));
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void testSave() {
        // Given
        OrderRepositoryJpa orderRepository = mock(OrderRepositoryJpa.class);
         when(orderRepository.save(any(OrderEntity.class))).thenAnswer(invocation -> {
            OrderEntity savedEntity = invocation.getArgument(0);
            savedEntity.setOrderId(orderId);
            return savedEntity;
        });

        OrderRepository orderRepositoryImpl = new OrderRepositoryImpl(orderRepository, sendCreatedOrderOutputPort, sendCreatedPayment);

        // When
        Order result = orderRepositoryImpl.save(order);

        // Then
        assertNotNull(result.getOrderId());
        assertEquals(order.getPaymentReceived(), result.getPaymentReceived());
        // Add more assertions as needed
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    void testDelete() {
        // Given
        OrderRepositoryJpa orderRepository = mock(OrderRepositoryJpa.class);

        OrderRepository orderRepositoryImpl = new OrderRepositoryImpl(orderRepository, sendCreatedOrderOutputPort, sendCreatedPayment);

        // When
        orderRepositoryImpl.delete(orderId);

        // Then
        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    void testUpdateByOrderStatusAndOrderId() {
        // Given
        OrderStatusEnum orderStatus = OrderStatusEnum.DONE;
        OrderRepositoryJpa orderRepository = mock(OrderRepositoryJpa.class);

        OrderRepository orderRepositoryImpl = new OrderRepositoryImpl(orderRepository, sendCreatedOrderOutputPort, sendCreatedPayment);

        // When
        orderRepositoryImpl.updateByOrderStatusAndOrderId(orderStatus, orderId);

        // Then
        verify(orderRepository, times(1)).updateByOrderStatusAndOrderId(orderStatus, orderId);
    }*/

}
