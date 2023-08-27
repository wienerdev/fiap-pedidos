package br.com.fiap.api.pedidos.interfaceadapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class MercadoPagoWebhookController {

    private static final Logger logger = LoggerFactory.getLogger(MercadoPagoWebhookController.class);

    @PostMapping("/mercado-pago")
    public ResponseEntity<String> handleMercadoPagoNotification(@RequestBody Map<String, Object> payload) {

        logger.info("Recebido webhook do Mercado Pago: {}", payload);

        // Exemplo de processamento, ajuste de acordo com o payload específico do Mercado Pago
        String status = (String) payload.get("status");

        if (status == null) {
            logger.warn("Webhook recebido sem status");
            return ResponseEntity.badRequest().body("Payload inválido");
        }

        switch (status.toLowerCase()) {
            case "approved":
                handleApprovedPayment(payload);
                break;
            case "rejected":
                handleRejectedPayment(payload);
                break;
            default:
                logger.warn("Status de pagamento desconhecido: {}", status);
                return ResponseEntity.badRequest().body("Status desconhecido");
        }

        return ResponseEntity.ok().build();
    }

    private void handleApprovedPayment(Map<String, Object> payload) {
        logger.info("Pagamento aprovado: {}", payload);
    }

    private void handleRejectedPayment(Map<String, Object> payload) {
        logger.warn("Pagamento recusado: {}", payload);
    }
}