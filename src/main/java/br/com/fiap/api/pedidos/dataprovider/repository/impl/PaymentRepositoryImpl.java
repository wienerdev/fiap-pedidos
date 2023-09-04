package br.com.fiap.api.pedidos.dataprovider.repository.impl;

import br.com.fiap.api.pedidos.core.Payment;
import br.com.fiap.api.pedidos.core.dataprovider.repository.PaymentRepository;
import br.com.fiap.api.pedidos.dataprovider.repository.PaymentRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.PaymentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentRepositoryJpa paymentRepositoryJpa;

    public PaymentRepositoryImpl(PaymentRepositoryJpa paymentRepositoryJpa) {
        this.paymentRepositoryJpa = paymentRepositoryJpa;
    }

    @Override
    public List<Payment> getAll() {
        List<PaymentEntity> entities = paymentRepositoryJpa.findAll();
        return entities.stream().map(PaymentEntity::toPayment).collect(Collectors.toList());
    }

    @Override
    public Payment getById(UUID id) {
        Optional<PaymentEntity> paymentEntity = this.paymentRepositoryJpa.findById(id);
        if (paymentEntity.isPresent())
            return paymentEntity.get().toPayment();
        throw new RuntimeException("Payment not found!");
    }

    @Override
    public Payment makePayment(Payment payment) {
        PaymentEntity entity = new PaymentEntity(payment.getPaymentId(), payment.getOrder().toEntity(), payment.getPaymentMethod());
        return paymentRepositoryJpa.save(entity).toPayment();
    }
}
