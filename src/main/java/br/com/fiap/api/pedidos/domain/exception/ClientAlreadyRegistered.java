package br.com.fiap.api.pedidos.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.CONFLICT)
public class ClientAlreadyRegistered extends RuntimeException {
        public ClientAlreadyRegistered(String message) {
            super(message);
    }
}
