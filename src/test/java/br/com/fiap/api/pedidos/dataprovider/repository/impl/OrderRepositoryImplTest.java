package br.com.fiap.api.pedidos.dataprovider.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.dataprovider.repository.OrderRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ProductEntity;

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

@ContextConfiguration(classes = {OrderRepositoryImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderRepositoryImplTest {
    @Autowired
    private OrderRepositoryImpl orderRepositoryImpl;

    @MockBean
    private OrderRepositoryJpa orderRepositoryJpa;

    /**
     * Method under test: {@link OrderRepositoryImpl#getAll()}
     */
    @Test
    public void testGetAll() {
        when(orderRepositoryJpa.findAll()).thenReturn(new ArrayList<>());
        assertTrue(orderRepositoryImpl.getAll().isEmpty());
        verify(orderRepositoryJpa).findAll();
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#getAll()}
     */
    @Test
    public void testGetAll2() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        clientEntity.setClientId(UUID.randomUUID());
        clientEntity.setClientName("Dr Jane Doe");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setActive(true);
        orderEntity.setClientEntity(clientEntity);
        orderEntity.setCustomerOrder("Customer Order");
        orderEntity.setOrderId(UUID.randomUUID());
        orderEntity.setOrderPrice(10.0d);
        orderEntity.setOrderProductIds(new ArrayList<>());
        orderEntity.setOrderProducts(new ArrayList<>());
        orderEntity.setOrderStatus(OrderStatusEnum.CREATED);

        ArrayList<OrderEntity> orderEntityList = new ArrayList<>();
        orderEntityList.add(orderEntity);
        when(orderRepositoryJpa.findAll()).thenReturn(orderEntityList);
        assertEquals(1, orderRepositoryImpl.getAll().size());
        verify(orderRepositoryJpa).findAll();
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#getAll()}
     */
    @Test
    public void testGetAll3() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        clientEntity.setClientId(UUID.randomUUID());
        clientEntity.setClientName("Dr Jane Doe");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setActive(true);
        orderEntity.setClientEntity(clientEntity);
        orderEntity.setCustomerOrder("Customer Order");
        orderEntity.setOrderId(UUID.randomUUID());
        orderEntity.setOrderPrice(10.0d);
        orderEntity.setOrderProductIds(new ArrayList<>());
        orderEntity.setOrderProducts(new ArrayList<>());
        orderEntity.setOrderStatus(OrderStatusEnum.CREATED);

        ClientEntity clientEntity2 = new ClientEntity();
        clientEntity2.setClientCpf("42");
        clientEntity2.setClientEmail("john.smith@example.org");
        clientEntity2.setClientId(UUID.randomUUID());
        clientEntity2.setClientName("Mr John Smith");

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setActive(false);
        orderEntity2.setClientEntity(clientEntity2);
        orderEntity2.setCustomerOrder("42");
        orderEntity2.setOrderId(UUID.randomUUID());
        orderEntity2.setOrderPrice(0.5d);
        orderEntity2.setOrderProductIds(new ArrayList<>());
        orderEntity2.setOrderProducts(new ArrayList<>());
        orderEntity2.setOrderStatus(OrderStatusEnum.IN_PROGRESS);

        ArrayList<OrderEntity> orderEntityList = new ArrayList<>();
        orderEntityList.add(orderEntity2);
        orderEntityList.add(orderEntity);
        when(orderRepositoryJpa.findAll()).thenReturn(orderEntityList);
        assertEquals(2, orderRepositoryImpl.getAll().size());
        verify(orderRepositoryJpa).findAll();
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#getAll()}
     */
    @Test
    public void testGetAll4() {
        when(orderRepositoryJpa.findAll()).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> orderRepositoryImpl.getAll());
        verify(orderRepositoryJpa).findAll();
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#getById(UUID)}
     */
    @Test
    public void testGetById() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        UUID clientId = UUID.randomUUID();
        clientEntity.setClientId(clientId);
        clientEntity.setClientName("Dr Jane Doe");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setActive(true);
        orderEntity.setClientEntity(clientEntity);
        orderEntity.setCustomerOrder("Customer Order");
        UUID orderId = UUID.randomUUID();
        orderEntity.setOrderId(orderId);
        orderEntity.setOrderPrice(10.0d);
        ArrayList<UUID> orderProductIds = new ArrayList<>();
        orderEntity.setOrderProductIds(orderProductIds);
        ArrayList<ProductEntity> orderProducts = new ArrayList<>();
        orderEntity.setOrderProducts(orderProducts);
        orderEntity.setOrderStatus(OrderStatusEnum.CREATED);
        Optional<OrderEntity> ofResult = Optional.of(orderEntity);
        when(orderRepositoryJpa.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        Order actualById = orderRepositoryImpl.getById(UUID.randomUUID());
        assertTrue(actualById.getActive());
        assertEquals(OrderStatusEnum.CREATED, actualById.getOrderStatus());
        assertEquals(orderProductIds, actualById.getOrderProducts());
        assertEquals(orderProducts, actualById.getOrderProductIds());
        assertEquals(10.0d, actualById.getOrderPrice().doubleValue(), 0.0);
        assertSame(orderId, actualById.getOrderId());
        assertEquals("Customer Order", actualById.getCustomerOrder());
        Client client = actualById.getClient();
        assertEquals("Dr Jane Doe", client.getClientName());
        assertSame(clientId, client.getClientId());
        assertEquals("jane.doe@example.org", client.getClientEmail());
        assertEquals("Client Cpf", client.getClientCpf());
        verify(orderRepositoryJpa).findById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#getById(UUID)}
     */
    @Test
    public void testGetById2() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        clientEntity.setClientId(UUID.randomUUID());
        clientEntity.setClientName("Dr Jane Doe");
        OrderEntity orderEntity = mock(OrderEntity.class);
        Order order = new Order();
        when(orderEntity.toOrder()).thenReturn(order);
        doNothing().when(orderEntity).setActive(Mockito.<Boolean>any());
        doNothing().when(orderEntity).setClientEntity(Mockito.<ClientEntity>any());
        doNothing().when(orderEntity).setCustomerOrder(Mockito.<String>any());
        doNothing().when(orderEntity).setOrderId(Mockito.<UUID>any());
        doNothing().when(orderEntity).setOrderPrice(Mockito.<Double>any());
        doNothing().when(orderEntity).setOrderProductIds(Mockito.<List<UUID>>any());
        doNothing().when(orderEntity).setOrderProducts(Mockito.<List<ProductEntity>>any());
        doNothing().when(orderEntity).setOrderStatus(Mockito.<OrderStatusEnum>any());
        orderEntity.setActive(true);
        orderEntity.setClientEntity(clientEntity);
        orderEntity.setCustomerOrder("Customer Order");
        orderEntity.setOrderId(UUID.randomUUID());
        orderEntity.setOrderPrice(10.0d);
        orderEntity.setOrderProductIds(new ArrayList<>());
        orderEntity.setOrderProducts(new ArrayList<>());
        orderEntity.setOrderStatus(OrderStatusEnum.CREATED);
        Optional<OrderEntity> ofResult = Optional.of(orderEntity);
        when(orderRepositoryJpa.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        assertSame(order, orderRepositoryImpl.getById(UUID.randomUUID()));
        verify(orderRepositoryJpa).findById(Mockito.<UUID>any());
        verify(orderEntity).toOrder();
        verify(orderEntity).setActive(Mockito.<Boolean>any());
        verify(orderEntity).setClientEntity(Mockito.<ClientEntity>any());
        verify(orderEntity).setCustomerOrder(Mockito.<String>any());
        verify(orderEntity).setOrderId(Mockito.<UUID>any());
        verify(orderEntity).setOrderPrice(Mockito.<Double>any());
        verify(orderEntity).setOrderProductIds(Mockito.<List<UUID>>any());
        verify(orderEntity).setOrderProducts(Mockito.<List<ProductEntity>>any());
        verify(orderEntity).setOrderStatus(Mockito.<OrderStatusEnum>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#getById(UUID)}
     */
    @Test
    public void testGetById3() {
        Optional<OrderEntity> emptyResult = Optional.empty();
        when(orderRepositoryJpa.findById(Mockito.<UUID>any())).thenReturn(emptyResult);
        assertThrows(RuntimeException.class, () -> orderRepositoryImpl.getById(UUID.randomUUID()));
        verify(orderRepositoryJpa).findById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#save(Order)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSave() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Collection.stream()" because "that" is null
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity.<init>(OrderEntity.java:45)
        //       at br.com.fiap.api.pedidos.dataprovider.repository.impl.OrderRepositoryImpl.save(OrderRepositoryImpl.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        orderRepositoryImpl.save(new Order());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#save(Order)}
     */
    @Test
    public void testSave2() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        UUID clientId = UUID.randomUUID();
        clientEntity.setClientId(clientId);
        clientEntity.setClientName("Dr Jane Doe");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setActive(true);
        orderEntity.setClientEntity(clientEntity);
        orderEntity.setCustomerOrder("Customer Order");
        UUID orderId = UUID.randomUUID();
        orderEntity.setOrderId(orderId);
        orderEntity.setOrderPrice(10.0d);
        ArrayList<UUID> orderProductIds = new ArrayList<>();
        orderEntity.setOrderProductIds(orderProductIds);
        ArrayList<ProductEntity> orderProducts = new ArrayList<>();
        orderEntity.setOrderProducts(orderProducts);
        orderEntity.setOrderStatus(OrderStatusEnum.CREATED);
        when(orderRepositoryJpa.save(Mockito.<OrderEntity>any())).thenReturn(orderEntity);

        ArrayList<Product> orderProducts2 = new ArrayList<>();
        orderProducts2.add(new Product());

        Order order = new Order();
        order.setOrderProducts(orderProducts2);
        Order actualSaveResult = orderRepositoryImpl.save(order);
        assertTrue(actualSaveResult.getActive());
        assertEquals(OrderStatusEnum.CREATED, actualSaveResult.getOrderStatus());
        assertEquals(orderProductIds, actualSaveResult.getOrderProducts());
        assertEquals(orderProducts, actualSaveResult.getOrderProductIds());
        assertEquals(10.0d, actualSaveResult.getOrderPrice().doubleValue(), 0.0);
        assertSame(orderId, actualSaveResult.getOrderId());
        assertEquals("Customer Order", actualSaveResult.getCustomerOrder());
        Client client = actualSaveResult.getClient();
        assertEquals("Dr Jane Doe", client.getClientName());
        assertSame(clientId, client.getClientId());
        assertEquals("jane.doe@example.org", client.getClientEmail());
        assertEquals("Client Cpf", client.getClientCpf());
        verify(orderRepositoryJpa).save(Mockito.<OrderEntity>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#save(Order)}
     */
    @Test
    public void testSave3() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        clientEntity.setClientId(UUID.randomUUID());
        clientEntity.setClientName("Dr Jane Doe");
        OrderEntity orderEntity = mock(OrderEntity.class);
        Order order = new Order();
        when(orderEntity.toOrder()).thenReturn(order);
        doNothing().when(orderEntity).setActive(Mockito.<Boolean>any());
        doNothing().when(orderEntity).setClientEntity(Mockito.<ClientEntity>any());
        doNothing().when(orderEntity).setCustomerOrder(Mockito.<String>any());
        doNothing().when(orderEntity).setOrderId(Mockito.<UUID>any());
        doNothing().when(orderEntity).setOrderPrice(Mockito.<Double>any());
        doNothing().when(orderEntity).setOrderProductIds(Mockito.<List<UUID>>any());
        doNothing().when(orderEntity).setOrderProducts(Mockito.<List<ProductEntity>>any());
        doNothing().when(orderEntity).setOrderStatus(Mockito.<OrderStatusEnum>any());
        orderEntity.setActive(true);
        orderEntity.setClientEntity(clientEntity);
        orderEntity.setCustomerOrder("Customer Order");
        orderEntity.setOrderId(UUID.randomUUID());
        orderEntity.setOrderPrice(10.0d);
        orderEntity.setOrderProductIds(new ArrayList<>());
        orderEntity.setOrderProducts(new ArrayList<>());
        orderEntity.setOrderStatus(OrderStatusEnum.CREATED);
        when(orderRepositoryJpa.save(Mockito.<OrderEntity>any())).thenReturn(orderEntity);

        ArrayList<Product> orderProducts = new ArrayList<>();
        orderProducts.add(new Product());

        Order order2 = new Order();
        order2.setOrderProducts(orderProducts);
        assertSame(order, orderRepositoryImpl.save(order2));
        verify(orderRepositoryJpa).save(Mockito.<OrderEntity>any());
        verify(orderEntity).toOrder();
        verify(orderEntity).setActive(Mockito.<Boolean>any());
        verify(orderEntity).setClientEntity(Mockito.<ClientEntity>any());
        verify(orderEntity).setCustomerOrder(Mockito.<String>any());
        verify(orderEntity).setOrderId(Mockito.<UUID>any());
        verify(orderEntity).setOrderPrice(Mockito.<Double>any());
        verify(orderEntity).setOrderProductIds(Mockito.<List<UUID>>any());
        verify(orderEntity).setOrderProducts(Mockito.<List<ProductEntity>>any());
        verify(orderEntity).setOrderStatus(Mockito.<OrderStatusEnum>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#save(Order)}
     */
    @Test
    public void testSave4() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        clientEntity.setClientId(UUID.randomUUID());
        clientEntity.setClientName("Dr Jane Doe");
        OrderEntity orderEntity = mock(OrderEntity.class);
        Order order = new Order();
        when(orderEntity.toOrder()).thenReturn(order);
        doNothing().when(orderEntity).setActive(Mockito.<Boolean>any());
        doNothing().when(orderEntity).setClientEntity(Mockito.<ClientEntity>any());
        doNothing().when(orderEntity).setCustomerOrder(Mockito.<String>any());
        doNothing().when(orderEntity).setOrderId(Mockito.<UUID>any());
        doNothing().when(orderEntity).setOrderPrice(Mockito.<Double>any());
        doNothing().when(orderEntity).setOrderProductIds(Mockito.<List<UUID>>any());
        doNothing().when(orderEntity).setOrderProducts(Mockito.<List<ProductEntity>>any());
        doNothing().when(orderEntity).setOrderStatus(Mockito.<OrderStatusEnum>any());
        orderEntity.setActive(true);
        orderEntity.setClientEntity(clientEntity);
        orderEntity.setCustomerOrder("Customer Order");
        orderEntity.setOrderId(UUID.randomUUID());
        orderEntity.setOrderPrice(10.0d);
        orderEntity.setOrderProductIds(new ArrayList<>());
        orderEntity.setOrderProducts(new ArrayList<>());
        orderEntity.setOrderStatus(OrderStatusEnum.CREATED);
        when(orderRepositoryJpa.save(Mockito.<OrderEntity>any())).thenReturn(orderEntity);

        ArrayList<Product> orderProducts = new ArrayList<>();
        orderProducts.add(new Product());
        UUID orderId = UUID.randomUUID();
        ArrayList<Product> orderProducts2 = new ArrayList<>();
        ArrayList<UUID> orderProductIds = new ArrayList<>();

        Order order2 = new Order(orderId, "Customer Order", true, OrderStatusEnum.CREATED, orderProducts2,
                orderProductIds, 10.0d, new Client());
        order2.setOrderProducts(orderProducts);
        assertSame(order, orderRepositoryImpl.save(order2));
        verify(orderRepositoryJpa).save(Mockito.<OrderEntity>any());
        verify(orderEntity).toOrder();
        verify(orderEntity).setActive(Mockito.<Boolean>any());
        verify(orderEntity).setClientEntity(Mockito.<ClientEntity>any());
        verify(orderEntity).setCustomerOrder(Mockito.<String>any());
        verify(orderEntity).setOrderId(Mockito.<UUID>any());
        verify(orderEntity).setOrderPrice(Mockito.<Double>any());
        verify(orderEntity).setOrderProductIds(Mockito.<List<UUID>>any());
        verify(orderEntity).setOrderProducts(Mockito.<List<ProductEntity>>any());
        verify(orderEntity).setOrderStatus(Mockito.<OrderStatusEnum>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#save(Order)}
     */
    @Test
    public void testSave5() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        clientEntity.setClientId(UUID.randomUUID());
        clientEntity.setClientName("Dr Jane Doe");
        OrderEntity orderEntity = mock(OrderEntity.class);
        Order order = new Order();
        when(orderEntity.toOrder()).thenReturn(order);
        doNothing().when(orderEntity).setActive(Mockito.<Boolean>any());
        doNothing().when(orderEntity).setClientEntity(Mockito.<ClientEntity>any());
        doNothing().when(orderEntity).setCustomerOrder(Mockito.<String>any());
        doNothing().when(orderEntity).setOrderId(Mockito.<UUID>any());
        doNothing().when(orderEntity).setOrderPrice(Mockito.<Double>any());
        doNothing().when(orderEntity).setOrderProductIds(Mockito.<List<UUID>>any());
        doNothing().when(orderEntity).setOrderProducts(Mockito.<List<ProductEntity>>any());
        doNothing().when(orderEntity).setOrderStatus(Mockito.<OrderStatusEnum>any());
        orderEntity.setActive(true);
        orderEntity.setClientEntity(clientEntity);
        orderEntity.setCustomerOrder("Customer Order");
        orderEntity.setOrderId(UUID.randomUUID());
        orderEntity.setOrderPrice(10.0d);
        orderEntity.setOrderProductIds(new ArrayList<>());
        orderEntity.setOrderProducts(new ArrayList<>());
        orderEntity.setOrderStatus(OrderStatusEnum.CREATED);
        when(orderRepositoryJpa.save(Mockito.<OrderEntity>any())).thenReturn(orderEntity);

        ArrayList<Product> orderProducts = new ArrayList<>();
        orderProducts.add(new Product());
        Order order2 = mock(Order.class);
        when(order2.getClient()).thenReturn(new Client());
        when(order2.getOrderStatus()).thenReturn(OrderStatusEnum.CREATED);
        when(order2.getActive()).thenReturn(true);
        when(order2.getOrderPrice()).thenReturn(10.0d);
        when(order2.getCustomerOrder()).thenReturn("Customer Order");
        when(order2.getOrderProducts()).thenReturn(new ArrayList<>());
        when(order2.getOrderId()).thenReturn(UUID.randomUUID());
        doNothing().when(order2).setOrderProducts(Mockito.<List<Product>>any());
        order2.setOrderProducts(orderProducts);
        assertSame(order, orderRepositoryImpl.save(order2));
        verify(orderRepositoryJpa).save(Mockito.<OrderEntity>any());
        verify(orderEntity).toOrder();
        verify(orderEntity).setActive(Mockito.<Boolean>any());
        verify(orderEntity).setClientEntity(Mockito.<ClientEntity>any());
        verify(orderEntity).setCustomerOrder(Mockito.<String>any());
        verify(orderEntity).setOrderId(Mockito.<UUID>any());
        verify(orderEntity).setOrderPrice(Mockito.<Double>any());
        verify(orderEntity).setOrderProductIds(Mockito.<List<UUID>>any());
        verify(orderEntity).setOrderProducts(Mockito.<List<ProductEntity>>any());
        verify(orderEntity).setOrderStatus(Mockito.<OrderStatusEnum>any());
        verify(order2, atLeast(1)).getClient();
        verify(order2).getOrderStatus();
        verify(order2).getActive();
        verify(order2).getOrderPrice();
        verify(order2).getCustomerOrder();
        verify(order2).getOrderProducts();
        verify(order2).getOrderId();
        verify(order2).setOrderProducts(Mockito.<List<Product>>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#save(Order)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSave6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.RuntimeException: foo
        //       at br.com.fiap.api.pedidos.entrypoint.controller.dto.ClientDto.fromClientToClientEntity(ClientDto.java:10)
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity.<init>(OrderEntity.java:49)
        //       at br.com.fiap.api.pedidos.dataprovider.repository.impl.OrderRepositoryImpl.save(OrderRepositoryImpl.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        clientEntity.setClientId(UUID.randomUUID());
        clientEntity.setClientName("Dr Jane Doe");
        OrderEntity orderEntity = mock(OrderEntity.class);
        when(orderEntity.toOrder()).thenReturn(new Order());
        doNothing().when(orderEntity).setActive(Mockito.<Boolean>any());
        doNothing().when(orderEntity).setClientEntity(Mockito.<ClientEntity>any());
        doNothing().when(orderEntity).setCustomerOrder(Mockito.<String>any());
        doNothing().when(orderEntity).setOrderId(Mockito.<UUID>any());
        doNothing().when(orderEntity).setOrderPrice(Mockito.<Double>any());
        doNothing().when(orderEntity).setOrderProductIds(Mockito.<List<UUID>>any());
        doNothing().when(orderEntity).setOrderProducts(Mockito.<List<ProductEntity>>any());
        doNothing().when(orderEntity).setOrderStatus(Mockito.<OrderStatusEnum>any());
        orderEntity.setActive(true);
        orderEntity.setClientEntity(clientEntity);
        orderEntity.setCustomerOrder("Customer Order");
        orderEntity.setOrderId(UUID.randomUUID());
        orderEntity.setOrderPrice(10.0d);
        orderEntity.setOrderProductIds(new ArrayList<>());
        orderEntity.setOrderProducts(new ArrayList<>());
        orderEntity.setOrderStatus(OrderStatusEnum.CREATED);
        when(orderRepositoryJpa.save(Mockito.<OrderEntity>any())).thenReturn(orderEntity);

        ArrayList<Product> orderProducts = new ArrayList<>();
        orderProducts.add(new Product());
        Client client = mock(Client.class);
        when(client.getClientCpf()).thenThrow(new RuntimeException("foo"));
        when(client.getClientEmail()).thenThrow(new RuntimeException("foo"));
        when(client.getClientName()).thenThrow(new RuntimeException("foo"));
        when(client.getClientId()).thenThrow(new RuntimeException("foo"));
        Order order = mock(Order.class);
        when(order.getClient()).thenReturn(client);
        when(order.getOrderStatus()).thenReturn(OrderStatusEnum.CREATED);
        when(order.getActive()).thenReturn(true);
        when(order.getOrderPrice()).thenReturn(10.0d);
        when(order.getCustomerOrder()).thenReturn("Customer Order");
        when(order.getOrderProducts()).thenReturn(new ArrayList<>());
        when(order.getOrderId()).thenReturn(UUID.randomUUID());
        doNothing().when(order).setOrderProducts(Mockito.<List<Product>>any());
        order.setOrderProducts(orderProducts);
        orderRepositoryImpl.save(order);
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#delete(UUID)}
     */
    @Test
    public void testDelete() {
        doNothing().when(orderRepositoryJpa).deleteById(Mockito.<UUID>any());
        orderRepositoryImpl.delete(UUID.randomUUID());
        verify(orderRepositoryJpa).deleteById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#delete(UUID)}
     */
    @Test
    public void testDelete2() {
        doThrow(new RuntimeException("foo")).when(orderRepositoryJpa).deleteById(Mockito.<UUID>any());
        assertThrows(RuntimeException.class, () -> orderRepositoryImpl.delete(UUID.randomUUID()));
        verify(orderRepositoryJpa).deleteById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#updateByOrderStatusAndOrderId(OrderStatusEnum, UUID)}
     */
    @Test
    public void testUpdateByOrderStatusAndOrderId() {
        doNothing().when(orderRepositoryJpa)
                .updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
        orderRepositoryImpl.updateByOrderStatusAndOrderId(OrderStatusEnum.CREATED, UUID.randomUUID());
        verify(orderRepositoryJpa).updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#updateByOrderStatusAndOrderId(OrderStatusEnum, UUID)}
     */
    @Test
    public void testUpdateByOrderStatusAndOrderId2() {
        doNothing().when(orderRepositoryJpa)
                .updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
        orderRepositoryImpl.updateByOrderStatusAndOrderId(OrderStatusEnum.IN_PROGRESS, UUID.randomUUID());
        verify(orderRepositoryJpa).updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#updateByOrderStatusAndOrderId(OrderStatusEnum, UUID)}
     */
    @Test
    public void testUpdateByOrderStatusAndOrderId3() {
        doNothing().when(orderRepositoryJpa)
                .updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
        orderRepositoryImpl.updateByOrderStatusAndOrderId(OrderStatusEnum.COMPLETED, UUID.randomUUID());
        verify(orderRepositoryJpa).updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#updateByOrderStatusAndOrderId(OrderStatusEnum, UUID)}
     */
    @Test
    public void testUpdateByOrderStatusAndOrderId4() {
        doNothing().when(orderRepositoryJpa)
                .updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
        orderRepositoryImpl.updateByOrderStatusAndOrderId(OrderStatusEnum.CLOSED, UUID.randomUUID());
        verify(orderRepositoryJpa).updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#updateByOrderStatusAndOrderId(OrderStatusEnum, UUID)}
     */
    @Test
    public void testUpdateByOrderStatusAndOrderId5() {
        doNothing().when(orderRepositoryJpa)
                .updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
        orderRepositoryImpl.updateByOrderStatusAndOrderId(OrderStatusEnum.CANCELED, UUID.randomUUID());
        verify(orderRepositoryJpa).updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link OrderRepositoryImpl#updateByOrderStatusAndOrderId(OrderStatusEnum, UUID)}
     */
    @Test
    public void testUpdateByOrderStatusAndOrderId6() {
        doThrow(new RuntimeException("foo")).when(orderRepositoryJpa)
                .updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
        assertThrows(RuntimeException.class,
                () -> orderRepositoryImpl.updateByOrderStatusAndOrderId(OrderStatusEnum.CREATED, UUID.randomUUID()));
        verify(orderRepositoryJpa).updateByOrderStatusAndOrderId(Mockito.<OrderStatusEnum>any(), Mockito.<UUID>any());
    }
}

