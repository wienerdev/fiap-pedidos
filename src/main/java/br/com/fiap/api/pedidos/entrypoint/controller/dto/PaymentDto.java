package br.com.fiap.api.pedidos.entrypoint.controller.dto;

import br.com.fiap.api.pedidos.core.Payment;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.PaymentEntity;

import java.util.UUID;

public record PaymentDto(UUID paymentId, OrderEntity order, String paymentMethod) {
    public static PaymentEntity fromPaymentToPaymentEntity(Payment payment) {
        return new PaymentEntity(payment.getPaymentId(), payment.getOrder().toEntity(), payment.getPaymentMethod());
    }
}