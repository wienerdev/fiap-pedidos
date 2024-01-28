package br.com.fiap.api.pedidos.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.api.pedidos.core.Product;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@Transactional
@ActiveProfiles("teste")
class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        UUID productId = UUID.randomUUID();
        String productName = "Test Product";
        String productDesc = "This is a test product";
        BigDecimal price = BigDecimal.valueOf(9.99);
        String category = "Test Category";

        product = new Product(productId, productName, productDesc, price, category);
    }

    @Test
    void testGetProductId() {
        UUID expected = product.getProductId();
        assertEquals(expected, product.getProductId());
    }

    @Test
    void testSetProductId() {
        UUID newProductId = UUID.randomUUID();
        product.setProductId(newProductId);
        assertEquals(newProductId, product.getProductId());
    }

    @Test
    void testGetProductName() {
        String expected = product.getProductName();
        assertEquals(expected, product.getProductName());
    }

    @Test
    void testSetProductName() {
        String newProductName = "New Test Product";
        product.setProductName(newProductName);
        assertEquals(newProductName, product.getProductName());
    }

    @Test
    void testGetProductDesc() {
        String expected = product.getProductDesc();
        assertEquals(expected, product.getProductDesc());
    }

    @Test
    void testSetProductDesc() {
        String newProductDesc = "New test product description";
        product.setProductDesc(newProductDesc);
        assertEquals(newProductDesc, product.getProductDesc());
    }

    @Test
    void testGetPrice() {
        BigDecimal expected = product.getPrice();
        assertEquals(expected, product.getPrice());
    }

    @Test
    void testSetPrice() {
        BigDecimal newPrice = BigDecimal.valueOf(19.99);
        product.setPrice(newPrice);
        assertEquals(newPrice, product.getPrice());
    }

    @Test
    void testGetCategory() {
        String expected = product.getCategory();
        assertEquals(expected, product.getCategory());
    }

    @Test
    void testSetCategory() {
        String newCategory = "New Test Category";
        product.setCategory(newCategory);
        assertEquals(newCategory, product.getCategory());
    }
}