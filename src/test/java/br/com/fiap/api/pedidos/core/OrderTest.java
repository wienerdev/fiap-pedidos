package br.com.fiap.api.pedidos.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

public class OrderTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Order#Order()}
     *   <li>{@link Order#setActive(Boolean)}
     *   <li>{@link Order#setClient(Client)}
     *   <li>{@link Order#setCustomerOrder(String)}
     *   <li>{@link Order#setOrderId(UUID)}
     *   <li>{@link Order#setOrderPrice(Double)}
     *   <li>{@link Order#setOrderProductIds(List)}
     *   <li>{@link Order#setOrderProducts(List)}
     *   <li>{@link Order#setOrderStatus(OrderStatusEnum)}
     *   <li>{@link Order#getActive()}
     *   <li>{@link Order#getClient()}
     *   <li>{@link Order#getCustomerOrder()}
     *   <li>{@link Order#getOrderId()}
     *   <li>{@link Order#getOrderPrice()}
     *   <li>{@link Order#getOrderProductIds()}
     *   <li>{@link Order#getOrderProducts()}
     *   <li>{@link Order#getOrderStatus()}
     * </ul>
     */
    @Test
    public void testConstructor() {
        Order actualOrder = new Order();
        actualOrder.setActive(true);
        Client client = new Client();
        actualOrder.setClient(client);
        actualOrder.setCustomerOrder("Customer Order");
        UUID orderId = UUID.randomUUID();
        actualOrder.setOrderId(orderId);
        actualOrder.setOrderPrice(10.0d);
        ArrayList<UUID> orderProductIds = new ArrayList<>();
        actualOrder.setOrderProductIds(orderProductIds);
        ArrayList<Product> orderProducts = new ArrayList<>();
        actualOrder.setOrderProducts(orderProducts);
        actualOrder.setOrderStatus(OrderStatusEnum.CREATED);
        Boolean actualActive = actualOrder.getActive();
        Client actualClient = actualOrder.getClient();
        String actualCustomerOrder = actualOrder.getCustomerOrder();
        UUID actualOrderId = actualOrder.getOrderId();
        Double actualOrderPrice = actualOrder.getOrderPrice();
        List<UUID> actualOrderProductIds = actualOrder.getOrderProductIds();
        List<Product> actualOrderProducts = actualOrder.getOrderProducts();
        OrderStatusEnum actualOrderStatus = actualOrder.getOrderStatus();
        assertTrue(actualActive);
        assertSame(client, actualClient);
        assertEquals("Customer Order", actualCustomerOrder);
        assertSame(orderId, actualOrderId);
        assertEquals(10.0d, actualOrderPrice.doubleValue(), 0.0);
        assertSame(orderProductIds, actualOrderProductIds);
        assertEquals(orderProducts, actualOrderProductIds);
        assertSame(orderProducts, actualOrderProducts);
        assertEquals(actualOrderProductIds, actualOrderProducts);
        assertEquals(OrderStatusEnum.CREATED, actualOrderStatus);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Order#Order(UUID, String, Boolean, OrderStatusEnum, List, List, Double, Client)}
     *   <li>{@link Order#setActive(Boolean)}
     *   <li>{@link Order#setClient(Client)}
     *   <li>{@link Order#setCustomerOrder(String)}
     *   <li>{@link Order#setOrderId(UUID)}
     *   <li>{@link Order#setOrderPrice(Double)}
     *   <li>{@link Order#setOrderProductIds(List)}
     *   <li>{@link Order#setOrderProducts(List)}
     *   <li>{@link Order#setOrderStatus(OrderStatusEnum)}
     *   <li>{@link Order#getActive()}
     *   <li>{@link Order#getClient()}
     *   <li>{@link Order#getCustomerOrder()}
     *   <li>{@link Order#getOrderId()}
     *   <li>{@link Order#getOrderPrice()}
     *   <li>{@link Order#getOrderProductIds()}
     *   <li>{@link Order#getOrderProducts()}
     *   <li>{@link Order#getOrderStatus()}
     * </ul>
     */
    @Test
    public void testConstructor2() {
        UUID orderId = UUID.randomUUID();
        ArrayList<Product> orderProducts = new ArrayList<>();
        ArrayList<UUID> orderProductIds = new ArrayList<>();
        Order actualOrder = new Order(orderId, "Customer Order", true, OrderStatusEnum.CREATED, orderProducts,
                orderProductIds, 10.0d, new Client());
        actualOrder.setActive(true);
        Client client = new Client();
        actualOrder.setClient(client);
        actualOrder.setCustomerOrder("Customer Order");
        UUID orderId2 = UUID.randomUUID();
        actualOrder.setOrderId(orderId2);
        actualOrder.setOrderPrice(10.0d);
        ArrayList<UUID> orderProductIds2 = new ArrayList<>();
        actualOrder.setOrderProductIds(orderProductIds2);
        ArrayList<Product> orderProducts2 = new ArrayList<>();
        actualOrder.setOrderProducts(orderProducts2);
        actualOrder.setOrderStatus(OrderStatusEnum.CREATED);
        Boolean actualActive = actualOrder.getActive();
        Client actualClient = actualOrder.getClient();
        String actualCustomerOrder = actualOrder.getCustomerOrder();
        UUID actualOrderId = actualOrder.getOrderId();
        Double actualOrderPrice = actualOrder.getOrderPrice();
        List<UUID> actualOrderProductIds = actualOrder.getOrderProductIds();
        List<Product> actualOrderProducts = actualOrder.getOrderProducts();
        OrderStatusEnum actualOrderStatus = actualOrder.getOrderStatus();
        assertTrue(actualActive);
        assertSame(client, actualClient);
        assertEquals("Customer Order", actualCustomerOrder);
        assertSame(orderId2, actualOrderId);
        assertEquals(10.0d, actualOrderPrice.doubleValue(), 0.0);
        assertSame(orderProductIds2, actualOrderProductIds);
        assertEquals(orderProducts, actualOrderProductIds);
        assertEquals(orderProductIds, actualOrderProductIds);
        assertEquals(orderProducts2, actualOrderProductIds);
        assertSame(orderProducts2, actualOrderProducts);
        assertEquals(orderProducts, actualOrderProducts);
        assertEquals(orderProductIds, actualOrderProducts);
        assertEquals(actualOrderProductIds, actualOrderProducts);
        assertEquals(OrderStatusEnum.CREATED, actualOrderStatus);
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    public void testEquals() {
        assertNotEquals(new Order(), null);
        assertNotEquals(new Order(), "Different type to Order");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Order#equals(Object)}
     *   <li>{@link Order#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals2() {
        Order order = new Order();
        assertEquals(order, order);
        int expectedHashCodeResult = order.hashCode();
        assertEquals(expectedHashCodeResult, order.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Order#equals(Object)}
     *   <li>{@link Order#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals3() {
        Order order = new Order();
        Order order2 = new Order();
        assertEquals(order, order2);
        int expectedHashCodeResult = order.hashCode();
        assertEquals(expectedHashCodeResult, order2.hashCode());
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    public void testEquals4() {
        UUID orderId = UUID.randomUUID();
        ArrayList<Product> orderProducts = new ArrayList<>();
        ArrayList<UUID> orderProductIds = new ArrayList<>();
        Order order = new Order(orderId, "Customer Order", true, OrderStatusEnum.CREATED, orderProducts, orderProductIds,
                10.0d, new Client());
        assertNotEquals(order, new Order());
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    public void testEquals5() {
        Order order = new Order();
        order.setCustomerOrder("Customer Order");
        assertNotEquals(order, new Order());
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    public void testEquals6() {
        Order order = new Order();
        order.setActive(true);
        assertNotEquals(order, new Order());
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    public void testEquals7() {
        Order order = new Order();
        order.setOrderStatus(OrderStatusEnum.CREATED);
        assertNotEquals(order, new Order());
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    public void testEquals8() {
        Order order = new Order();
        order.setOrderProducts(new ArrayList<>());
        assertNotEquals(order, new Order());
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    public void testEquals9() {
        Order order = new Order();
        order.setOrderPrice(10.0d);
        assertNotEquals(order, new Order());
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    public void testEquals10() {
        UUID orderId = UUID.randomUUID();
        ArrayList<Product> orderProducts = new ArrayList<>();
        Order order = new Order(orderId, "Customer Order", true, OrderStatusEnum.CREATED, orderProducts,
                new ArrayList<>(), 10.0d, mock(Client.class));
        assertNotEquals(order, new Order());
    }
}

