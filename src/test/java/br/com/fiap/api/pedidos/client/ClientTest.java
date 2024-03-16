package br.com.fiap.api.pedidos.client;

import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.ClientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.api.pedidos.core.Client;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@ActiveProfiles("teste")
class ClientTest {

    private Client client;

    @BeforeEach
    void setUp() {
        UUID clientId = UUID.randomUUID();
        String clientCpf = "123456789";
        String clientName = "John Doe";
        String clientEmail = "johndoe@example.com";
        String clientAddress = "Street 01";
        String clientPhoneNumber = "1921992";

        client = new Client(clientId, clientCpf, clientName, clientEmail, clientAddress, clientPhoneNumber);
    }

    @Test
    void testGetClientId() {
        UUID expected = client.getClientId();
        assertEquals(expected, client.getClientId());
    }

    @Test
    void testSetClientId() {
        UUID newClientId = UUID.randomUUID();
        client.setClientId(newClientId);
        assertEquals(newClientId, client.getClientId());
    }

    @Test
    void testGetClientCpf() {
        String expected = client.getClientCpf();
        assertEquals(expected, client.getClientCpf());
    }

    @Test
    void testSetClientCpf() {
        String newClientCpf = "987654321";
        client.setClientCpf(newClientCpf);
        assertEquals(newClientCpf, client.getClientCpf());
    }

    @Test
    void testGetClientName() {
        String expected = client.getClientName();
        assertEquals(expected, client.getClientName());
    }

    @Test
    void testSetClientName() {
        String newClientName = "Jane Smith";
        client.setClientName(newClientName);
        assertEquals(newClientName, client.getClientName());
    }

    @Test
    void testGetClientEmail() {
        String expected = client.getClientEmail();
        assertEquals(expected, client.getClientEmail());
    }

    @Test
    void testSetClientEmail() {
        String newClientEmail = "janesmith@example.com";
        client.setClientEmail(newClientEmail);
        assertEquals(newClientEmail, client.getClientEmail());
    }

    @Test
    void testToResponse() {
        ClientResponse expected = new ClientResponse(client.getClientId(), client.getClientCpf(), client.getClientName(), client.getClientEmail(), client.getClientAddress(), client.getClientPhoneNumber());
        assertEquals(expected, client.toResponse());
    }

    @Test
    void testToEntity() {
        ClientEntity expected = new ClientEntity(client.getClientId(), client.getClientCpf(), client.getClientName(), client.getClientEmail(), client.getClientAddress(), client.getClientPhoneNumber());
        assertEquals(expected.getClientId(), client.toEntity().getClientId());
    }
}