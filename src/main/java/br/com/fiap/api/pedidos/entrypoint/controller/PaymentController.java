package br.com.fiap.api.pedidos.entrypoint.controller;

import br.com.fiap.api.pedidos.application.gateway.MercadoPagoWebhookGateway;
import br.com.fiap.api.pedidos.core.Payment;
import br.com.fiap.api.pedidos.core.usecase.PaymentUseCase;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.MakePaymentRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.PaymentResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.PaymentStatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentUseCase paymentUseCase;
    private final MercadoPagoWebhookGateway mercadoPagoWebhookGateway;


    public PaymentController(PaymentUseCase paymentUseCase, MercadoPagoWebhookGateway mercadoPagoWebhookGateway) {
        this.paymentUseCase = paymentUseCase;
        this.mercadoPagoWebhookGateway = mercadoPagoWebhookGateway;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<PaymentResponse>>> getAll() {
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                paymentUseCase.getAll().stream().map(PaymentResponse::fromEntityToResponse).toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Object>> getById(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(new BaseResponse<>(
                    true,
                    paymentUseCase.getById(id)), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(new BaseResponse<>(
                    false,
                    e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @GetMapping("/status/{order-id}")
    public ResponseEntity<BaseResponse<PaymentStatusResponse>> getPaymentStatus(@PathVariable("order-id") UUID id) {
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                paymentUseCase.getPaymentStatusByOrderId(id)), HttpStatus.OK);
    }

    @PostMapping("pay-order")
    public ResponseEntity<BaseResponse<PaymentResponse>> makePayment(@RequestBody MakePaymentRequest request) {
        Payment paymentSave = paymentUseCase.makePayment(request);
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                PaymentResponse.fromEntityToResponse(paymentSave)), HttpStatus.OK);
    }

    @PostMapping("/webhook/mercado-pago")
    public ResponseEntity<String> handleMercadoPagoNotification(@RequestBody Map<String, Object> payload) {

        logger.info("Recebido webhook do Mercado Pago: {}", payload);

        return mercadoPagoWebhookGateway.handleMercadoPagoNotification(payload);
    }
}
