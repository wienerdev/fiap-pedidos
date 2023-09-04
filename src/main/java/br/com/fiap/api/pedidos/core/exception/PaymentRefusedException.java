package br.com.fiap.api.pedidos.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class PaymentRefusedException extends RuntimeException {

    public PaymentRefusedException(String message) {
        super(message);
    }
}
