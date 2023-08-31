package br.com.fiap.api.pedidos.entrypoint.controller.dto.response;

import static org.junit.Assert.assertSame;

import br.com.fiap.api.pedidos.core.Product;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

public class ProductResponseTest {
    @MockBean
    private Product product;

    /**
     * Method under test: {@link ProductResponse#fromEntityToResponse(Product)}
     */
    @Test
    public void testFromEntityToResponse() {
        assertSame(product, ProductResponse.fromEntityToResponse(product).product());
    }
}

