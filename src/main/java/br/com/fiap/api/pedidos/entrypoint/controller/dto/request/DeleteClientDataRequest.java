package br.com.fiap.api.pedidos.entrypoint.controller.dto.request;

import java.util.UUID;

public record DeleteClientDataRequest(String clientName, String clientAddress, String clientPhoneNumber) {
}
