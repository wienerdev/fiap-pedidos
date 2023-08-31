package br.com.fiap.api.pedidos.dataprovider.repository.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {ProductEntity.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductEntityTest {
    @Autowired
    private ProductEntity productEntity;

    /**
     * Method under test: {@link ProductEntity#ProductEntity()}
     */
    @Test
    public void testConstructor() {
        ProductEntity actualProductEntity = new ProductEntity();
        assertNull(actualProductEntity.getCategory());
        assertNull(actualProductEntity.getProductName());
        assertNull(actualProductEntity.getPrice());
        assertNull(actualProductEntity.getProductId());
        assertNull(actualProductEntity.getProductDesc());
        Product toProductResult = actualProductEntity.toProduct();
        assertNull(toProductResult.getPrice());
        assertNull(toProductResult.getCategory());
        assertNull(toProductResult.getProductName());
        assertNull(toProductResult.getProductDesc());
        assertNull(toProductResult.getProductId());
    }

    /**
     * Method under test: {@link ProductEntity#ProductEntity(UUID, String, String, BigDecimal, String)}
     */
    @Test
    public void testConstructor2() {
        UUID productId = UUID.randomUUID();
        BigDecimal price = BigDecimal.valueOf(1L);
        ProductEntity actualProductEntity = new ProductEntity(productId, "Product Name", "Product Desc", price,
                "Category");

        assertEquals("Category", actualProductEntity.getCategory());
        assertEquals("Product Name", actualProductEntity.getProductName());
        BigDecimal expectedPrice = price.ONE;
        BigDecimal price2 = actualProductEntity.getPrice();
        assertSame(expectedPrice, price2);
        UUID productId2 = actualProductEntity.getProductId();
        assertSame(productId, productId2);
        assertEquals("Product Desc", actualProductEntity.getProductDesc());
        assertEquals(4, productId2.version());
        assertEquals(2, productId2.variant());
        assertEquals(1, price2.signum());
        assertEquals(0, price2.scale());
        Product toProductResult = actualProductEntity.toProduct();
        assertEquals("Product Desc", toProductResult.getProductDesc());
        assertSame(price2, toProductResult.getPrice());
        assertEquals("Category", toProductResult.getCategory());
        assertEquals("Product Name", toProductResult.getProductName());
        assertEquals("1", price2.toString());
        assertSame(productId2, toProductResult.getProductId());
    }

    /**
     * Method under test: {@link ProductEntity#ProductEntity(Product)}
     */
    @Test
    public void testConstructor3() {
        ProductEntity actualProductEntity = new ProductEntity(new Product());
        assertNull(actualProductEntity.getCategory());
        assertNull(actualProductEntity.getProductName());
        assertNull(actualProductEntity.getPrice());
        assertNull(actualProductEntity.getProductId());
        assertNull(actualProductEntity.getProductDesc());
    }

    /**
     * Method under test: {@link ProductEntity#ProductEntity(Product)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testConstructor4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.Product.getProductId()" because "product" is null
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.ProductEntity.<init>(ProductEntity.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        new ProductEntity(null);
    }

    /**
     * Method under test: {@link ProductEntity#ProductEntity(Product)}
     */
    @Test
    public void testConstructor5() {
        Product product = mock(Product.class);
        when(product.getCategory()).thenReturn("Category");
        when(product.getProductDesc()).thenReturn("Product Desc");
        when(product.getProductName()).thenReturn("Product Name");
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        when(product.getPrice()).thenReturn(valueOfResult);
        UUID randomUUIDResult = UUID.randomUUID();
        when(product.getProductId()).thenReturn(randomUUIDResult);
        ProductEntity actualProductEntity = new ProductEntity(product);
        assertEquals("Category", actualProductEntity.getCategory());
        assertEquals("Product Name", actualProductEntity.getProductName());
        BigDecimal expectedPrice = valueOfResult.ONE;
        BigDecimal price = actualProductEntity.getPrice();
        assertSame(expectedPrice, price);
        assertSame(randomUUIDResult, actualProductEntity.getProductId());
        assertEquals("Product Desc", actualProductEntity.getProductDesc());
        assertEquals("1", price.toString());
        verify(product).getCategory();
        verify(product).getProductDesc();
        verify(product).getProductName();
        verify(product).getPrice();
        verify(product).getProductId();
    }

    /**
     * Method under test: {@link ProductEntity#toProduct()}
     */
    @Test
    public void testToProduct() {
        Product actualToProductResult = (new ProductEntity()).toProduct();
        assertNull(actualToProductResult.getCategory());
        assertNull(actualToProductResult.getProductName());
        assertNull(actualToProductResult.getProductId());
        assertNull(actualToProductResult.getProductDesc());
        assertNull(actualToProductResult.getPrice());
    }

    /**
     * Method under test: {@link ProductEntity#toProduct()}
     */
    @Test
    public void testToProduct2() {
        Product product = mock(Product.class);
        when(product.getCategory()).thenReturn("Category");
        when(product.getProductDesc()).thenReturn("Product Desc");
        when(product.getProductName()).thenReturn("Product Name");
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        when(product.getPrice()).thenReturn(valueOfResult);
        UUID randomUUIDResult = UUID.randomUUID();
        when(product.getProductId()).thenReturn(randomUUIDResult);
        Product actualToProductResult = (new ProductEntity(product)).toProduct();
        assertEquals("Category", actualToProductResult.getCategory());
        assertEquals("Product Name", actualToProductResult.getProductName());
        assertSame(randomUUIDResult, actualToProductResult.getProductId());
        assertEquals("Product Desc", actualToProductResult.getProductDesc());
        BigDecimal expectedPrice = valueOfResult.ONE;
        BigDecimal price = actualToProductResult.getPrice();
        assertSame(expectedPrice, price);
        assertEquals("1", price.toString());
        verify(product).getCategory();
        verify(product).getProductDesc();
        verify(product).getProductName();
        verify(product).getPrice();
        verify(product).getProductId();
    }

    /**
     * Method under test: {@link ProductEntity#toProductEntityList(List)}
     */
    @Test
    public void testToProductEntityList() {
        List<ProductEntity> actualToProductEntityListResult = ProductEntity.toProductEntityList(new ArrayList<>());
        assertTrue(actualToProductEntityListResult.isEmpty());
    }

    /**
     * Method under test: {@link ProductEntity#toProductEntityList(List)}
     */
    @Test
    public void testToProductEntityList2() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product());
        List<ProductEntity> actualToProductEntityListResult = ProductEntity.toProductEntityList(products);
        assertEquals(1, actualToProductEntityListResult.size());
    }

    /**
     * Method under test: {@link ProductEntity#toProductEntityList(List)}
     */
    @Test
    public void testToProductEntityList3() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        List<ProductEntity> actualToProductEntityListResult = ProductEntity.toProductEntityList(products);
        assertEquals(2, actualToProductEntityListResult.size());
    }

    /**
     * Method under test: {@link ProductEntity#toProductEntityList(List)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testToProductEntityList4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.Product.getProductId()" because "product" is null
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.ProductEntity.<init>(ProductEntity.java:45)
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.ProductEntity.toProductEntityList(ProductEntity.java:59)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Product> products = new ArrayList<>();
        products.add(null);
        ProductEntity.toProductEntityList(products);
    }

    /**
     * Method under test: {@link ProductEntity#toProductEntityList(List)}
     */
    @Test
    public void testToProductEntityList5() {
        Product product = mock(Product.class);
        when(product.getCategory()).thenReturn("Category");
        when(product.getProductDesc()).thenReturn("Product Desc");
        when(product.getProductName()).thenReturn("Product Name");
        when(product.getPrice()).thenReturn(BigDecimal.valueOf(1L));
        when(product.getProductId()).thenReturn(UUID.randomUUID());

        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        List<ProductEntity> actualToProductEntityListResult = ProductEntity.toProductEntityList(products);
        assertEquals(1, actualToProductEntityListResult.size());
        verify(product).getCategory();
        verify(product).getProductDesc();
        verify(product).getProductName();
        verify(product).getPrice();
        verify(product).getProductId();
    }
}

