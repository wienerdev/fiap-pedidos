package br.com.fiap.api.pedidos.client;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.ClientResponse;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientTest {

    @Test
    void testToResponse() {
        // Given
        UUID clientId = UUID.randomUUID();
        String clientCpf = "12345678900";
        String clientName = "John Doe";
        String clientEmail = "john.doe@example.com";

        Client client = new Client(clientId, clientCpf, clientName, clientEmail);

        // When
        ClientResponse clientResponse = client.toResponse();

        // Then
        assertEquals(client.getClientId(), clientResponse.clientId());
        assertEquals(client.getClientCpf(), clientResponse.clientCpf());
        assertEquals(client.getClientName(), clientResponse.clientName());
        assertEquals(client.getClientEmail(), clientResponse.clientEmail());
        // Add more assertions as needed
    }

    @Test
    void testToEntity() {
        // Given
        UUID clientId = UUID.randomUUID();
        String clientCpf = "12345678900";
        String clientName = "John Doe";
        String clientEmail = "john.doe@example.com";

        Client client = new Client(clientId, clientCpf, clientName, clientEmail);

        // When
        ClientEntity clientEntity = client.toEntity();

        // Then
        assertEquals(client.getClientId(), clientEntity.getClientId());
        assertEquals(client.getClientCpf(), clientEntity.getClientCpf());
        assertEquals(client.getClientName(), clientEntity.getClientName());
        assertEquals(client.getClientEmail(), clientEntity.getClientEmail());
    }

}
