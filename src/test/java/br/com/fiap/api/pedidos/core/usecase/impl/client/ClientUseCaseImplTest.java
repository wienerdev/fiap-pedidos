package br.com.fiap.api.pedidos.core.usecase.impl.client;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ClientRepository;
import br.com.fiap.api.pedidos.core.exception.ClientNotFoundException;
import br.com.fiap.api.pedidos.dataprovider.repository.ClientRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.ClientRepositoryImpl;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {ClientUseCaseImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientUseCaseImplTest {
    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientUseCaseImpl clientUseCaseImpl;

    /**
     * Method under test: {@link ClientUseCaseImpl#ClientUseCaseImpl(ClientRepository)}
     */
    @Test
    public void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     ClientUseCaseImpl.clientRepository

        new ClientUseCaseImpl(new ClientRepositoryImpl(mock(ClientRepositoryJpa.class)));
    }

    /**
     * Method under test: {@link ClientUseCaseImpl#getClientByCpf(String)}
     */
    @Test
    public void testGetClientByCpf() {
        Client client = new Client();
        Optional<Client> ofResult = Optional.of(client);
        when(clientRepository.identifyClientByCpf(Mockito.<String>any())).thenReturn(ofResult);
        assertSame(client, clientUseCaseImpl.getClientByCpf("Cpf"));
        verify(clientRepository).identifyClientByCpf(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ClientUseCaseImpl#getClientByCpf(String)}
     */
    @Test
    public void testGetClientByCpf2() {
        Optional<Client> emptyResult = Optional.empty();
        when(clientRepository.identifyClientByCpf(Mockito.<String>any())).thenReturn(emptyResult);
        assertThrows(ClientNotFoundException.class, () -> clientUseCaseImpl.getClientByCpf("Cpf"));
        verify(clientRepository).identifyClientByCpf(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ClientUseCaseImpl#getClientByCpf(String)}
     */
    @Test
    public void testGetClientByCpf3() {
        when(clientRepository.identifyClientByCpf(Mockito.<String>any()))
                .thenThrow(new ClientNotFoundException("An error occurred"));
        assertThrows(ClientNotFoundException.class, () -> clientUseCaseImpl.getClientByCpf("Cpf"));
        verify(clientRepository).identifyClientByCpf(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ClientUseCaseImpl#saveClient(Client)}
     */
    @Test
    public void testSaveClient() {
        when(clientRepository.registerClient(Mockito.<Client>any())).thenReturn(new Client());
        Client client = new Client();
        assertSame(client, clientUseCaseImpl.saveClient(client));
        verify(clientRepository).registerClient(Mockito.<Client>any());
    }

    /**
     * Method under test: {@link ClientUseCaseImpl#saveClient(Client)}
     */
    @Test
    public void testSaveClient2() {
        when(clientRepository.registerClient(Mockito.<Client>any()))
                .thenThrow(new ClientNotFoundException("An error occurred"));
        assertThrows(ClientNotFoundException.class, () -> clientUseCaseImpl.saveClient(new Client()));
        verify(clientRepository).registerClient(Mockito.<Client>any());
    }
}

