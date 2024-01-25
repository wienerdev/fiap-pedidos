package br.com.fiap.api.pedidos.order;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ClientRepository;
import br.com.fiap.api.pedidos.core.dataprovider.repository.OrderRepository;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ProductRepository;
import br.com.fiap.api.pedidos.core.usecase.OrderUseCase;
import br.com.fiap.api.pedidos.core.usecase.impl.order.OrderUseCaseImpl;
import br.com.fiap.api.pedidos.core.usecase.impl.product.ProductUseCaseImpl;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderUseCaseImplTest {
    

    @InjectMocks
    private OrderUseCaseImpl orderUseCase = mock(OrderUseCaseImpl.class);



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    UUID orderId = UUID.randomUUID();

    List<Product> products = Arrays.asList(
            new Product(UUID.randomUUID(), "Product1", "Description1", new BigDecimal(100), "Category1"),
            new Product(UUID.randomUUID(), "Product2", "Description2", new BigDecimal(200), "Category2")
    );
    @Test
    void getAllOrders() {
        // Arrange
        List<UUID> productIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
        Client client = new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br");

        List<Order> expectedOrders = Arrays.asList( new Order(orderId, false, OrderStatusEnum.RECEIVED, products, productIds, 10.00, client));
        when(orderUseCase.getAllOrders()).thenReturn(expectedOrders);

        // Act
        List<Order> actualOrders = orderUseCase.getAllOrders();

        // Assert
        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    void getOrderById() {
        // Arrange

        Client client = new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br");
        List<UUID> productIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
        Order orderToSave = new Order(
                orderId,
                false,
                OrderStatusEnum.RECEIVED,
                products,
                productIds,
                10.00,
                client);
        orderUseCase.saveOrder(orderToSave);
        when(orderUseCase.getOrderById(orderId).get()).thenReturn(orderToSave);

        // Act
        Optional<Order> actualOrder = orderUseCase.getOrderById(orderId);

        // Assert
        assertTrue(actualOrder.isPresent());
        assertEquals(orderToSave, actualOrder.get());
    }

    @Test
    void saveOrder() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        Client client = new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br");
        List<UUID> productIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
        List<Product> products = Arrays.asList(
                new Product(UUID.randomUUID(), "Product1", "Description1", new BigDecimal(100), "Category1"),
                new Product(UUID.randomUUID(), "Product2", "Description2", new BigDecimal(200), "Category2")
        );
        Order orderToSave = new Order(
                orderId,
                false,
                OrderStatusEnum.RECEIVED,
                products,
                productIds,
                10.00,
                client);

        // Act
        Order savedOrder = orderUseCase.saveOrder(orderToSave);

        // Assert
        assertNotNull(savedOrder);
        assertEquals(orderToSave, savedOrder);
        assertEquals(products, savedOrder.getOrderProducts());
    }

    @Test
    void updateOrder() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        OrderStatusEnum newStatus = OrderStatusEnum.DONE;

        // Act
        assertDoesNotThrow(() -> orderUseCase.updateOrder(newStatus, orderId));

    }

    @Test
    void deleteOrder() {
        // Arrange
        UUID orderId = UUID.randomUUID();

        // Act
        assertDoesNotThrow(() -> orderUseCase.deleteOrder(orderId));

    }

    @Test
    void calculateOrderPrice() {
        // Arrange
        List<Product> products = Arrays.asList(
                new Product(UUID.randomUUID(), "Product1", "Description1", new BigDecimal(100), "Category1"),
                new Product(UUID.randomUUID(), "Product2", "Description2", new BigDecimal(200), "Category2")
        );

        // Act
        double totalPrice = orderUseCase.calculateOrderPrice(products);

        // Assert
        assertEquals(300, totalPrice, 0.01);
    }
}
