package br.com.fiap.api.pedidos.core;

import br.com.fiap.api.pedidos.dataprovider.repository.entity.PaymentEntity;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.PaymentResponse;

import java.util.UUID;

public class Payment {

    private UUID paymentId;
    private Order order;
    private String paymentMethod;

    public Payment() {
    }

    public Payment(UUID paymentId, Order order, String paymentMethod) {
        this.paymentId = paymentId;
        this.order = order;
        this.paymentMethod = paymentMethod;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentEntity toEntity() {
        return new PaymentEntity(this.paymentId, this.order.toEntity(), this.paymentMethod);
    }

    public PaymentResponse toResponse() {
        return new PaymentResponse(this.paymentId, this.order.toResponse(), this.paymentMethod);
    }
}
