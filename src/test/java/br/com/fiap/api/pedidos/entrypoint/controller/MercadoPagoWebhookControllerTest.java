package br.com.fiap.api.pedidos.entrypoint.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.application.gateway.MercadoPagoWebhookGateway;
import br.com.fiap.api.pedidos.core.usecase.impl.payments.MercadoPagoWebhookUseCaseImpl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

public class MercadoPagoWebhookControllerTest {
    /**
     * Method under test: {@link MercadoPagoWebhookController#handleMercadoPagoNotification(Map)}
     */
    @Test
    public void testHandleMercadoPagoNotification() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R034 Diffblue Cover can't complete test.
        //   Diffblue Cover was unable to complete the test.
        //   Try to increase the number of fuzzing iterations if non-default
        //   value is used.
        //   See https://diff.blue/R034 for further troubleshooting of this issue.

        MercadoPagoWebhookController mercadoPagoWebhookController = new MercadoPagoWebhookController(
                new MercadoPagoWebhookGateway(new MercadoPagoWebhookUseCaseImpl()));
        ResponseEntity<String> actualHandleMercadoPagoNotificationResult = mercadoPagoWebhookController
                .handleMercadoPagoNotification(new HashMap<>());
        assertEquals("Payload inválido", actualHandleMercadoPagoNotificationResult.getBody());
        assertEquals(200, actualHandleMercadoPagoNotificationResult.getStatusCodeValue());
        assertTrue(actualHandleMercadoPagoNotificationResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link MercadoPagoWebhookController#handleMercadoPagoNotification(Map)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testHandleMercadoPagoNotification2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R034 Diffblue Cover can't complete test.
        //   Diffblue Cover was unable to complete the test.
        //   Try to increase the number of fuzzing iterations if non-default
        //   value is used.
        //   See https://diff.blue/R034 for further troubleshooting of this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.usecase.impl.payments.MercadoPagoWebhookUseCaseImpl.handleMercadoPagoNotification(java.util.Map)" because "this.mercadoPagoWebhookUseCase" is null
        //       at br.com.fiap.api.pedidos.application.gateway.MercadoPagoWebhookGateway.handleMercadoPagoNotification(MercadoPagoWebhookGateway.java:23)
        //       at br.com.fiap.api.pedidos.entrypoint.controller.MercadoPagoWebhookController.handleMercadoPagoNotification(MercadoPagoWebhookController.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        MercadoPagoWebhookController mercadoPagoWebhookController = new MercadoPagoWebhookController(
                new MercadoPagoWebhookGateway(null));
        mercadoPagoWebhookController.handleMercadoPagoNotification(new HashMap<>());
    }

    /**
     * Method under test: {@link MercadoPagoWebhookController#handleMercadoPagoNotification(Map)}
     */
    @Test
    public void testHandleMercadoPagoNotification3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R034 Diffblue Cover can't complete test.
        //   Diffblue Cover was unable to complete the test.
        //   Try to increase the number of fuzzing iterations if non-default
        //   value is used.
        //   See https://diff.blue/R034 for further troubleshooting of this issue.

        MercadoPagoWebhookUseCaseImpl mercadoPagoWebhookUseCase = mock(MercadoPagoWebhookUseCaseImpl.class);
        when(mercadoPagoWebhookUseCase.handleMercadoPagoNotification(Mockito.<Map<String, Object>>any()))
                .thenReturn("Handle Mercado Pago Notification");
        MercadoPagoWebhookController mercadoPagoWebhookController = new MercadoPagoWebhookController(
                new MercadoPagoWebhookGateway(mercadoPagoWebhookUseCase));
        ResponseEntity<String> actualHandleMercadoPagoNotificationResult = mercadoPagoWebhookController
                .handleMercadoPagoNotification(new HashMap<>());
        assertEquals("Handle Mercado Pago Notification", actualHandleMercadoPagoNotificationResult.getBody());
        assertEquals(200, actualHandleMercadoPagoNotificationResult.getStatusCodeValue());
        assertTrue(actualHandleMercadoPagoNotificationResult.getHeaders().isEmpty());
        verify(mercadoPagoWebhookUseCase).handleMercadoPagoNotification(Mockito.<Map<String, Object>>any());
    }

    /**
     * Method under test: {@link MercadoPagoWebhookController#handleMercadoPagoNotification(Map)}
     */
    @Test
    public void testHandleMercadoPagoNotification4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R034 Diffblue Cover can't complete test.
        //   Diffblue Cover was unable to complete the test.
        //   Try to increase the number of fuzzing iterations if non-default
        //   value is used.
        //   See https://diff.blue/R034 for further troubleshooting of this issue.

        MercadoPagoWebhookGateway mercadoPagoWebhookGateway = mock(MercadoPagoWebhookGateway.class);
        when(mercadoPagoWebhookGateway.handleMercadoPagoNotification(Mockito.<Map<String, Object>>any()))
                .thenReturn(null);
        MercadoPagoWebhookController mercadoPagoWebhookController = new MercadoPagoWebhookController(
                mercadoPagoWebhookGateway);
        assertNull(mercadoPagoWebhookController.handleMercadoPagoNotification(new HashMap<>()));
        verify(mercadoPagoWebhookGateway).handleMercadoPagoNotification(Mockito.<Map<String, Object>>any());
    }

    /**
     * Method under test: {@link MercadoPagoWebhookController#handleMercadoPagoNotification(Map)}
     */
    @Test
    public void testHandleMercadoPagoNotification5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R034 Diffblue Cover can't complete test.
        //   Diffblue Cover was unable to complete the test.
        //   Try to increase the number of fuzzing iterations if non-default
        //   value is used.
        //   See https://diff.blue/R034 for further troubleshooting of this issue.

        MercadoPagoWebhookController mercadoPagoWebhookController = new MercadoPagoWebhookController(
                new MercadoPagoWebhookGateway(new MercadoPagoWebhookUseCaseImpl()));

        HashMap<String, Object> payload = new HashMap<>();
        payload.put("status", "42");
        ResponseEntity<String> actualHandleMercadoPagoNotificationResult = mercadoPagoWebhookController
                .handleMercadoPagoNotification(payload);
        assertEquals("Status desconhecido", actualHandleMercadoPagoNotificationResult.getBody());
        assertEquals(200, actualHandleMercadoPagoNotificationResult.getStatusCodeValue());
        assertTrue(actualHandleMercadoPagoNotificationResult.getHeaders().isEmpty());
    }
}

