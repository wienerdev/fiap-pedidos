package br.com.fiap.api.pedidos.entrypoint.controller.dto.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;

import java.util.UUID;

import org.junit.Test;

public class UpdateOrderRequestTest {
    /**
     * Method under test: {@link UpdateOrderRequest#UpdateOrderRequest(UUID, OrderStatusEnum)}
     */
    @Test
    public void testConstructor() {
        UUID orderId = UUID.randomUUID();
        UpdateOrderRequest actualUpdateOrderRequest = new UpdateOrderRequest(orderId, OrderStatusEnum.CREATED);

        UUID orderIdResult = actualUpdateOrderRequest.orderId();
        assertSame(orderId, orderIdResult);
        assertEquals(OrderStatusEnum.CREATED, actualUpdateOrderRequest.orderStatus());
        assertEquals(4, orderIdResult.version());
        assertEquals(2, orderIdResult.variant());
    }

    /**
     * Method under test: {@link UpdateOrderRequest#orderId()}
     */
    @Test
    public void testOrderId() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       java.util.UUID
        //   See https://diff.blue/R027 to resolve this issue.

        UUID orderId = UUID.randomUUID();
        assertSame(orderId, (new UpdateOrderRequest(orderId, OrderStatusEnum.CREATED)).orderId());
    }

    /**
     * Method under test: {@link UpdateOrderRequest#orderStatus()}
     */
    @Test
    public void testOrderStatus() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       java.util.UUID
        //   See https://diff.blue/R027 to resolve this issue.

        assertEquals(OrderStatusEnum.CREATED,
                (new UpdateOrderRequest(UUID.randomUUID(), OrderStatusEnum.CREATED)).orderStatus());
        assertEquals(OrderStatusEnum.IN_PROGRESS,
                (new UpdateOrderRequest(UUID.randomUUID(), OrderStatusEnum.IN_PROGRESS)).orderStatus());
        assertEquals(OrderStatusEnum.COMPLETED,
                (new UpdateOrderRequest(UUID.randomUUID(), OrderStatusEnum.COMPLETED)).orderStatus());
        assertEquals(OrderStatusEnum.CLOSED,
                (new UpdateOrderRequest(UUID.randomUUID(), OrderStatusEnum.CLOSED)).orderStatus());
        assertEquals(OrderStatusEnum.CANCELED,
                (new UpdateOrderRequest(UUID.randomUUID(), OrderStatusEnum.CANCELED)).orderStatus());
    }
}

