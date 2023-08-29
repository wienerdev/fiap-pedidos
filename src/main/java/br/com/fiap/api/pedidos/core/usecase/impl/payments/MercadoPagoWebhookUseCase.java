package br.com.fiap.api.pedidos.core.usecase.impl.payments;

import br.com.fiap.api.pedidos.entrypoint.controller.MercadoPagoWebhookController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MercadoPagoWebhookUseCase implements br.com.fiap.api.pedidos.core.usecase.MercadoPagoWebhookUseCase {

    private static final Logger logger = LoggerFactory.getLogger(MercadoPagoWebhookController.class);

    @Override
    public String handleMercadoPagoNotification(Map<String, Object> payload) {
        String status = (String) payload.get("status");

        if (status == null) {
            logger.warn("Webhook recebido sem status");
            return "Payload inválido";
        }

        switch (status.toLowerCase()) {
            case "approved":
                return handleApprovedPayment(payload);
            case "rejected":
                return handleRejectedPayment(payload);
            default:
                logger.warn("Status de pagamento desconhecido: {}", status);
                return "Status desconhecido";
        }
    }

    private String handleApprovedPayment(Map<String, Object> payload) {
        logger.info("Pagamento aprovado: {}", payload);
        return String.format("Pagamento aprovado: {}", payload);
    }

    private String handleRejectedPayment(Map<String, Object> payload) {
        logger.warn("Pagamento recusado: {}", payload);
        return String.format("Pagamento recusado: {%s}", payload);
    }
}
