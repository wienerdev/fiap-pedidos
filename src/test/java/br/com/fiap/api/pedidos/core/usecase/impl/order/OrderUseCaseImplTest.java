package br.com.fiap.api.pedidos.core.usecase.impl.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ClientRepository;
import br.com.fiap.api.pedidos.core.dataprovider.repository.OrderRepository;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ProductRepository;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.dataprovider.repository.ClientRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.OrderRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.ProductRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.ClientRepositoryImpl;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.OrderRepositoryImpl;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.ProductRepositoryImpl;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.UUID;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {OrderUseCaseImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderUseCaseImplTest {
    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderUseCaseImpl orderUseCaseImpl;

    @MockBean
    private ProductRepository productRepository;

    /**
     * Method under test: {@link OrderUseCaseImpl#OrderUseCaseImpl(OrderRepository, ProductRepository, ClientRepository)}
     */
    @Test
    public void testConstructor() {
        OrderRepositoryImpl orderRepository = new OrderRepositoryImpl(mock(OrderRepositoryJpa.class));
        ProductRepositoryImpl productRepositoryPort = new ProductRepositoryImpl(mock(ProductRepositoryJpa.class));
        assertTrue((new OrderUseCaseImpl(orderRepository, productRepositoryPort,
                new ClientRepositoryImpl(mock(ClientRepositoryJpa.class)))).getAllOrders().isEmpty());
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#getAllOrders()}
     */
    @Test
    public void testGetAllOrders() {
        ArrayList<Order> orderList = new ArrayList<>();
        when(orderRepository.getAll()).thenReturn(orderList);
        List<Order> actualAllOrders = orderUseCaseImpl.getAllOrders();
        assertSame(orderList, actualAllOrders);
        assertTrue(actualAllOrders.isEmpty());
        verify(orderRepository).getAll();
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#getOrderById(UUID)}
     */
    @Test
    public void testGetOrderById() {
        when(orderRepository.getById(Mockito.<UUID>any())).thenReturn(new Order());
        assertTrue(orderUseCaseImpl.getOrderById(UUID.randomUUID()).isPresent());
        verify(orderRepository).getById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#saveOrder(Order)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSaveOrder() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.Client.getClientCpf()" because the return value of "br.com.fiap.api.pedidos.core.Order.getClient()" is null
        //       at br.com.fiap.api.pedidos.core.usecase.impl.order.OrderUseCaseImpl.saveOrder(OrderUseCaseImpl.java:53)
        //   See https://diff.blue/R013 to resolve this issue.

        when(productRepository.getAllById(Mockito.<List<UUID>>any())).thenReturn(new ArrayList<>());
        orderUseCaseImpl.saveOrder(new Order());
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#saveOrder(Order)}
     */
    @Test
    public void testSaveOrder2() {
        Order order = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order);
        when(productRepository.getAllById(Mockito.<List<UUID>>any())).thenReturn(new ArrayList<>());
        Client client = new Client();
        Optional<Client> ofResult = Optional.of(client);
        when(clientRepository.identifyClientByCpf(Mockito.<String>any())).thenReturn(ofResult);

        Order order2 = new Order();
        order2.setClient(new Client());
        assertSame(order, orderUseCaseImpl.saveOrder(order2));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(productRepository).getAllById(Mockito.<List<UUID>>any());
        verify(clientRepository).identifyClientByCpf(Mockito.<String>any());
        assertTrue(order2.getOrderProducts().isEmpty());
        assertEquals(0.0d, order2.getOrderPrice().doubleValue(), 0.0);
        assertSame(client, order2.getClient());
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#saveOrder(Order)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSaveOrder3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.math.BigDecimal.doubleValue()" because the return value of "br.com.fiap.api.pedidos.core.Product.getPrice()" is null
        //       at br.com.fiap.api.pedidos.core.usecase.impl.order.OrderUseCaseImpl.lambda$calculateOrderPrice$0(OrderUseCaseImpl.java:75)
        //       at java.base/java.util.stream.ReferencePipeline$6$1.accept(ReferencePipeline.java:248)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.base/java.util.stream.DoublePipeline.collect(DoublePipeline.java:541)
        //       at java.base/java.util.stream.DoublePipeline.sum(DoublePipeline.java:450)
        //       at br.com.fiap.api.pedidos.core.usecase.impl.order.OrderUseCaseImpl.calculateOrderPrice(OrderUseCaseImpl.java:76)
        //       at br.com.fiap.api.pedidos.core.usecase.impl.order.OrderUseCaseImpl.saveOrder(OrderUseCaseImpl.java:55)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productRepository.getAllById(Mockito.<List<UUID>>any())).thenReturn(productList);
        Optional<Client> ofResult = Optional.of(new Client());
        when(clientRepository.identifyClientByCpf(Mockito.<String>any())).thenReturn(ofResult);

        Order order = new Order();
        order.setClient(new Client());
        orderUseCaseImpl.saveOrder(order);
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#saveOrder(Order)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSaveOrder4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.base/java.util.Optional.get(Optional.java:143)
        //       at br.com.fiap.api.pedidos.core.usecase.impl.order.OrderUseCaseImpl.saveOrder(OrderUseCaseImpl.java:57)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(productRepository.getAllById(Mockito.<List<UUID>>any())).thenReturn(new ArrayList<>());
        Optional<Client> emptyResult = Optional.empty();
        when(clientRepository.identifyClientByCpf(Mockito.<String>any())).thenReturn(emptyResult);

        Order order = new Order();
        order.setClient(new Client());
        orderUseCaseImpl.saveOrder(order);
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#saveOrder(Order)}
     */
    @Test
    public void testSaveOrder5() {
        Order order = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order);

        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(1L));

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.getAllById(Mockito.<List<UUID>>any())).thenReturn(productList);
        Client client = new Client();
        Optional<Client> ofResult = Optional.of(client);
        when(clientRepository.identifyClientByCpf(Mockito.<String>any())).thenReturn(ofResult);

        Order order2 = new Order();
        order2.setClient(new Client());
        assertSame(order, orderUseCaseImpl.saveOrder(order2));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(productRepository).getAllById(Mockito.<List<UUID>>any());
        verify(clientRepository).identifyClientByCpf(Mockito.<String>any());
        assertEquals(1, order2.getOrderProducts().size());
        assertSame(client, order2.getClient());
        assertEquals(1.0d, order2.getOrderPrice().doubleValue(), 0.0);
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#saveOrder(Order)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSaveOrder6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.Product.getPrice()" because "product" is null
        //       at br.com.fiap.api.pedidos.core.usecase.impl.order.OrderUseCaseImpl.lambda$calculateOrderPrice$0(OrderUseCaseImpl.java:75)
        //       at java.base/java.util.stream.ReferencePipeline$6$1.accept(ReferencePipeline.java:248)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.base/java.util.stream.DoublePipeline.collect(DoublePipeline.java:541)
        //       at java.base/java.util.stream.DoublePipeline.sum(DoublePipeline.java:450)
        //       at br.com.fiap.api.pedidos.core.usecase.impl.order.OrderUseCaseImpl.calculateOrderPrice(OrderUseCaseImpl.java:76)
        //       at br.com.fiap.api.pedidos.core.usecase.impl.order.OrderUseCaseImpl.saveOrder(OrderUseCaseImpl.java:55)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(null);
        when(productRepository.getAllById(Mockito.<List<UUID>>any())).thenReturn(productList);
        Optional<Client> ofResult = Optional.of(new Client());
        when(clientRepository.identifyClientByCpf(Mockito.<String>any())).thenReturn(ofResult);

        Order order = new Order();
        order.setClient(new Client());
        orderUseCaseImpl.saveOrder(order);
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#updateOrder(OrderStatusEnum, UUID)}
     */
    @Test
    public void testUpdateOrder() {
        doNothing().when(orderRepository)
                .updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
        orderUseCaseImpl.updateOrder(OrderStatusEnum.CREATED, UUID.randomUUID());
        verify(orderRepository).updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#updateOrder(OrderStatusEnum, UUID)}
     */
    @Test
    public void testUpdateOrder2() {
        doNothing().when(orderRepository)
                .updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
        orderUseCaseImpl.updateOrder(OrderStatusEnum.IN_PROGRESS, UUID.randomUUID());
        verify(orderRepository).updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#updateOrder(OrderStatusEnum, UUID)}
     */
    @Test
    public void testUpdateOrder3() {
        doNothing().when(orderRepository)
                .updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
        orderUseCaseImpl.updateOrder(OrderStatusEnum.COMPLETED, UUID.randomUUID());
        verify(orderRepository).updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#updateOrder(OrderStatusEnum, UUID)}
     */
    @Test
    public void testUpdateOrder4() {
        doNothing().when(orderRepository)
                .updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
        orderUseCaseImpl.updateOrder(OrderStatusEnum.CLOSED, UUID.randomUUID());
        verify(orderRepository).updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#updateOrder(OrderStatusEnum, UUID)}
     */
    @Test
    public void testUpdateOrder5() {
        doNothing().when(orderRepository)
                .updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
        orderUseCaseImpl.updateOrder(OrderStatusEnum.CANCELED, UUID.randomUUID());
        verify(orderRepository).updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderUseCaseImpl#deleteOrder(UUID)}
     */
    @Test
    public void testDeleteOrder() {
        doNothing().when(orderRepository).delete(Mockito.<UUID>any());
        orderUseCaseImpl.deleteOrder(UUID.randomUUID());
        verify(orderRepository).delete(Mockito.<UUID>any());
    }
}

