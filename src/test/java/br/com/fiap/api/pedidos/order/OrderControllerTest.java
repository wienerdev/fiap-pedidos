package br.com.fiap.api.pedidos.order;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.usecase.OrderUseCase;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.entrypoint.controller.OrderController;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.OrderCheckoutResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.OrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.webjars.NotFoundException;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    UUID orderId = UUID.randomUUID();

    List<Product> products = Arrays.asList(
            new Product(UUID.randomUUID(), "Product1", "Description1", new BigDecimal(100), "Category1"),
            new Product(UUID.randomUUID(), "Product2", "Description2", new BigDecimal(200), "Category2")
    );
    List<UUID> productIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
    Client client = new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br");
    Order order = new Order(
            orderId,
            false,
            OrderStatusEnum.RECEIVED,
            products,
            productIds,
            10.00,
            client);

    @Test
    void getAll() {
        // Given
        OrderUseCase orderUseCase = mock(OrderUseCase.class);
        List<Order> orders = Collections.singletonList(new Order(UUID.randomUUID(), true, null, null, null, 100.0, null));
        when(orderUseCase.getAllOrders()).thenReturn(orders);

        OrderController orderController = new OrderController(orderUseCase);

        // When
        ResponseEntity<BaseResponse<List<OrderResponse>>> result = orderController.getAll();

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody().isSuccess());
        assertEquals(orders.size(), result.getBody().getResponse().size());
        verify(orderUseCase, times(1)).getAllOrders();
    }

    @Test
    void testGetById_OrderFound() {
        // Given
        UUID orderId = UUID.randomUUID();
        OrderUseCase orderUseCase = mock(OrderUseCase.class);
        Order order = new Order(orderId, true, null, null, null, 100.0, null);
        when(orderUseCase.getOrderById(orderId)).thenReturn(java.util.Optional.of(order));

        OrderController orderController = new OrderController(orderUseCase);

        // When
        ResponseEntity<BaseResponse<OrderResponse>> result = orderController.getById(orderId);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody().isSuccess());
        assertEquals(orderId, result.getBody().getResponse().orderId());
        verify(orderUseCase, times(1)).getOrderById(orderId);
    }

    @Test
    void testGetById_OrderNotFound() {
        // Given
        OrderUseCase orderUseCase = mock(OrderUseCase.class);
        when(orderUseCase.getOrderById(orderId)).thenReturn(java.util.Optional.empty());

        OrderController orderController = new OrderController(orderUseCase);

        // When & Then
        assertThrows(NoSuchElementException.class, () -> orderController.getById(orderId));
        verify(orderUseCase, times(1)).getOrderById(orderId);
    }
    @Test
    void testCreateOrder() {
        // Given
        OrderUseCase orderUseCase = mock(OrderUseCase.class);
        CreateOrderRequest request = new CreateOrderRequest(productIds,client);
        when(orderUseCase.saveOrder(any(Order.class))).thenReturn(order);

        OrderController orderController = new OrderController(orderUseCase);

        // When
        ResponseEntity<BaseResponse<OrderResponse>> result = orderController.create(request);

        // Then
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertTrue(result.getBody().isSuccess());
        assertNotNull(result.getBody().getResponse().orderId());
        verify(orderUseCase, times(1)).saveOrder(any(Order.class));
    }

    @Test
    void testUpdateOrderStatus() {
        // Given
        OrderUseCase orderUseCase = mock(OrderUseCase.class);
        CreateOrderRequest requests = new CreateOrderRequest(productIds,client);
        when(orderUseCase.saveOrder(any(Order.class))).thenReturn(order);

        OrderController orderController = new OrderController(orderUseCase);
        ResponseEntity<BaseResponse<OrderResponse>> results = orderController.create(requests);
        UpdateOrderRequest request = new UpdateOrderRequest(orderId,OrderStatusEnum.RECEIVED);


        // When
        ResponseEntity<BaseResponse> result = orderController.update(request);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody().isSuccess());
        verify(orderUseCase, times(1)).updateOrder(request.orderStatus(), request.orderId());
    }

    @Test
    void testDeleteOrder() {
        // Given
        OrderUseCase orderUseCase = mock(OrderUseCase.class);
        OrderController orderController = new OrderController(orderUseCase);

        CreateOrderRequest requests = new CreateOrderRequest(productIds,client);
        when(orderUseCase.saveOrder(any(Order.class))).thenReturn(order);

        ResponseEntity<BaseResponse<OrderResponse>> results = orderController.create(requests);

        // When
        ResponseEntity<BaseResponse> result = orderController.delete(client.getClientId());

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody().isSuccess());
        assertEquals("Order deleted successfully", result.getBody().getResponse());
    }

    @Test
    void testOrderCheckout() {
        // Given
        OrderUseCase orderUseCase = mock(OrderUseCase.class);
        OrderController orderController = new OrderController(orderUseCase);

        CreateOrderRequest requests = new CreateOrderRequest(productIds,client);
        when(orderUseCase.saveOrder(any(Order.class))).thenReturn(order);

        ResponseEntity<BaseResponse<OrderResponse>> results = orderController.create(requests);
        String cpf = "01374050067";
        List<OrderCheckoutResponse> checkoutResponses = Collections.singletonList(new OrderCheckoutResponse(orderId,products,10.00));

  ;

        // When
        ResponseEntity<BaseResponse<List<OrderCheckoutResponse>>> result = orderController.orderCheckout(cpf);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody().isSuccess());
        verify(orderUseCase, times(1)).orderCheckout(cpf);
    }
}
