package br.com.fiap.api.pedidos.domain.dto.response;

import br.com.fiap.api.pedidos.domain.Client;
import java.util.UUID;

public record ClientResponse(UUID clientId, String clientName, String clientEmail, String clientCpf ) {
    public static ClientResponse fromClientResponse(Client client) {
        return new ClientResponse(UUID.randomUUID(),client.getClientCpf(), client.getClientName(), client.getClientEmail());
    }
}

