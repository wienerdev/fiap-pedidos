package br.com.fiap.api.pedidos.dataprovider.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.exception.ClientAlreadyRegistered;
import br.com.fiap.api.pedidos.core.exception.ClientNotFoundException;
import br.com.fiap.api.pedidos.dataprovider.repository.ClientRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;

import java.util.Optional;
import java.util.UUID;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {ClientRepositoryImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientRepositoryImplTest {
    @Autowired
    private ClientRepositoryImpl clientRepositoryImpl;

    @MockBean
    private ClientRepositoryJpa clientRepositoryJpa;

    /**
     * Method under test: {@link ClientRepositoryImpl#identifyClientByCpf(String)}
     */
    @Test
    public void testIdentifyClientByCpf() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        UUID clientId = UUID.randomUUID();
        clientEntity.setClientId(clientId);
        clientEntity.setClientName("Dr Jane Doe");
        Optional<ClientEntity> ofResult = Optional.of(clientEntity);
        when(clientRepositoryJpa.findByClientCpf(Mockito.<String>any())).thenReturn(ofResult);
        Optional<Client> actualIdentifyClientByCpfResult = clientRepositoryImpl.identifyClientByCpf("Cpf");
        assertTrue(actualIdentifyClientByCpfResult.isPresent());
        Client getResult = actualIdentifyClientByCpfResult.get();
        assertEquals("Client Cpf", getResult.getClientCpf());
        assertEquals("Dr Jane Doe", getResult.getClientName());
        assertSame(clientId, getResult.getClientId());
        assertEquals("jane.doe@example.org", getResult.getClientEmail());
        verify(clientRepositoryJpa).findByClientCpf(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ClientRepositoryImpl#identifyClientByCpf(String)}
     */
    @Test
    public void testIdentifyClientByCpf2() {
        ClientEntity clientEntity = mock(ClientEntity.class);
        when(clientEntity.toClient()).thenReturn(new Client());
        doNothing().when(clientEntity).setClientCpf(Mockito.<String>any());
        doNothing().when(clientEntity).setClientEmail(Mockito.<String>any());
        doNothing().when(clientEntity).setClientId(Mockito.<UUID>any());
        doNothing().when(clientEntity).setClientName(Mockito.<String>any());
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        clientEntity.setClientId(UUID.randomUUID());
        clientEntity.setClientName("Dr Jane Doe");
        Optional<ClientEntity> ofResult = Optional.of(clientEntity);
        when(clientRepositoryJpa.findByClientCpf(Mockito.<String>any())).thenReturn(ofResult);
        assertTrue(clientRepositoryImpl.identifyClientByCpf("Cpf").isPresent());
        verify(clientRepositoryJpa).findByClientCpf(Mockito.<String>any());
        verify(clientEntity).toClient();
        verify(clientEntity).setClientCpf(Mockito.<String>any());
        verify(clientEntity).setClientEmail(Mockito.<String>any());
        verify(clientEntity).setClientId(Mockito.<UUID>any());
        verify(clientEntity).setClientName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ClientRepositoryImpl#identifyClientByCpf(String)}
     */
    @Test
    public void testIdentifyClientByCpf3() {
        Optional<ClientEntity> emptyResult = Optional.empty();
        when(clientRepositoryJpa.findByClientCpf(Mockito.<String>any())).thenReturn(emptyResult);
        assertThrows(ClientNotFoundException.class, () -> clientRepositoryImpl.identifyClientByCpf("Cpf"));
        verify(clientRepositoryJpa).findByClientCpf(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ClientRepositoryImpl#registerClient(Client)}
     */
    @Test
    public void testRegisterClient() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        clientEntity.setClientId(UUID.randomUUID());
        clientEntity.setClientName("Dr Jane Doe");
        Optional<ClientEntity> ofResult = Optional.of(clientEntity);
        when(clientRepositoryJpa.findByClientCpf(Mockito.<String>any())).thenReturn(ofResult);
        assertThrows(ClientAlreadyRegistered.class, () -> clientRepositoryImpl.registerClient(new Client()));
        verify(clientRepositoryJpa).findByClientCpf(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ClientRepositoryImpl#registerClient(Client)}
     */
    @Test
    public void testRegisterClient2() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        UUID clientId = UUID.randomUUID();
        clientEntity.setClientId(clientId);
        clientEntity.setClientName("Dr Jane Doe");
        when(clientRepositoryJpa.save(Mockito.<ClientEntity>any())).thenReturn(clientEntity);
        Optional<ClientEntity> emptyResult = Optional.empty();
        when(clientRepositoryJpa.findByClientCpf(Mockito.<String>any())).thenReturn(emptyResult);
        Client actualRegisterClientResult = clientRepositoryImpl.registerClient(new Client());
        assertEquals("Client Cpf", actualRegisterClientResult.getClientCpf());
        assertEquals("Dr Jane Doe", actualRegisterClientResult.getClientName());
        assertSame(clientId, actualRegisterClientResult.getClientId());
        assertEquals("jane.doe@example.org", actualRegisterClientResult.getClientEmail());
        verify(clientRepositoryJpa).save(Mockito.<ClientEntity>any());
        verify(clientRepositoryJpa).findByClientCpf(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ClientRepositoryImpl#registerClient(Client)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testRegisterClient3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.Client.getClientCpf()" because "client" is null
        //       at br.com.fiap.api.pedidos.dataprovider.repository.impl.ClientRepositoryImpl.registerClient(ClientRepositoryImpl.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        clientEntity.setClientId(UUID.randomUUID());
        clientEntity.setClientName("Dr Jane Doe");

        ClientEntity clientEntity2 = new ClientEntity();
        clientEntity2.setClientCpf("Client Cpf");
        clientEntity2.setClientEmail("jane.doe@example.org");
        clientEntity2.setClientId(UUID.randomUUID());
        clientEntity2.setClientName("Dr Jane Doe");
        Optional<ClientEntity> ofResult = Optional.of(clientEntity2);
        when(clientRepositoryJpa.save(Mockito.<ClientEntity>any())).thenReturn(clientEntity);
        when(clientRepositoryJpa.findByClientCpf(Mockito.<String>any())).thenReturn(ofResult);
        clientRepositoryImpl.registerClient(null);
    }

    /**
     * Method under test: {@link ClientRepositoryImpl#registerClient(Client)}
     */
    @Test
    public void testRegisterClient4() {
        when(clientRepositoryJpa.findByClientCpf(Mockito.<String>any()))
                .thenThrow(new ClientAlreadyRegistered("Not all who wander are lost"));
        assertThrows(ClientAlreadyRegistered.class, () -> clientRepositoryImpl.registerClient(new Client()));
        verify(clientRepositoryJpa).findByClientCpf(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ClientRepositoryImpl#registerClient(Client)}
     */
    @Test
    public void testRegisterClient5() {
        ClientEntity clientEntity = mock(ClientEntity.class);
        Client client = new Client();
        when(clientEntity.toClient()).thenReturn(client);
        doNothing().when(clientEntity).setClientCpf(Mockito.<String>any());
        doNothing().when(clientEntity).setClientEmail(Mockito.<String>any());
        doNothing().when(clientEntity).setClientId(Mockito.<UUID>any());
        doNothing().when(clientEntity).setClientName(Mockito.<String>any());
        clientEntity.setClientCpf("Client Cpf");
        clientEntity.setClientEmail("jane.doe@example.org");
        clientEntity.setClientId(UUID.randomUUID());
        clientEntity.setClientName("Dr Jane Doe");
        when(clientRepositoryJpa.save(Mockito.<ClientEntity>any())).thenReturn(clientEntity);
        Optional<ClientEntity> emptyResult = Optional.empty();
        when(clientRepositoryJpa.findByClientCpf(Mockito.<String>any())).thenReturn(emptyResult);
        assertSame(client, clientRepositoryImpl.registerClient(new Client()));
        verify(clientRepositoryJpa).save(Mockito.<ClientEntity>any());
        verify(clientRepositoryJpa).findByClientCpf(Mockito.<String>any());
        verify(clientEntity).toClient();
        verify(clientEntity).setClientCpf(Mockito.<String>any());
        verify(clientEntity).setClientEmail(Mockito.<String>any());
        verify(clientEntity).setClientId(Mockito.<UUID>any());
        verify(clientEntity).setClientName(Mockito.<String>any());
    }
}

