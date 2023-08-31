package br.com.fiap.api.pedidos.core.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ClientNotFoundExceptionTest {
    /**
     * Method under test: {@link ClientNotFoundException#ClientNotFoundException(String)}
     */
    @Test
    public void testConstructor() {
        ClientNotFoundException actualClientNotFoundException = new ClientNotFoundException("An error occurred");
        assertNull(actualClientNotFoundException.getCause());
        assertEquals(0, actualClientNotFoundException.getSuppressed().length);
        assertEquals("An error occurred", actualClientNotFoundException.getMessage());
        assertEquals("An error occurred", actualClientNotFoundException.getLocalizedMessage());
    }
}

