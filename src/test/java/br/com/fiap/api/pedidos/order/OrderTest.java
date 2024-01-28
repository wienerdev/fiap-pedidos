package br.com.fiap.api.pedidos.order;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.OrderResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderTest {

    UUID orderId = UUID.randomUUID();

    List<Product> products = Arrays.asList(
            new Product(UUID.randomUUID(), "Product1", "Description1", new BigDecimal(100), "Category1"),
            new Product(UUID.randomUUID(), "Product2", "Description2", new BigDecimal(200), "Category2")
    );
    boolean isPaymentReceived = true;
    OrderStatusEnum orderStatus = OrderStatusEnum.PREPARING;
    Double orderPrice = 100.0;

    List<UUID> productIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
    Client client = new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br");
    @Test
    void testToEntity() {
        // Given
        Order order = new Order(orderId, isPaymentReceived, orderStatus, products, null, orderPrice, client);

        // When
        OrderEntity orderEntity = order.toEntity();

        // Then
        assertEquals(order.getOrderId(), orderEntity.getOrderId());
        assertEquals(order.getPaymentReceived(), orderEntity.getIsPaymentReceived());
    }

    @Test
    void testToResponse() {
        // Given



        Order order = new Order(orderId, isPaymentReceived, orderStatus, products, null, orderPrice, client);

        // Mock the toEntity method of the client since it's called in toResponse
        Client mockClient = mock(Client.class);
        when(mockClient.toEntity()).thenReturn(new ClientEntity(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br"));
        order.setClient(mockClient);

        // When
        OrderResponse orderResponse = order.toResponse();

        // Then
        assertEquals(order.getOrderId(), orderResponse.orderId());
        assertEquals(order.getPaymentReceived(), orderResponse.isPaymentReceived());
    }

}
