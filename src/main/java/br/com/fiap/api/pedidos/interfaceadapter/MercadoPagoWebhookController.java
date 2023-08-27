package br.com.fiap.api.pedidos.interfaceadapter;

import br.com.fiap.api.pedidos.application.gateway.MercadoPagoWebhookGateway;
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

    private final MercadoPagoWebhookGateway mercadoPagoWebhookGateway;

    public MercadoPagoWebhookController(MercadoPagoWebhookGateway mercadoPagoWebhookGateway) {
        this.mercadoPagoWebhookGateway = mercadoPagoWebhookGateway;
    }

    private static final Logger logger = LoggerFactory.getLogger(MercadoPagoWebhookController.class);

    @PostMapping("/mercado-pago")
    public ResponseEntity<String> handleMercadoPagoNotification(@RequestBody Map<String, Object> payload) {

        logger.info("Recebido webhook do Mercado Pago: {}", payload);

        return mercadoPagoWebhookGateway.handleMercadoPagoNotification(payload);
    }
}