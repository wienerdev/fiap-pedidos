package br.com.fiap.api.pedidos.dataprovider.repository.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {OrderEntity.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderEntityTest {
    @Autowired
    private OrderEntity orderEntity;

    /**
     * Method under test: {@link OrderEntity#OrderEntity()}
     */
    @Test
    public void testConstructor() {
        OrderEntity actualOrderEntity = new OrderEntity();
        assertNull(actualOrderEntity.getActive());
        assertNull(actualOrderEntity.getOrderStatus());
        assertNull(actualOrderEntity.getOrderProducts());
        assertNull(actualOrderEntity.getOrderProductIds());
        assertNull(actualOrderEntity.getOrderPrice());
        assertNull(actualOrderEntity.getOrderId());
        assertNull(actualOrderEntity.getCustomerOrder());
        assertNull(actualOrderEntity.getClientEntity());
    }

    /**
     * Method under test: {@link OrderEntity#OrderEntity(Order)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testConstructor2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Collection.stream()" because "that" is null
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity.<init>(OrderEntity.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        new OrderEntity(new Order());
    }

    /**
     * Method under test: {@link OrderEntity#OrderEntity(Order)}
     */
    @Test
    public void testConstructor3() {
        Order order = new Order();
        order.setOrderProducts(new ArrayList<>());
        OrderEntity actualOrderEntity = new OrderEntity(order);
        assertNull(actualOrderEntity.getActive());
        assertNull(actualOrderEntity.getOrderStatus());
        assertNull(actualOrderEntity.getClientEntity());
        assertNull(actualOrderEntity.getOrderId());
        assertNull(actualOrderEntity.getOrderPrice());
        assertNull(actualOrderEntity.getCustomerOrder());
        assertTrue(actualOrderEntity.getOrderProducts().isEmpty());
    }

    /**
     * Method under test: {@link OrderEntity#OrderEntity(Order)}
     */
    @Test
    public void testConstructor4() {
        UUID orderId = UUID.randomUUID();
        ArrayList<Product> orderProducts = new ArrayList<>();
        ArrayList<UUID> orderProductIds = new ArrayList<>();
        Order order = new Order(orderId, "Customer Order", true, OrderStatusEnum.CREATED, orderProducts, orderProductIds,
                10.0d, new Client());

        OrderEntity actualOrderEntity = new OrderEntity(order);
        assertTrue(actualOrderEntity.getActive());
        assertEquals(order, actualOrderEntity.toOrder());
        assertEquals(OrderStatusEnum.CREATED, actualOrderEntity.getOrderStatus());
        assertEquals(10.0d, actualOrderEntity.getOrderPrice().doubleValue(), 0.0);
        assertEquals("Customer Order", actualOrderEntity.getCustomerOrder());
        assertTrue(actualOrderEntity.getOrderProducts().isEmpty());
        ClientEntity clientEntity = actualOrderEntity.getClientEntity();
        assertNull(clientEntity.getClientName());
        assertNull(clientEntity.getClientId());
        assertNull(clientEntity.getClientEmail());
        assertNull(clientEntity.getClientCpf());
    }

    /**
     * Method under test: {@link OrderEntity#OrderEntity(Order)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testConstructor5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.Order.getOrderId()" because "order" is null
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity.<init>(OrderEntity.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        new OrderEntity(null);
    }

    /**
     * Method under test: {@link OrderEntity#OrderEntity(Order)}
     */
    @Test
    public void testConstructor6() {
        Order order = mock(Order.class);
        when(order.getClient()).thenReturn(new Client());
        when(order.getOrderStatus()).thenReturn(OrderStatusEnum.CREATED);
        when(order.getActive()).thenReturn(true);
        when(order.getOrderPrice()).thenReturn(10.0d);
        when(order.getCustomerOrder()).thenReturn("Customer Order");
        when(order.getOrderProducts()).thenReturn(new ArrayList<>());
        UUID randomUUIDResult = UUID.randomUUID();
        when(order.getOrderId()).thenReturn(randomUUIDResult);
        OrderEntity actualOrderEntity = new OrderEntity(order);
        assertTrue(actualOrderEntity.getActive());
        assertEquals(OrderStatusEnum.CREATED, actualOrderEntity.getOrderStatus());
        assertSame(randomUUIDResult, actualOrderEntity.getOrderId());
        assertEquals(10.0d, actualOrderEntity.getOrderPrice().doubleValue(), 0.0);
        assertEquals("Customer Order", actualOrderEntity.getCustomerOrder());
        assertTrue(actualOrderEntity.getOrderProducts().isEmpty());
        ClientEntity clientEntity = actualOrderEntity.getClientEntity();
        assertNull(clientEntity.getClientName());
        assertNull(clientEntity.getClientId());
        assertNull(clientEntity.getClientEmail());
        assertNull(clientEntity.getClientCpf());
        verify(order, atLeast(1)).getClient();
        verify(order).getOrderStatus();
        verify(order).getActive();
        verify(order).getOrderPrice();
        verify(order).getCustomerOrder();
        verify(order).getOrderProducts();
        verify(order).getOrderId();
    }

    /**
     * Method under test: {@link OrderEntity#OrderEntity(Order)}
     */
    @Test
    public void testConstructor7() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        Order order = mock(Order.class);
        when(order.getClient()).thenReturn(new Client());
        when(order.getOrderStatus()).thenReturn(OrderStatusEnum.CREATED);
        when(order.getActive()).thenReturn(true);
        when(order.getOrderPrice()).thenReturn(10.0d);
        when(order.getCustomerOrder()).thenReturn("Customer Order");
        when(order.getOrderProducts()).thenReturn(productList);
        UUID randomUUIDResult = UUID.randomUUID();
        when(order.getOrderId()).thenReturn(randomUUIDResult);
        OrderEntity actualOrderEntity = new OrderEntity(order);
        assertTrue(actualOrderEntity.getActive());
        assertEquals(OrderStatusEnum.CREATED, actualOrderEntity.getOrderStatus());
        assertSame(randomUUIDResult, actualOrderEntity.getOrderId());
        assertEquals(10.0d, actualOrderEntity.getOrderPrice().doubleValue(), 0.0);
        assertEquals("Customer Order", actualOrderEntity.getCustomerOrder());
        assertEquals(1, actualOrderEntity.getOrderProducts().size());
        ClientEntity clientEntity = actualOrderEntity.getClientEntity();
        assertNull(clientEntity.getClientName());
        assertNull(clientEntity.getClientId());
        assertNull(clientEntity.getClientEmail());
        assertNull(clientEntity.getClientCpf());
        verify(order, atLeast(1)).getClient();
        verify(order).getOrderStatus();
        verify(order).getActive();
        verify(order).getOrderPrice();
        verify(order).getCustomerOrder();
        verify(order).getOrderProducts();
        verify(order).getOrderId();
    }

    /**
     * Method under test: {@link OrderEntity#OrderEntity(Order)}
     */
    @Test
    public void testConstructor8() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        Order order = mock(Order.class);
        when(order.getClient()).thenReturn(new Client());
        when(order.getOrderStatus()).thenReturn(OrderStatusEnum.CREATED);
        when(order.getActive()).thenReturn(true);
        when(order.getOrderPrice()).thenReturn(10.0d);
        when(order.getCustomerOrder()).thenReturn("Customer Order");
        when(order.getOrderProducts()).thenReturn(productList);
        UUID randomUUIDResult = UUID.randomUUID();
        when(order.getOrderId()).thenReturn(randomUUIDResult);
        OrderEntity actualOrderEntity = new OrderEntity(order);
        assertTrue(actualOrderEntity.getActive());
        assertEquals(OrderStatusEnum.CREATED, actualOrderEntity.getOrderStatus());
        assertSame(randomUUIDResult, actualOrderEntity.getOrderId());
        assertEquals(10.0d, actualOrderEntity.getOrderPrice().doubleValue(), 0.0);
        assertEquals("Customer Order", actualOrderEntity.getCustomerOrder());
        assertEquals(2, actualOrderEntity.getOrderProducts().size());
        ClientEntity clientEntity = actualOrderEntity.getClientEntity();
        assertNull(clientEntity.getClientName());
        assertNull(clientEntity.getClientId());
        assertNull(clientEntity.getClientEmail());
        assertNull(clientEntity.getClientCpf());
        verify(order, atLeast(1)).getClient();
        verify(order).getOrderStatus();
        verify(order).getActive();
        verify(order).getOrderPrice();
        verify(order).getCustomerOrder();
        verify(order).getOrderProducts();
        verify(order).getOrderId();
    }

    /**
     * Method under test: {@link OrderEntity#OrderEntity(Order)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testConstructor9() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.Product.getProductId()" because "product" is null
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity.lambda$new$0(OrderEntity.java:46)
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
        //       at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
        //       at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
        //       at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
        //       at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity.<init>(OrderEntity.java:48)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(null);
        Order order = mock(Order.class);
        when(order.getClient()).thenReturn(new Client());
        when(order.getOrderStatus()).thenReturn(OrderStatusEnum.CREATED);
        when(order.getActive()).thenReturn(true);
        when(order.getOrderPrice()).thenReturn(10.0d);
        when(order.getCustomerOrder()).thenReturn("Customer Order");
        when(order.getOrderProducts()).thenReturn(productList);
        when(order.getOrderId()).thenReturn(UUID.randomUUID());
        new OrderEntity(order);
    }

    /**
     * Method under test: {@link OrderEntity#toOrder()}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testToOrder() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Collection.stream()" because "that" is null
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity.toProductList(OrderEntity.java:64)
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity.toOrder(OrderEntity.java:58)
        //   See https://diff.blue/R013 to resolve this issue.

        (new OrderEntity()).toOrder();
    }

    /**
     * Method under test: {@link OrderEntity#toOrder()}
     */
    @Test
    public void testToOrder2() {
        OrderEntity orderEntity2 = new OrderEntity();
        ArrayList<ProductEntity> orderProducts = new ArrayList<>();
        orderEntity2.setOrderProducts(orderProducts);
        Order actualToOrderResult = orderEntity2.toOrder();
        assertNull(actualToOrderResult.getActive());
        assertNull(actualToOrderResult.getOrderStatus());
        assertEquals(orderProducts, actualToOrderResult.getOrderProducts());
        assertNull(actualToOrderResult.getOrderProductIds());
        assertNull(actualToOrderResult.getOrderPrice());
        assertNull(actualToOrderResult.getOrderId());
        assertNull(actualToOrderResult.getCustomerOrder());
        assertNull(actualToOrderResult.getClient());
    }

    /**
     * Method under test: {@link OrderEntity#toOrder()}
     */
    @Test
    public void testToOrder3() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        UUID clientId = UUID.randomUUID();
        clientEntity.setClientId(clientId);
        clientEntity.setClientName("Dr Jane Doe");

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setClientEntity(clientEntity);
        ArrayList<ProductEntity> orderProducts = new ArrayList<>();
        orderEntity2.setOrderProducts(orderProducts);
        Order actualToOrderResult = orderEntity2.toOrder();
        assertNull(actualToOrderResult.getActive());
        assertNull(actualToOrderResult.getOrderStatus());
        assertEquals(orderProducts, actualToOrderResult.getOrderProducts());
        assertNull(actualToOrderResult.getOrderProductIds());
        assertNull(actualToOrderResult.getOrderPrice());
        assertNull(actualToOrderResult.getOrderId());
        assertNull(actualToOrderResult.getCustomerOrder());
        Client client = actualToOrderResult.getClient();
        assertEquals("Client Cpf", client.getClientCpf());
        assertEquals("Dr Jane Doe", client.getClientName());
        assertSame(clientId, client.getClientId());
        assertEquals("jane.doe@example.org", client.getClientEmail());
    }

    /**
     * Method under test: {@link OrderEntity#toOrder()}
     */
    @Test
    public void testToOrder4() {
        ClientEntity clientEntity = mock(ClientEntity.class);
        Client client = new Client();
        when(clientEntity.toClient()).thenReturn(client);
        doNothing().when(clientEntity).setClientCpf(Mockito.<String>any());
        doNothing().when(clientEntity).setClientEmail(Mockito.<String>any());
        doNothing().when(clientEntity).setClientId(Mockito.<UUID>any());
        doNothing().when(clientEntity).setClientName(Mockito.<String>any());
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        clientEntity.setClientId(UUID.randomUUID());
        clientEntity.setClientName("Dr Jane Doe");

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setClientEntity(clientEntity);
        ArrayList<ProductEntity> orderProducts = new ArrayList<>();
        orderEntity2.setOrderProducts(orderProducts);
        Order actualToOrderResult = orderEntity2.toOrder();
        assertNull(actualToOrderResult.getActive());
        assertNull(actualToOrderResult.getOrderStatus());
        assertEquals(orderProducts, actualToOrderResult.getOrderProducts());
        assertNull(actualToOrderResult.getOrderProductIds());
        assertNull(actualToOrderResult.getOrderPrice());
        assertNull(actualToOrderResult.getOrderId());
        assertNull(actualToOrderResult.getCustomerOrder());
        assertSame(client, actualToOrderResult.getClient());
        verify(clientEntity).toClient();
        verify(clientEntity).setClientCpf(Mockito.<String>any());
        verify(clientEntity).setClientEmail(Mockito.<String>any());
        verify(clientEntity).setClientId(Mockito.<UUID>any());
        verify(clientEntity).setClientName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OrderEntity#toProductList(List)}
     */
    @Test
    public void testToProductList() {
        List<Product> actualToProductListResult = OrderEntity.toProductList(new ArrayList<>());
        assertTrue(actualToProductListResult.isEmpty());
    }

    /**
     * Method under test: {@link OrderEntity#toProductList(List)}
     */
    @Test
    public void testToProductList2() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        BigDecimal price = BigDecimal.valueOf(1L);
        productEntity.setPrice(price);
        productEntity.setProductDesc("Product Desc");
        UUID productId = UUID.randomUUID();
        productEntity.setProductId(productId);
        productEntity.setProductName("Product Name");

        ArrayList<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(productEntity);
        List<Product> actualToProductListResult = OrderEntity.toProductList(productEntities);
        assertEquals(1, actualToProductListResult.size());
        Product getResult = actualToProductListResult.get(0);
        assertEquals("Category", getResult.getCategory());
        assertEquals("Product Name", getResult.getProductName());
        assertSame(productId, getResult.getProductId());
        assertEquals("Product Desc", getResult.getProductDesc());
        BigDecimal expectedPrice = price.ONE;
        BigDecimal price2 = getResult.getPrice();
        assertSame(expectedPrice, price2);
        assertEquals("1", price2.toString());
    }

    /**
     * Method under test: {@link OrderEntity#toProductList(List)}
     */
    @Test
    public void testToProductList3() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        BigDecimal price = BigDecimal.valueOf(1L);
        productEntity.setPrice(price);
        productEntity.setProductDesc("Product Desc");
        UUID productId = UUID.randomUUID();
        productEntity.setProductId(productId);
        productEntity.setProductName("Product Name");

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setCategory("42");
        productEntity2.setPrice(BigDecimal.valueOf(1L));
        productEntity2.setProductDesc("42");
        UUID productId2 = UUID.randomUUID();
        productEntity2.setProductId(productId2);
        productEntity2.setProductName("42");

        ArrayList<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(productEntity2);
        productEntities.add(productEntity);
        List<Product> actualToProductListResult = OrderEntity.toProductList(productEntities);
        assertEquals(2, actualToProductListResult.size());
        Product getResult = actualToProductListResult.get(0);
        assertEquals("42", getResult.getProductName());
        Product getResult2 = actualToProductListResult.get(1);
        assertEquals("Product Name", getResult2.getProductName());
        assertSame(productId, getResult2.getProductId());
        assertEquals("Product Desc", getResult2.getProductDesc());
        assertEquals("42", getResult.getCategory());
        assertEquals("42", getResult.getProductDesc());
        assertSame(productId2, getResult.getProductId());
        BigDecimal expectedPrice = price.ONE;
        BigDecimal price2 = getResult.getPrice();
        assertSame(expectedPrice, price2);
        assertEquals("Category", getResult2.getCategory());
        assertEquals("1", getResult2.getPrice().toString());
        assertEquals("1", price2.toString());
    }

    /**
     * Method under test: {@link OrderEntity#toProductList(List)}
     */
    @Test
    public void testToProductList4() {
        ProductEntity productEntity = mock(ProductEntity.class);
        when(productEntity.toProduct()).thenReturn(new Product());
        doNothing().when(productEntity).setCategory(Mockito.<String>any());
        doNothing().when(productEntity).setPrice(Mockito.<BigDecimal>any());
        doNothing().when(productEntity).setProductDesc(Mockito.<String>any());
        doNothing().when(productEntity).setProductId(Mockito.<UUID>any());
        doNothing().when(productEntity).setProductName(Mockito.<String>any());
        productEntity.setCategory("Category");
        productEntity.setPrice(BigDecimal.valueOf(1L));
        productEntity.setProductDesc("Product Desc");
        productEntity.setProductId(UUID.randomUUID());
        productEntity.setProductName("Product Name");

        ArrayList<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(productEntity);
        List<Product> actualToProductListResult = OrderEntity.toProductList(productEntities);
        assertEquals(1, actualToProductListResult.size());
        verify(productEntity).toProduct();
        verify(productEntity).setCategory(Mockito.<String>any());
        verify(productEntity).setPrice(Mockito.<BigDecimal>any());
        verify(productEntity).setProductDesc(Mockito.<String>any());
        verify(productEntity).setProductId(Mockito.<UUID>any());
        verify(productEntity).setProductName(Mockito.<String>any());
    }
}

