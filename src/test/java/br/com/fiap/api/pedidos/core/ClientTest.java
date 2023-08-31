package br.com.fiap.api.pedidos.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.UUID;

import org.junit.Test;

public class ClientTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Client#Client()}
     *   <li>{@link Client#setClientCpf(String)}
     *   <li>{@link Client#setClientEmail(String)}
     *   <li>{@link Client#setClientId(UUID)}
     *   <li>{@link Client#setClientName(String)}
     *   <li>{@link Client#getClientCpf()}
     *   <li>{@link Client#getClientEmail()}
     *   <li>{@link Client#getClientId()}
     *   <li>{@link Client#getClientName()}
     * </ul>
     */
    @Test
    public void testConstructor() {
        Client actualClient = new Client();
        actualClient.setClientCpf("Client Cpf");
        actualClient.setClientEmail("jane.doe@example.org");
        UUID clientId = UUID.randomUUID();
        actualClient.setClientId(clientId);
        actualClient.setClientName("Dr Jane Doe");
        String actualClientCpf = actualClient.getClientCpf();
        String actualClientEmail = actualClient.getClientEmail();
        UUID actualClientId = actualClient.getClientId();
        assertEquals("Client Cpf", actualClientCpf);
        assertEquals("jane.doe@example.org", actualClientEmail);
        assertSame(clientId, actualClientId);
        assertEquals("Dr Jane Doe", actualClient.getClientName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Client#Client(UUID, String, String, String)}
     *   <li>{@link Client#setClientCpf(String)}
     *   <li>{@link Client#setClientEmail(String)}
     *   <li>{@link Client#setClientId(UUID)}
     *   <li>{@link Client#setClientName(String)}
     *   <li>{@link Client#getClientCpf()}
     *   <li>{@link Client#getClientEmail()}
     *   <li>{@link Client#getClientId()}
     *   <li>{@link Client#getClientName()}
     * </ul>
     */
    @Test
    public void testConstructor2() {
        Client actualClient = new Client(UUID.randomUUID(), "Client Cpf", "Dr Jane Doe", "jane.doe@example.org");
        actualClient.setClientCpf("Client Cpf");
        actualClient.setClientEmail("jane.doe@example.org");
        UUID clientId = UUID.randomUUID();
        actualClient.setClientId(clientId);
        actualClient.setClientName("Dr Jane Doe");
        String actualClientCpf = actualClient.getClientCpf();
        String actualClientEmail = actualClient.getClientEmail();
        UUID actualClientId = actualClient.getClientId();
        assertEquals("Client Cpf", actualClientCpf);
        assertEquals("jane.doe@example.org", actualClientEmail);
        assertSame(clientId, actualClientId);
        assertEquals("Dr Jane Doe", actualClient.getClientName());
    }
}

