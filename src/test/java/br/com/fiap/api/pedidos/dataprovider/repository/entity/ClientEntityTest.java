package br.com.fiap.api.pedidos.dataprovider.repository.entity;

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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {ClientEntity.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientEntityTest {
    @Autowired
    private ClientEntity clientEntity;

    /**
     * Method under test: {@link ClientEntity#ClientEntity()}
     */
    @Test
    public void testConstructor() {
        ClientEntity actualClientEntity = new ClientEntity();
        assertNull(actualClientEntity.getClientCpf());
        assertNull(actualClientEntity.getClientName());
        assertNull(actualClientEntity.getClientEmail());
        assertNull(actualClientEntity.getClientId());
        Client toClientResult = actualClientEntity.toClient();
        assertNull(toClientResult.getClientEmail());
        assertNull(toClientResult.getClientCpf());
        assertNull(toClientResult.getClientId());
        assertNull(toClientResult.getClientName());
    }

    /**
     * Method under test: {@link ClientEntity#ClientEntity(UUID, String, String, String)}
     */
    @Test
    public void testConstructor2() {
        UUID clientId = UUID.randomUUID();
        ClientEntity actualClientEntity = new ClientEntity(clientId, "Client Cpf", "Dr Jane Doe", "jane.doe@example.org");

        assertEquals("Client Cpf", actualClientEntity.getClientCpf());
        assertEquals("Dr Jane Doe", actualClientEntity.getClientName());
        assertEquals("jane.doe@example.org", actualClientEntity.getClientEmail());
        UUID clientId2 = actualClientEntity.getClientId();
        assertSame(clientId, clientId2);
        Client toClientResult = actualClientEntity.toClient();
        assertEquals("jane.doe@example.org", toClientResult.getClientEmail());
        assertEquals(4, clientId2.version());
        assertEquals(2, clientId2.variant());
        assertEquals("Client Cpf", toClientResult.getClientCpf());
        assertSame(clientId2, toClientResult.getClientId());
        assertEquals("Dr Jane Doe", toClientResult.getClientName());
    }

    /**
     * Method under test: {@link ClientEntity#ClientEntity(Client)}
     */
    @Test
    public void testConstructor3() {
        ClientEntity actualClientEntity = new ClientEntity(new Client());
        assertNull(actualClientEntity.getClientCpf());
        assertNull(actualClientEntity.getClientName());
        assertNull(actualClientEntity.getClientEmail());
        assertNull(actualClientEntity.getClientId());
    }

    /**
     * Method under test: {@link ClientEntity#ClientEntity(Client)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testConstructor4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.Client.getClientId()" because "client" is null
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity.<init>(ClientEntity.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        new ClientEntity(null);
    }

    /**
     * Method under test: {@link ClientEntity#ClientEntity(Client)}
     */
    @Test
    public void testConstructor5() {
        Client client = mock(Client.class);
        when(client.getClientCpf()).thenReturn("Client Cpf");
        when(client.getClientEmail()).thenReturn("jane.doe@example.org");
        when(client.getClientName()).thenReturn("Dr Jane Doe");
        UUID randomUUIDResult = UUID.randomUUID();
        when(client.getClientId()).thenReturn(randomUUIDResult);
        ClientEntity actualClientEntity = new ClientEntity(client);
        assertEquals("Client Cpf", actualClientEntity.getClientCpf());
        assertEquals("Dr Jane Doe", actualClientEntity.getClientName());
        assertEquals("jane.doe@example.org", actualClientEntity.getClientEmail());
        assertSame(randomUUIDResult, actualClientEntity.getClientId());
        verify(client).getClientCpf();
        verify(client).getClientEmail();
        verify(client).getClientName();
        verify(client).getClientId();
    }

    /**
     * Method under test: {@link ClientEntity#toClient()}
     */
    @Test
    public void testToClient() {
        Client actualToClientResult = (new ClientEntity()).toClient();
        assertNull(actualToClientResult.getClientCpf());
        assertNull(actualToClientResult.getClientName());
        assertNull(actualToClientResult.getClientId());
        assertNull(actualToClientResult.getClientEmail());
    }

    /**
     * Method under test: {@link ClientEntity#toClient()}
     */
    @Test
    public void testToClient2() {
        Client client = mock(Client.class);
        when(client.getClientCpf()).thenReturn("Client Cpf");
        when(client.getClientEmail()).thenReturn("jane.doe@example.org");
        when(client.getClientName()).thenReturn("Dr Jane Doe");
        UUID randomUUIDResult = UUID.randomUUID();
        when(client.getClientId()).thenReturn(randomUUIDResult);
        Client actualToClientResult = (new ClientEntity(client)).toClient();
        assertEquals("Client Cpf", actualToClientResult.getClientCpf());
        assertEquals("Dr Jane Doe", actualToClientResult.getClientName());
        assertSame(randomUUIDResult, actualToClientResult.getClientId());
        assertEquals("jane.doe@example.org", actualToClientResult.getClientEmail());
        verify(client).getClientCpf();
        verify(client).getClientEmail();
        verify(client).getClientName();
        verify(client).getClientId();
    }
}

