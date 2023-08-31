package br.com.fiap.api.pedidos.core.usecase.impl.payments;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {MercadoPagoWebhookUseCaseImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MercadoPagoWebhookUseCaseImplTest {
    @Autowired
    private MercadoPagoWebhookUseCaseImpl mercadoPagoWebhookUseCaseImpl;

    /**
     * Method under test: {@link MercadoPagoWebhookUseCaseImpl#handleMercadoPagoNotification(Map)}
     */
    @Test
    public void testHandleMercadoPagoNotification() {
        assertEquals("Payload inválido", mercadoPagoWebhookUseCaseImpl.handleMercadoPagoNotification(new HashMap<>()));
    }

    /**
     * Method under test: {@link MercadoPagoWebhookUseCaseImpl#handleMercadoPagoNotification(Map)}
     */
    @Test
    public void testHandleMercadoPagoNotification2() {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("status", "42");
        assertEquals("Status desconhecido", mercadoPagoWebhookUseCaseImpl.handleMercadoPagoNotification(payload));
    }
}

