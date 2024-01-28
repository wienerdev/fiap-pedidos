package br.com.fiap.api.pedidos.client;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ClientRepository;
import br.com.fiap.api.pedidos.core.exception.ClientNotFoundException;
import br.com.fiap.api.pedidos.core.usecase.impl.client.ClientUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Profile("teste")
class ClientUseCaseImplTeste {

    private ClientRepository clientRepository;
    private ClientUseCaseImpl clientUseCase;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        clientUseCase = new ClientUseCaseImpl(clientRepository);
    }

    @Test
    void testGetClientByCpf() {
        // Given
        String cpf = "12345678900";
        Client client = new Client();
        when(clientRepository.identifyClientByCpf(cpf)).thenReturn(Optional.of(client));

        // When
        Client result = clientUseCase.getClientByCpf(cpf);

        // Then
        assertEquals(client, result);
        verify(clientRepository, times(1)).identifyClientByCpf(cpf);
    }

    @Test
    void testGetClientByCpfNotFound() {
        // Given
        String cpf = "12345678900";
        when(clientRepository.identifyClientByCpf(cpf)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(ClientNotFoundException.class, () -> clientUseCase.getClientByCpf(cpf));
        verify(clientRepository, times(1)).identifyClientByCpf(cpf);
    }

    @Test
    void testSaveClient() {
        // Given
        Client client = new Client();

        // When
        Client result = clientUseCase.saveClient(client);

        // Then
        assertEquals(client, result);
        verify(clientRepository, times(1)).registerClient(client);
    }
}