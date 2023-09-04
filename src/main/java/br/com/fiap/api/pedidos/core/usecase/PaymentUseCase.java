package br.com.fiap.api.pedidos.core.usecase;

import br.com.fiap.api.pedidos.core.Payment;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.MakePaymentRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.PaymentResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.PaymentStatusResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentUseCase {

    List<Payment> getAll();

    PaymentResponse getById(UUID id);
    Payment makePayment(MakePaymentRequest request);

    PaymentStatusResponse getPaymentStatusByOrderId(UUID id);
}
