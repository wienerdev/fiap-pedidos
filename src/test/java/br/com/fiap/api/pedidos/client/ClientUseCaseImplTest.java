package br.com.fiap.api.pedidos.client;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ClientRepository;
import br.com.fiap.api.pedidos.core.exception.ClientNotFoundException;
import br.com.fiap.api.pedidos.core.usecase.impl.client.ClientUseCaseImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientUseCaseImplTest {

    @Mock
    private ClientRepository clientRepository;

    private ClientUseCaseImpl clientUseCase;
    UUID idClient = UUID.randomUUID();
    String cpf = "01374050067";
    Client client = new Client(idClient, cpf, "John Doe", "john.doe@example.com", "Street 01", "92389382938");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientUseCase = new ClientUseCaseImpl(clientRepository);
    }

    @Test
    void testGetClientByCpf() {
        // Given
        when(clientRepository.identifyClientByCpf(cpf)).thenReturn(Optional.of(client));

        // When
        Client result = clientUseCase.getClientByCpf(cpf);

        // Then
        assertEquals(client, result);
        verify(clientRepository, times(1)).identifyClientByCpf(cpf);
    }

    @Test
    void testGetClientByCpf_ClientNotFound() {
        when(clientRepository.identifyClientByCpf(cpf)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(ClientNotFoundException.class, () -> clientUseCase.getClientByCpf(cpf));
        verify(clientRepository, times(1)).identifyClientByCpf(cpf);
    }

    @Test
    void testSaveClient() {

        // When
        Client result = clientUseCase.saveClient(client);

        // Then
        assertEquals(client, result);
        verify(clientRepository, times(1)).registerClient(client);
    }
}