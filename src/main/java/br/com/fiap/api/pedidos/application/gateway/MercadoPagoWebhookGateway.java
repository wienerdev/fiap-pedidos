package br.com.fiap.api.pedidos.application.gateway;


import br.com.fiap.api.pedidos.core.usecase.impl.payments.MercadoPagoWebhookUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MercadoPagoWebhookGateway {

    private final MercadoPagoWebhookUseCaseImpl mercadoPagoWebhookUseCase;

    public MercadoPagoWebhookGateway(MercadoPagoWebhookUseCaseImpl mercadoPagoWebhookUseCase) {
        this.mercadoPagoWebhookUseCase = mercadoPagoWebhookUseCase;
    }


    public ResponseEntity<String> handleMercadoPagoNotification(Map<String, Object> payload) {
        return new ResponseEntity<>(
                mercadoPagoWebhookUseCase.handleMercadoPagoNotification(payload),
                HttpStatus.OK);
    }
}