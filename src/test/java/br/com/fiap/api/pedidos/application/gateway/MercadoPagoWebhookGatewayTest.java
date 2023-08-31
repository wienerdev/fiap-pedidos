package br.com.fiap.api.pedidos.application.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.usecase.impl.payments.MercadoPagoWebhookUseCaseImpl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {MercadoPagoWebhookGateway.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MercadoPagoWebhookGatewayTest {
    @Autowired
    private MercadoPagoWebhookGateway mercadoPagoWebhookGateway;

    @MockBean
    private MercadoPagoWebhookUseCaseImpl mercadoPagoWebhookUseCaseImpl;

    /**
     * Method under test: {@link MercadoPagoWebhookGateway#handleMercadoPagoNotification(Map)}
     */
    @Test
    public void testHandleMercadoPagoNotification() {
        when(mercadoPagoWebhookUseCaseImpl.handleMercadoPagoNotification(Mockito.<Map<String, Object>>any()))
                .thenReturn("Handle Mercado Pago Notification");
        ResponseEntity<String> actualHandleMercadoPagoNotificationResult = mercadoPagoWebhookGateway
                .handleMercadoPagoNotification(new HashMap<>());
        assertEquals("Handle Mercado Pago Notification", actualHandleMercadoPagoNotificationResult.getBody());
        assertEquals(200, actualHandleMercadoPagoNotificationResult.getStatusCodeValue());
        assertTrue(actualHandleMercadoPagoNotificationResult.getHeaders().isEmpty());
        verify(mercadoPagoWebhookUseCaseImpl).handleMercadoPagoNotification(Mockito.<Map<String, Object>>any());
    }
}

