package br.com.fiap.api.pedidos.entrypoint.controller.dto.response;

import br.com.fiap.api.pedidos.core.Client;
import java.util.UUID;

public record ClientResponse(UUID clientId, String clientCpf, String clientName, String clientEmail) {

    public static ClientResponse fromClientResponse(Client client) {
        return new ClientResponse(client.getClientId(), client.getClientCpf(), client.getClientName(), client.getClientEmail());
    }
}

