package br.com.fiap.api.pedidos.application.gateway;

import br.com.fiap.api.pedidos.domain.port.usecase.MercadoPagoWebhookUseCasePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MercadoPagoWebhookGateway {

    private final MercadoPagoWebhookUseCasePort mercadoPagoWebhookUseCasePort;

    public MercadoPagoWebhookGateway(MercadoPagoWebhookUseCasePort mercadoPagoWebhookUseCasePort) {
        this.mercadoPagoWebhookUseCasePort = mercadoPagoWebhookUseCasePort;
    }

    public ResponseEntity<String> handleMercadoPagoNotification(Map<String, Object> payload) {
        return new ResponseEntity<>(
                mercadoPagoWebhookUseCasePort.handleMercadoPagoNotification(payload),
                HttpStatus.OK);
    }
}