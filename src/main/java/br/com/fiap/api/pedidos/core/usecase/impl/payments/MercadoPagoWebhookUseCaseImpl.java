package br.com.fiap.api.pedidos.core.usecase.impl.payments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MercadoPagoWebhookUseCaseImpl implements br.com.fiap.api.pedidos.core.usecase.MercadoPagoWebhookUseCase {

    private static final Logger logger = LoggerFactory.getLogger(MercadoPagoWebhookUseCaseImpl.class);

    @Override
    public String handleMercadoPagoNotification(Map<String, Object> payload) {
        String action = (String) payload.get("action");

        if (action == null) {
            logger.warn("Webhook recebido sem ação");
            return "Payload inválido";
        }

        if (action.equalsIgnoreCase("test.created")) {
            return handleTestCreated(payload);
        }
        logger.warn("Ação desconhecida: {}", action);
        return "Ação desconhecida";
    }

    private String handleTestCreated(Map<String, Object> payload) {
        logger.info("Ação de teste criada: {}", payload);
        return String.format("Ação de teste criada: {}", payload);
    }

}
