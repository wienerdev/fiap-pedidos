package br.com.fiap.api.pedidos.core.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ProductNotFoundExceptionTest {
    /**
     * Method under test: {@link ProductNotFoundException#ProductNotFoundException(String)}
     */
    @Test
    public void testConstructor() {
        ProductNotFoundException actualProductNotFoundException = new ProductNotFoundException("An error occurred");
        assertNull(actualProductNotFoundException.getCause());
        assertEquals(0, actualProductNotFoundException.getSuppressed().length);
        assertEquals("An error occurred", actualProductNotFoundException.getMessage());
        assertEquals("An error occurred", actualProductNotFoundException.getLocalizedMessage());
    }
}

