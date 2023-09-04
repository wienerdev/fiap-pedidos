package br.com.fiap.api.pedidos.dataprovider.repository.entity;

import br.com.fiap.api.pedidos.core.Payment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    private UUID paymentId;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderEntity order;
    private String paymentMethod;

    public PaymentEntity() {
    }

    public PaymentEntity(UUID paymentId, OrderEntity order, String paymentMethod) {
        this.paymentId = paymentId;
        this.order = order;
        this.paymentMethod = paymentMethod;
    }

    public PaymentEntity(Payment payment) {
        this.paymentId = payment.getPaymentId();
        this.order = payment.getOrder().toEntity();
        this.paymentMethod = payment.getPaymentMethod();
    }

    public Payment toPayment() {
        return new Payment(
                this.paymentId,
                this.order.toOrder(),
                this.paymentMethod);
    }
}
