package br.com.fiap.api.pedidos.entrypoint.controller.dto.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Client;

import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;

public class ClientResponseTest {
    /**
     * Method under test: {@link ClientResponse#fromClientResponse(Client)}
     */
    @Test
    public void testFromClientResponse() {
        ClientResponse actualFromClientResponseResult = ClientResponse.fromClientResponse(new Client());
        assertNull(actualFromClientResponseResult.clientCpf());
        assertNull(actualFromClientResponseResult.clientName());
        assertNull(actualFromClientResponseResult.clientId());
        assertNull(actualFromClientResponseResult.clientEmail());
    }

    /**
     * Method under test: {@link ClientResponse#fromClientResponse(Client)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testFromClientResponse2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.Client.getClientId()" because "client" is null
        //       at br.com.fiap.api.pedidos.entrypoint.controller.dto.response.ClientResponse.fromClientResponse(ClientResponse.java:9)
        //   See https://diff.blue/R013 to resolve this issue.

        ClientResponse.fromClientResponse(null);
    }

    /**
     * Method under test: {@link ClientResponse#fromClientResponse(Client)}
     */
    @Test
    public void testFromClientResponse3() {
        Client client = mock(Client.class);
        when(client.getClientCpf()).thenReturn("Client Cpf");
        when(client.getClientEmail()).thenReturn("jane.doe@example.org");
        when(client.getClientName()).thenReturn("Dr Jane Doe");
        UUID randomUUIDResult = UUID.randomUUID();
        when(client.getClientId()).thenReturn(randomUUIDResult);
        ClientResponse actualFromClientResponseResult = ClientResponse.fromClientResponse(client);
        assertEquals("Client Cpf", actualFromClientResponseResult.clientCpf());
        assertEquals("Dr Jane Doe", actualFromClientResponseResult.clientName());
        assertSame(randomUUIDResult, actualFromClientResponseResult.clientId());
        assertEquals("jane.doe@example.org", actualFromClientResponseResult.clientEmail());
        verify(client).getClientCpf();
        verify(client).getClientEmail();
        verify(client).getClientName();
        verify(client).getClientId();
    }
}

