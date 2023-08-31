package br.com.fiap.api.pedidos.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.Test;

public class ProductTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Product#Product()}
     *   <li>{@link Product#setCategory(String)}
     *   <li>{@link Product#setPrice(BigDecimal)}
     *   <li>{@link Product#setProductDesc(String)}
     *   <li>{@link Product#setProductId(UUID)}
     *   <li>{@link Product#setProductName(String)}
     *   <li>{@link Product#getCategory()}
     *   <li>{@link Product#getPrice()}
     *   <li>{@link Product#getProductDesc()}
     *   <li>{@link Product#getProductId()}
     *   <li>{@link Product#getProductName()}
     * </ul>
     */
    @Test
    public void testConstructor() {
        Product actualProduct = new Product();
        actualProduct.setCategory("Category");
        actualProduct.setPrice(BigDecimal.valueOf(1L));
        actualProduct.setProductDesc("Product Desc");
        UUID productId = UUID.randomUUID();
        actualProduct.setProductId(productId);
        actualProduct.setProductName("Product Name");
        String actualCategory = actualProduct.getCategory();
        BigDecimal actualPrice = actualProduct.getPrice();
        String actualProductDesc = actualProduct.getProductDesc();
        UUID actualProductId = actualProduct.getProductId();
        assertEquals("Category", actualCategory);
        assertSame(actualPrice.ONE, actualPrice);
        assertEquals("Product Desc", actualProductDesc);
        assertSame(productId, actualProductId);
        assertEquals("Product Name", actualProduct.getProductName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Product#Product(UUID, String, String, BigDecimal, String)}
     *   <li>{@link Product#setCategory(String)}
     *   <li>{@link Product#setPrice(BigDecimal)}
     *   <li>{@link Product#setProductDesc(String)}
     *   <li>{@link Product#setProductId(UUID)}
     *   <li>{@link Product#setProductName(String)}
     *   <li>{@link Product#getCategory()}
     *   <li>{@link Product#getPrice()}
     *   <li>{@link Product#getProductDesc()}
     *   <li>{@link Product#getProductId()}
     *   <li>{@link Product#getProductName()}
     * </ul>
     */
    @Test
    public void testConstructor2() {
        UUID productId = UUID.randomUUID();
        Product actualProduct = new Product(productId, "Product Name", "Product Desc", BigDecimal.valueOf(1L),
                "Category");
        actualProduct.setCategory("Category");
        actualProduct.setPrice(BigDecimal.valueOf(1L));
        actualProduct.setProductDesc("Product Desc");
        UUID productId2 = UUID.randomUUID();
        actualProduct.setProductId(productId2);
        actualProduct.setProductName("Product Name");
        String actualCategory = actualProduct.getCategory();
        BigDecimal actualPrice = actualProduct.getPrice();
        String actualProductDesc = actualProduct.getProductDesc();
        UUID actualProductId = actualProduct.getProductId();
        assertEquals("Category", actualCategory);
        assertSame(actualPrice.ONE, actualPrice);
        assertEquals("Product Desc", actualProductDesc);
        assertSame(productId2, actualProductId);
        assertEquals("Product Name", actualProduct.getProductName());
    }
}

