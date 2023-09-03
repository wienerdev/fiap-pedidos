package br.com.fiap.api.pedidos.entrypoint.controller.dto.request;

import br.com.fiap.api.pedidos.core.Client;

import java.util.UUID;

public record CreateClientRequest(String clientCpf, String clientName, String clientEmail) {
    public Client toClient() {
        return new Client(UUID.randomUUID(), clientCpf, clientName, clientEmail);
    }

}
