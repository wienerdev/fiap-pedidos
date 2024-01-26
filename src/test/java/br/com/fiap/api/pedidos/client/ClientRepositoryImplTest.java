package br.com.fiap.api.pedidos.client;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.exception.ClientAlreadyRegistered;
import br.com.fiap.api.pedidos.core.exception.ClientNotFoundException;
import br.com.fiap.api.pedidos.dataprovider.repository.ClientRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.ClientRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientRepositoryImplTest {

    @Test
    void getAllByClientCpf() {
        // Given
        String cpf = "12345678900";
        ClientRepositoryJpa clientRepository = mock(ClientRepositoryJpa.class);
        ClientEntity clientEntity = new ClientEntity(UUID.randomUUID(), cpf, "John Doe", "john.doe@example.com");
        when(clientRepository.findByClientCpf(cpf)).thenReturn(Optional.of(clientEntity));

        ClientRepositoryImpl clientRepositoryImpl = new ClientRepositoryImpl(clientRepository);

        // When
        Optional<Client> result = clientRepositoryImpl.identifyClientByCpf(cpf);

        // Then
        assertTrue(result.isPresent());
        assertEquals(clientEntity.toClient(), result.get());
        verify(clientRepository, times(1)).findByClientCpf(cpf);
    }

    @Test
    void getAllByClientCpf_ClientNotFound() {
        // Given
        String cpf = "12345678900";
        ClientRepositoryJpa clientRepository = mock(ClientRepositoryJpa.class);
        when(clientRepository.findByClientCpf(cpf)).thenReturn(Optional.empty());

        ClientRepositoryImpl clientRepositoryImpl = new ClientRepositoryImpl(clientRepository);

        // When & Then
        assertThrows(ClientNotFoundException.class, () -> clientRepositoryImpl.identifyClientByCpf(cpf));
        verify(clientRepository, times(1)).findByClientCpf(cpf);
    }

    @Test
    void testRegisterClient_ClientNotRegistered() {
        // Given
        ClientRepositoryJpa clientRepository = mock(ClientRepositoryJpa.class);
        Client client = new Client(UUID.randomUUID(), "12345678900", "John Doe", "john.doe@example.com");
        when(clientRepository.findByClientCpf(client.getClientCpf())).thenReturn(Optional.empty());
        when(clientRepository.save(any(ClientEntity.class))).thenAnswer(invocation -> {
            ClientEntity savedEntity = invocation.getArgument(0);
            savedEntity.setClientId(UUID.randomUUID());
            return savedEntity;
        });

        ClientRepositoryImpl clientRepositoryImpl = new ClientRepositoryImpl(clientRepository);

        // When
        Client result = clientRepositoryImpl.registerClient(client);

        // Then
        assertNotNull(result.getClientId());
        assertEquals(client.getClientCpf(), result.getClientCpf());
        assertEquals(client.getClientName(), result.getClientName());
        assertEquals(client.getClientEmail(), result.getClientEmail());
        verify(clientRepository, times(1)).findByClientCpf(client.getClientCpf());
        verify(clientRepository, times(1)).save(any(ClientEntity.class));
    }

    @Test
    void testRegisterClient_ClientAlreadyRegistered() {
        // Given
        ClientRepositoryJpa clientRepository = mock(ClientRepositoryJpa.class);
        Client client = new Client(UUID.randomUUID(), "12345678900", "John Doe", "john.doe@example.com");
        when(clientRepository.findByClientCpf(client.getClientCpf())).thenReturn(Optional.of(new ClientEntity()));

        ClientRepositoryImpl clientRepositoryImpl = new ClientRepositoryImpl(clientRepository);

        // When & Then
        assertThrows(ClientAlreadyRegistered.class, () -> clientRepositoryImpl.registerClient(client));
        verify(clientRepository, times(1)).findByClientCpf(client.getClientCpf());
        verify(clientRepository, never()).save(any(ClientEntity.class));
    }
}
