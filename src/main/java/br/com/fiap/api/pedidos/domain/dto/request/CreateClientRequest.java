package br.com.fiap.api.pedidos.domain.dto.request;

import br.com.fiap.api.pedidos.domain.Client;

import java.util.UUID;

public record CreateClientRequest(String clientName, String clientEmail, String clientCpf ) {

    public static Client fromClient(CreateClientRequest request) {
        return new Client(UUID.randomUUID(),request.clientCpf(), request.clientName(), request.clientEmail());
    }
}
