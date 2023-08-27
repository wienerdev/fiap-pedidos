package br.com.fiap.api.pedidos.domain.port.usecase;

import java.util.Map;

public interface MercadoPagoWebhookUseCasePort {

    String handleMercadoPagoNotification(Map<String, Object> payload);

}
