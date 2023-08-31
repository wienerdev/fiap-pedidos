package br.com.fiap.api.pedidos.entrypoint.controller.dto.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;

public class OrderResponseTest {
    /**
     * Method under test: {@link OrderResponse#fromEntityToRespons(Order)}
     */
    @Test
    public void testFromEntityToRespons() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       java.util.UUID
        //   See https://diff.blue/R027 to resolve this issue.

        OrderResponse actualFromEntityToResponsResult = OrderResponse.fromEntityToRespons(new Order());
        assertNull(actualFromEntityToResponsResult.active());
        assertNull(actualFromEntityToResponsResult.orderStatus());
        assertNull(actualFromEntityToResponsResult.orderProducts());
        assertNull(actualFromEntityToResponsResult.orderPrice());
        assertNull(actualFromEntityToResponsResult.orderId());
        assertNull(actualFromEntityToResponsResult.customerOrder());
        assertNull(actualFromEntityToResponsResult.client());
    }

    /**
     * Method under test: {@link OrderResponse#fromEntityToRespons(Order)}
     */
    @Test
    public void testFromEntityToRespons2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       java.util.UUID
        //   See https://diff.blue/R027 to resolve this issue.

        Order order = new Order();
        order.setClient(new Client());
        OrderResponse actualFromEntityToResponsResult = OrderResponse.fromEntityToRespons(order);
        assertNull(actualFromEntityToResponsResult.active());
        assertNull(actualFromEntityToResponsResult.orderStatus());
        assertNull(actualFromEntityToResponsResult.orderProducts());
        assertNull(actualFromEntityToResponsResult.orderPrice());
        assertNull(actualFromEntityToResponsResult.orderId());
        assertNull(actualFromEntityToResponsResult.customerOrder());
        ClientEntity clientResult = actualFromEntityToResponsResult.client();
        assertNull(clientResult.getClientCpf());
        assertNull(clientResult.getClientName());
        assertNull(clientResult.getClientEmail());
        assertNull(clientResult.getClientId());
    }

    /**
     * Method under test: {@link OrderResponse#fromEntityToRespons(Order)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testFromEntityToRespons3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       java.util.UUID
        //   See https://diff.blue/R027 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.Order.getOrderId()" because "order" is null
        //       at br.com.fiap.api.pedidos.entrypoint.controller.dto.response.OrderResponse.fromEntityToRespons(OrderResponse.java:19)
        //   See https://diff.blue/R013 to resolve this issue.

        OrderResponse.fromEntityToRespons(null);
    }

    /**
     * Method under test: {@link OrderResponse#fromEntityToRespons(Order)}
     */
    @Test
    public void testFromEntityToRespons4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       java.util.UUID
        //   See https://diff.blue/R027 to resolve this issue.

        Order order = mock(Order.class);
        when(order.getClient()).thenReturn(new Client());
        when(order.getOrderStatus()).thenReturn(OrderStatusEnum.CREATED);
        when(order.getActive()).thenReturn(true);
        when(order.getOrderPrice()).thenReturn(10.0d);
        when(order.getCustomerOrder()).thenReturn("Customer Order");
        when(order.getOrderProducts()).thenReturn(new ArrayList<>());
        UUID randomUUIDResult = UUID.randomUUID();
        when(order.getOrderId()).thenReturn(randomUUIDResult);
        OrderResponse actualFromEntityToResponsResult = OrderResponse.fromEntityToRespons(order);
        assertTrue(actualFromEntityToResponsResult.active());
        assertEquals(OrderStatusEnum.CREATED, actualFromEntityToResponsResult.orderStatus());
        assertTrue(actualFromEntityToResponsResult.orderProducts().isEmpty());
        assertEquals(10.0d, actualFromEntityToResponsResult.orderPrice().doubleValue(), 0.0);
        assertSame(randomUUIDResult, actualFromEntityToResponsResult.orderId());
        assertEquals("Customer Order", actualFromEntityToResponsResult.customerOrder());
        ClientEntity clientResult = actualFromEntityToResponsResult.client();
        assertNull(clientResult.getClientName());
        assertNull(clientResult.getClientCpf());
        assertNull(clientResult.getClientId());
        assertNull(clientResult.getClientEmail());
        verify(order, atLeast(1)).getClient();
        verify(order).getOrderStatus();
        verify(order).getActive();
        verify(order).getOrderPrice();
        verify(order).getCustomerOrder();
        verify(order).getOrderProducts();
        verify(order).getOrderId();
    }
}

