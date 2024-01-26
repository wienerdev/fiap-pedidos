package br.com.fiap.api.pedidos.product;

import br.com.fiap.api.pedidos.core.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void testGettersAndSetters() {
        // Given
        UUID productId = UUID.randomUUID();
        String productName = "Example Product";
        String productDesc = "Description of the product";
        BigDecimal price = new BigDecimal("99.99");
        String category = "Electronics";

        // When
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductDesc(productDesc);
        product.setPrice(price);
        product.setCategory(category);

        // Then
        assertEquals(productId, product.getProductId());
        assertEquals(productName, product.getProductName());
        assertEquals(productDesc, product.getProductDesc());
        assertEquals(price, product.getPrice());
        assertEquals(category, product.getCategory());
    }

    @Test
    void testParameterizedConstructor() {
        // Given
        UUID productId = UUID.randomUUID();
        String productName = "Example Product";
        String productDesc = "Description of the product";
        BigDecimal price = new BigDecimal("99.99");
        String category = "Electronics";

        // When
        Product product = new Product(productId, productName, productDesc, price, category);

        // Then
        assertEquals(productId, product.getProductId());
        assertEquals(productName, product.getProductName());
        assertEquals(productDesc, product.getProductDesc());
        assertEquals(price, product.getPrice());
        assertEquals(category, product.getCategory());
    }

}
