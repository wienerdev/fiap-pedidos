package br.com.fiap.api.pedidos.client;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ClientRepository;
import br.com.fiap.api.pedidos.core.exception.ClientNotFoundException;
import br.com.fiap.api.pedidos.core.usecase.impl.client.ClientUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientUseCaseImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientUseCaseImpl clientUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetClientByCpf() {
        // Arrange
        String cpf = "01374050067";
        Client expectedClient = new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br");
        when(clientRepository.identifyClientByCpf(cpf)).thenReturn(Optional.of(expectedClient));

        // Act
        Client actualClient = clientUseCase.getClientByCpf(cpf);

        // Assert
        assertEquals(expectedClient, actualClient);
    }

    @Test
    public void testGetClientByCpf_ClientNotFound() {
        // Arrange
        String cpf = "98765432100";
        when(clientRepository.identifyClientByCpf(cpf)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ClientNotFoundException.class, () -> clientUseCase.getClientByCpf(cpf));
    }

    @Test
    public void testSaveClient() {
        // Arrange
        Client clientToSave = new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br");

        // Act
        Client savedClient = clientUseCase.saveClient(clientToSave);

        // Assert
        assertNotNull(savedClient);
        assertEquals(clientToSave, savedClient);

        // Verify that the repository's registerClient method was called
        verify(clientRepository, times(1)).registerClient(clientToSave);
    }
}