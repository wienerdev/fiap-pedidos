package br.com.fiap.api.pedidos.entrypoint.controller.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;

import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;

public class ClientDtoTest {
    /**
     * Method under test: {@link ClientDto#fromClientToClientEntity(Client)}
     */
    @Test
    public void testFromClientToClientEntity() {
        ClientEntity actualFromClientToClientEntityResult = ClientDto.fromClientToClientEntity(new Client());
        assertNull(actualFromClientToClientEntityResult.getClientCpf());
        assertNull(actualFromClientToClientEntityResult.getClientName());
        assertNull(actualFromClientToClientEntityResult.getClientEmail());
        assertNull(actualFromClientToClientEntityResult.getClientId());
    }

    /**
     * Method under test: {@link ClientDto#fromClientToClientEntity(Client)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testFromClientToClientEntity2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.Client.getClientId()" because "client" is null
        //       at br.com.fiap.api.pedidos.entrypoint.controller.dto.ClientDto.fromClientToClientEntity(ClientDto.java:10)
        //   See https://diff.blue/R013 to resolve this issue.

        ClientDto.fromClientToClientEntity(null);
    }

    /**
     * Method under test: {@link ClientDto#fromClientToClientEntity(Client)}
     */
    @Test
    public void testFromClientToClientEntity3() {
        Client client = mock(Client.class);
        when(client.getClientCpf()).thenReturn("Client Cpf");
        when(client.getClientEmail()).thenReturn("jane.doe@example.org");
        when(client.getClientName()).thenReturn("Dr Jane Doe");
        UUID randomUUIDResult = UUID.randomUUID();
        when(client.getClientId()).thenReturn(randomUUIDResult);
        ClientEntity actualFromClientToClientEntityResult = ClientDto.fromClientToClientEntity(client);
        assertEquals("Dr Jane Doe", actualFromClientToClientEntityResult.getClientCpf());
        assertEquals("jane.doe@example.org", actualFromClientToClientEntityResult.getClientName());
        assertEquals("Client Cpf", actualFromClientToClientEntityResult.getClientEmail());
        assertSame(randomUUIDResult, actualFromClientToClientEntityResult.getClientId());
        verify(client).getClientCpf();
        verify(client).getClientEmail();
        verify(client).getClientName();
        verify(client).getClientId();
    }
}

