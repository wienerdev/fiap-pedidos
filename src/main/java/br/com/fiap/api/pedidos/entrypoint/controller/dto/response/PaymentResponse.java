package br.com.fiap.api.pedidos.entrypoint.controller.dto.response;

import br.com.fiap.api.pedidos.core.Payment;

import java.util.UUID;

public record PaymentResponse(UUID paymentId, OrderResponse orderResponse, String paymentMethod) {

    public static PaymentResponse fromEntityToResponse(Payment payment) {
        return new PaymentResponse(payment.getPaymentId(), payment.getOrder().toResponse(), payment.getPaymentMethod());
    }
}
