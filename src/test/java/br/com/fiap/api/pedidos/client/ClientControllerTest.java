package br.com.fiap.api.pedidos.client;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.usecase.ClientUseCase;
import br.com.fiap.api.pedidos.entrypoint.controller.ClientController;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.CreateClientRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.ClientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
@Transactional
@ActiveProfiles("teste")
class ClientControllerTest {

    @Mock
    private ClientUseCase clientUseCase;

    private ClientController clientController;

    UUID idClient = UUID.randomUUID();
    String cpf = "01374050067";
    Client client = new Client(idClient, cpf, "John Doe", "john.doe@example.com", "Street 01", "1921992");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientController = new ClientController(clientUseCase);
    }

    @Test
    void testGetByCpf() {
        // Given
        Client client = new Client(idClient, cpf, "John Doe", "john.doe@example.com", "Street 01", "1921992");
        when(clientUseCase.getClientByCpf(cpf)).thenReturn(client);

        // When
        ResponseEntity<BaseResponse<ClientResponse>> response = clientController.getByCpf(cpf);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(client.getClientId(), response.getBody().getResponse().clientId());
        verify(clientUseCase, times(1)).getClientByCpf(cpf);
    }


}