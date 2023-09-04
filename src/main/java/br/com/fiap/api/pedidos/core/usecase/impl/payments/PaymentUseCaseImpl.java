package br.com.fiap.api.pedidos.core.usecase.impl.payments;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Payment;
import br.com.fiap.api.pedidos.core.dataprovider.repository.OrderRepository;
import br.com.fiap.api.pedidos.core.dataprovider.repository.PaymentRepository;
import br.com.fiap.api.pedidos.core.exception.PaymentRefusedException;
import br.com.fiap.api.pedidos.core.usecase.PaymentUseCase;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.MakePaymentRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.PaymentResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.PaymentStatusResponse;

import java.util.List;
import java.util.UUID;

public class PaymentUseCaseImpl implements PaymentUseCase {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepositoryPort;

    public PaymentUseCaseImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepositoryPort = paymentRepository;
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepositoryPort.getAll();
    }

    @Override
    public PaymentResponse getById(UUID id) {
        Payment payment = paymentRepositoryPort.getById(id);
        return new PaymentResponse(payment.getPaymentId(), payment.getOrder().toResponse(), payment.getPaymentMethod());
    }

    @Override
    public Payment makePayment(MakePaymentRequest request) {
        Order order = orderRepository.getById(request.orderId());

        boolean paymentResponse = mercadoPagoIntegrationOrderPay();

        if (paymentResponse) {
            saveOrder(order);
            return paymentRepositoryPort.makePayment(new Payment(UUID.randomUUID(), order, request.paymentMethod()));
        }

        throw new PaymentRefusedException("There was a problem with the payment, please try again!");
    }

    @Override
    public PaymentStatusResponse getPaymentStatusByOrderId(UUID orderId) {
        Order order = orderRepository.getById(orderId);

        if (order.getPaymentReceived()) {
            return new PaymentStatusResponse(true);
        }

        return new PaymentStatusResponse(false);
    }

    private void saveOrder(Order order) {
        order.setOrderStatus(OrderStatusEnum.PREPARING);
        order.setPaymentReceived(true);
        orderRepository.save(order);
    }

    private boolean mercadoPagoIntegrationOrderPay() {
        return true;
    }

}
