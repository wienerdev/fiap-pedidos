package br.com.fiap.api.pedidos.core.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ClientAlreadyRegisteredTest {
    /**
     * Method under test: {@link ClientAlreadyRegistered#ClientAlreadyRegistered(String)}
     */
    @Test
    public void testConstructor() {
        ClientAlreadyRegistered actualClientAlreadyRegistered = new ClientAlreadyRegistered("Not all who wander are lost");
        assertNull(actualClientAlreadyRegistered.getCause());
        assertEquals(0, actualClientAlreadyRegistered.getSuppressed().length);
        assertEquals("Not all who wander are lost", actualClientAlreadyRegistered.getMessage());
        assertEquals("Not all who wander are lost", actualClientAlreadyRegistered.getLocalizedMessage());
    }
}

