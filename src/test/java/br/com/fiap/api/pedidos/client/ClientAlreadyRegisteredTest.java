package br.com.fiap.api.pedidos.client;

import br.com.fiap.api.pedidos.core.exception.ClientAlreadyRegistered;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientAlreadyRegisteredTest {

    @Test
    void testExceptionMessage() {
        // Given
        String errorMessage = "Client already registered";

        // When
        ClientAlreadyRegistered exception = new ClientAlreadyRegistered(errorMessage);

        // Then
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testResponseStatus() {
        // Given
        ClientAlreadyRegistered exception = new ClientAlreadyRegistered("Client already registered");

        // Then
        assertEquals("Client already registered", exception.getMessage());
    }
}
