package br.com.fiap.api.pedidos.entrypoint.controller.dto.request;

import static org.junit.Assert.assertEquals;

import br.com.fiap.api.pedidos.core.Product;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {CreateProductRequest.class, String.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateProductRequestTest {
    @MockBean
    private BigDecimal bigDecimal;

    @Autowired
    private CreateProductRequest createProductRequest;

    /**
     * Method under test: {@link CreateProductRequest#fromRequestProduct()}
     */
    @Test
    public void testFromRequestProduct() {
        Product actualFromRequestProductResult = createProductRequest.fromRequestProduct();
        assertEquals("", actualFromRequestProductResult.getCategory());
        assertEquals("", actualFromRequestProductResult.getProductName());
        assertEquals("", actualFromRequestProductResult.getProductDesc());
    }
}

