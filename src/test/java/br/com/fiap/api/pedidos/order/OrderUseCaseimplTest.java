package br.com.fiap.api.pedidos.order;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ClientRepository;
import br.com.fiap.api.pedidos.core.dataprovider.repository.OrderRepository;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ProductRepository;
import br.com.fiap.api.pedidos.core.usecase.impl.order.OrderUseCaseImpl;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.dataprovider.repository.OrderRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.OrderRepositoryImpl;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.SendCreatedOrderImpl;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.SendCreatedPaymentImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class OrderUseCaseimplTest {


    @InjectMocks
    private OrderUseCaseImpl orderUseCase;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ClientRepository clientRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    UUID orderId = UUID.randomUUID();

    List<Product> products = Arrays.asList(
            new Product(UUID.randomUUID(), "Product1", "Description1", new BigDecimal(100), "Category1"),
            new Product(UUID.randomUUID(), "Product2", "Description2", new BigDecimal(200), "Category2")
    );
    Client client = new Client(orderId, "01374050067", "John Doe", "alexandre.dias@meta.com.br", "Street 01", "1921992");
    List<UUID> productIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
    @Test
    void getAllOrders() {
        // Arrange
        List<UUID> productIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
        Client client = new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br", "Street 01", "1921992");

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
        Client client = new Client(UUID.randomUUID(), "01374050067", "John Doe", "alexandre.dias@meta.com.br", "Street 01", "1921992");
        List<UUID> productIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
        Order orderToSave = new Order(
                orderId,
                false,
                OrderStatusEnum.RECEIVED,
                products,
                productIds,
                10.00,
                client);
//
        // Act
        Optional<Order> actualOrder = orderUseCase.getOrderById(orderId);

        // Assert
       // assertTrue(actualOrder.isPresent());
      //  assertEquals(orderToSave.getOrderId(), actualOrder.get().getOrderId());
    }


    /*@Test
    void saveOrder() {
//        // Arrange
//        UUID orderId = UUID.randomUUID();
//        Client client = new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br");
//        List<UUID> productIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
//        List<Product> products = Arrays.asList(
//                new Product(UUID.randomUUID(), "Product1", "Description1", new BigDecimal(100), "Category1"),
//                new Product(UUID.randomUUID(), "Product2", "Description2", new BigDecimal(200), "Category2")
//        );
//        Order orderToSave = new Order(
//                orderId,
//                false,
//                OrderStatusEnum.RECEIVED,
//                products,
//                productIds,
//                10.00,
//                client);
//
//        // Act
//        Order savedOrder = orderUseCase.saveOrder(orderToSave);

        // Assert
//        assertNotNull(savedOrder);
   //     assertEquals(orderToSave, savedOrder);
   //     assertEquals(products, savedOrder.getOrderProducts());
        Order orderToSave = new Order(
                orderId,
                false,
                OrderStatusEnum.RECEIVED,
                products,
                productIds,
                10.00,
                client);

        List<Order> expectedOrders = Arrays.asList(orderToSave);
        OrderRepositoryJpa orderRepository = mock(OrderRepositoryJpa.class);
        SendCreatedOrderImpl sendCreatedOrderOutputPort = new SendCreatedOrderImpl();
        SendCreatedPaymentImpl sendCreatedPayment= new SendCreatedPaymentImpl();
        Order order = new Order(
                orderId,
                false,
                OrderStatusEnum.RECEIVED,
                products,
                productIds,
                10.00,
                client);
        when(orderRepository.save(any(OrderEntity.class))).thenAnswer(invocation -> {
            OrderEntity savedEntity = invocation.getArgument(0);
            savedEntity.setOrderId(UUID.randomUUID());
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
    }*/


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
        //double totalPrice = orderUseCase.calculateOrderPrice(products);

        // Assert
//        assertEquals(0.01, totalPrice, 0.01);
    }
}
