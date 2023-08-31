package br.com.fiap.api.pedidos.dataprovider.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.dataprovider.repository.ProductRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ProductEntity;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.UUID;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {ProductRepositoryImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductRepositoryImplTest {
    @Autowired
    private ProductRepositoryImpl productRepositoryImpl;

    @MockBean
    private ProductRepositoryJpa productRepositoryJpa;

    /**
     * Method under test: {@link ProductRepositoryImpl#getAll()}
     */
    @Test
    public void testGetAll() {
        when(productRepositoryJpa.findAll()).thenReturn(new ArrayList<>());
        assertTrue(productRepositoryImpl.getAll().isEmpty());
        verify(productRepositoryJpa).findAll();
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#getAll()}
     */
    @Test
    public void testGetAll2() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        BigDecimal price = BigDecimal.valueOf(1L);
        productEntity.setPrice(price);
        productEntity.setProductDesc("Product Desc");
        UUID productId = UUID.randomUUID();
        productEntity.setProductId(productId);
        productEntity.setProductName("Product Name");

        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(productEntity);
        when(productRepositoryJpa.findAll()).thenReturn(productEntityList);
        List<Product> actualAll = productRepositoryImpl.getAll();
        assertEquals(1, actualAll.size());
        Product getResult = actualAll.get(0);
        assertEquals("Category", getResult.getCategory());
        assertEquals("Product Name", getResult.getProductName());
        assertSame(productId, getResult.getProductId());
        assertEquals("Product Desc", getResult.getProductDesc());
        BigDecimal expectedPrice = price.ONE;
        BigDecimal price2 = getResult.getPrice();
        assertSame(expectedPrice, price2);
        assertEquals("1", price2.toString());
        verify(productRepositoryJpa).findAll();
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#getAll()}
     */
    @Test
    public void testGetAll3() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        BigDecimal price = BigDecimal.valueOf(1L);
        productEntity.setPrice(price);
        productEntity.setProductDesc("Product Desc");
        UUID productId = UUID.randomUUID();
        productEntity.setProductId(productId);
        productEntity.setProductName("Product Name");

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setCategory("42");
        productEntity2.setPrice(BigDecimal.valueOf(1L));
        productEntity2.setProductDesc("42");
        UUID productId2 = UUID.randomUUID();
        productEntity2.setProductId(productId2);
        productEntity2.setProductName("42");

        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(productEntity2);
        productEntityList.add(productEntity);
        when(productRepositoryJpa.findAll()).thenReturn(productEntityList);
        List<Product> actualAll = productRepositoryImpl.getAll();
        assertEquals(2, actualAll.size());
        Product getResult = actualAll.get(0);
        assertEquals("42", getResult.getProductName());
        Product getResult2 = actualAll.get(1);
        assertEquals("Product Name", getResult2.getProductName());
        assertSame(productId, getResult2.getProductId());
        assertEquals("Product Desc", getResult2.getProductDesc());
        assertEquals("42", getResult.getCategory());
        assertEquals("42", getResult.getProductDesc());
        assertSame(productId2, getResult.getProductId());
        BigDecimal expectedPrice = price.ONE;
        BigDecimal price2 = getResult.getPrice();
        assertSame(expectedPrice, price2);
        assertEquals("Category", getResult2.getCategory());
        assertEquals("1", getResult2.getPrice().toString());
        assertEquals("1", price2.toString());
        verify(productRepositoryJpa).findAll();
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#getAllById(List)}
     */
    @Test
    public void testGetAllById() {
        when(productRepositoryJpa.findAllById(Mockito.<Iterable<UUID>>any())).thenReturn(new ArrayList<>());
        assertTrue(productRepositoryImpl.getAllById(new ArrayList<>()).isEmpty());
        verify(productRepositoryJpa).findAllById(Mockito.<Iterable<UUID>>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#getAllById(List)}
     */
    @Test
    public void testGetAllById2() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        BigDecimal price = BigDecimal.valueOf(1L);
        productEntity.setPrice(price);
        productEntity.setProductDesc("Product Desc");
        UUID productId = UUID.randomUUID();
        productEntity.setProductId(productId);
        productEntity.setProductName("Product Name");

        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(productEntity);
        when(productRepositoryJpa.findAllById(Mockito.<Iterable<UUID>>any())).thenReturn(productEntityList);
        List<Product> actualAllById = productRepositoryImpl.getAllById(new ArrayList<>());
        assertEquals(1, actualAllById.size());
        Product getResult = actualAllById.get(0);
        assertEquals("Category", getResult.getCategory());
        assertEquals("Product Name", getResult.getProductName());
        assertSame(productId, getResult.getProductId());
        assertEquals("Product Desc", getResult.getProductDesc());
        BigDecimal expectedPrice = price.ONE;
        BigDecimal price2 = getResult.getPrice();
        assertSame(expectedPrice, price2);
        assertEquals("1", price2.toString());
        verify(productRepositoryJpa).findAllById(Mockito.<Iterable<UUID>>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#getAllById(List)}
     */
    @Test
    public void testGetAllById3() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        BigDecimal price = BigDecimal.valueOf(1L);
        productEntity.setPrice(price);
        productEntity.setProductDesc("Product Desc");
        UUID productId = UUID.randomUUID();
        productEntity.setProductId(productId);
        productEntity.setProductName("Product Name");

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setCategory("42");
        productEntity2.setPrice(BigDecimal.valueOf(1L));
        productEntity2.setProductDesc("42");
        UUID productId2 = UUID.randomUUID();
        productEntity2.setProductId(productId2);
        productEntity2.setProductName("42");

        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(productEntity2);
        productEntityList.add(productEntity);
        when(productRepositoryJpa.findAllById(Mockito.<Iterable<UUID>>any())).thenReturn(productEntityList);
        List<Product> actualAllById = productRepositoryImpl.getAllById(new ArrayList<>());
        assertEquals(2, actualAllById.size());
        Product getResult = actualAllById.get(0);
        assertEquals("42", getResult.getProductName());
        Product getResult2 = actualAllById.get(1);
        assertEquals("Product Name", getResult2.getProductName());
        assertSame(productId, getResult2.getProductId());
        assertEquals("Product Desc", getResult2.getProductDesc());
        assertEquals("42", getResult.getCategory());
        assertEquals("42", getResult.getProductDesc());
        assertSame(productId2, getResult.getProductId());
        BigDecimal expectedPrice = price.ONE;
        BigDecimal price2 = getResult.getPrice();
        assertSame(expectedPrice, price2);
        assertEquals("Category", getResult2.getCategory());
        assertEquals("1", getResult2.getPrice().toString());
        assertEquals("1", price2.toString());
        verify(productRepositoryJpa).findAllById(Mockito.<Iterable<UUID>>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#getAllById(List)}
     */
    @Test
    public void testGetAllById4() {
        when(productRepositoryJpa.findAllById(Mockito.<Iterable<UUID>>any())).thenReturn(new ArrayList<>());

        ArrayList<UUID> productIds = new ArrayList<>();
        productIds.add(UUID.randomUUID());
        assertTrue(productRepositoryImpl.getAllById(productIds).isEmpty());
        verify(productRepositoryJpa).findAllById(Mockito.<Iterable<UUID>>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#getAllById(List)}
     */
    @Test
    public void testGetAllById5() {
        when(productRepositoryJpa.findAllById(Mockito.<Iterable<UUID>>any())).thenReturn(new ArrayList<>());

        ArrayList<UUID> productIds = new ArrayList<>();
        productIds.add(UUID.randomUUID());
        productIds.add(UUID.randomUUID());
        assertTrue(productRepositoryImpl.getAllById(productIds).isEmpty());
        verify(productRepositoryJpa).findAllById(Mockito.<Iterable<UUID>>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#getByCategory(String)}
     */
    @Test
    public void testGetByCategory() {
        when(productRepositoryJpa.findAllByCategory(Mockito.<String>any())).thenReturn(new ArrayList<>());
        assertTrue(productRepositoryImpl.getByCategory("Category").isEmpty());
        verify(productRepositoryJpa).findAllByCategory(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#getByCategory(String)}
     */
    @Test
    public void testGetByCategory2() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        BigDecimal price = BigDecimal.valueOf(1L);
        productEntity.setPrice(price);
        productEntity.setProductDesc("Product Desc");
        UUID productId = UUID.randomUUID();
        productEntity.setProductId(productId);
        productEntity.setProductName("Product Name");

        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(productEntity);
        when(productRepositoryJpa.findAllByCategory(Mockito.<String>any())).thenReturn(productEntityList);
        List<Product> actualByCategory = productRepositoryImpl.getByCategory("Category");
        assertEquals(1, actualByCategory.size());
        Product getResult = actualByCategory.get(0);
        assertEquals("Category", getResult.getCategory());
        assertEquals("Product Name", getResult.getProductName());
        assertSame(productId, getResult.getProductId());
        assertEquals("Product Desc", getResult.getProductDesc());
        BigDecimal expectedPrice = price.ONE;
        BigDecimal price2 = getResult.getPrice();
        assertSame(expectedPrice, price2);
        assertEquals("1", price2.toString());
        verify(productRepositoryJpa).findAllByCategory(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#getByCategory(String)}
     */
    @Test
    public void testGetByCategory3() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        BigDecimal price = BigDecimal.valueOf(1L);
        productEntity.setPrice(price);
        productEntity.setProductDesc("Product Desc");
        UUID productId = UUID.randomUUID();
        productEntity.setProductId(productId);
        productEntity.setProductName("Product Name");

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setCategory("42");
        productEntity2.setPrice(BigDecimal.valueOf(1L));
        productEntity2.setProductDesc("42");
        UUID productId2 = UUID.randomUUID();
        productEntity2.setProductId(productId2);
        productEntity2.setProductName("42");

        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(productEntity2);
        productEntityList.add(productEntity);
        when(productRepositoryJpa.findAllByCategory(Mockito.<String>any())).thenReturn(productEntityList);
        List<Product> actualByCategory = productRepositoryImpl.getByCategory("Category");
        assertEquals(2, actualByCategory.size());
        Product getResult = actualByCategory.get(0);
        assertEquals("42", getResult.getProductName());
        Product getResult2 = actualByCategory.get(1);
        assertEquals("Product Name", getResult2.getProductName());
        assertSame(productId, getResult2.getProductId());
        assertEquals("Product Desc", getResult2.getProductDesc());
        assertEquals("42", getResult.getCategory());
        assertEquals("42", getResult.getProductDesc());
        assertSame(productId2, getResult.getProductId());
        BigDecimal expectedPrice = price.ONE;
        BigDecimal price2 = getResult.getPrice();
        assertSame(expectedPrice, price2);
        assertEquals("Category", getResult2.getCategory());
        assertEquals("1", getResult2.getPrice().toString());
        assertEquals("1", price2.toString());
        verify(productRepositoryJpa).findAllByCategory(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#getById(UUID)}
     */
    @Test
    public void testGetById() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        BigDecimal price = BigDecimal.valueOf(1L);
        productEntity.setPrice(price);
        productEntity.setProductDesc("Product Desc");
        UUID productId = UUID.randomUUID();
        productEntity.setProductId(productId);
        productEntity.setProductName("Product Name");
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productRepositoryJpa.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        Optional<Product> actualById = productRepositoryImpl.getById(UUID.randomUUID());
        assertTrue(actualById.isPresent());
        Product getResult = actualById.get();
        assertEquals("Category", getResult.getCategory());
        assertEquals("Product Name", getResult.getProductName());
        assertSame(productId, getResult.getProductId());
        assertEquals("Product Desc", getResult.getProductDesc());
        BigDecimal expectedPrice = price.ONE;
        BigDecimal price2 = getResult.getPrice();
        assertSame(expectedPrice, price2);
        assertEquals("1", price2.toString());
        verify(productRepositoryJpa).findById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#getById(UUID)}
     */
    @Test
    public void testGetById2() {
        ProductEntity productEntity = mock(ProductEntity.class);
        when(productEntity.toProduct()).thenReturn(new Product());
        doNothing().when(productEntity).setCategory(Mockito.<String>any());
        doNothing().when(productEntity).setPrice(Mockito.<BigDecimal>any());
        doNothing().when(productEntity).setProductDesc(Mockito.<String>any());
        doNothing().when(productEntity).setProductId(Mockito.<UUID>any());
        doNothing().when(productEntity).setProductName(Mockito.<String>any());
        productEntity.setCategory("Category");
        productEntity.setPrice(BigDecimal.valueOf(1L));
        productEntity.setProductDesc("Product Desc");
        productEntity.setProductId(UUID.randomUUID());
        productEntity.setProductName("Product Name");
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productRepositoryJpa.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        assertTrue(productRepositoryImpl.getById(UUID.randomUUID()).isPresent());
        verify(productRepositoryJpa).findById(Mockito.<UUID>any());
        verify(productEntity).toProduct();
        verify(productEntity).setCategory(Mockito.<String>any());
        verify(productEntity).setPrice(Mockito.<BigDecimal>any());
        verify(productEntity).setProductDesc(Mockito.<String>any());
        verify(productEntity).setProductId(Mockito.<UUID>any());
        verify(productEntity).setProductName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#save(Product)}
     */
    @Test
    public void testSave() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        BigDecimal price = BigDecimal.valueOf(1L);
        productEntity.setPrice(price);
        productEntity.setProductDesc("Product Desc");
        UUID productId = UUID.randomUUID();
        productEntity.setProductId(productId);
        productEntity.setProductName("Product Name");
        when(productRepositoryJpa.save(Mockito.<ProductEntity>any())).thenReturn(productEntity);
        Product actualSaveResult = productRepositoryImpl.save(new Product());
        assertEquals("Category", actualSaveResult.getCategory());
        assertEquals("Product Name", actualSaveResult.getProductName());
        assertSame(productId, actualSaveResult.getProductId());
        assertEquals("Product Desc", actualSaveResult.getProductDesc());
        BigDecimal expectedPrice = price.ONE;
        BigDecimal price2 = actualSaveResult.getPrice();
        assertSame(expectedPrice, price2);
        assertEquals("1", price2.toString());
        verify(productRepositoryJpa).save(Mockito.<ProductEntity>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#save(Product)}
     */
    @Test
    public void testSave2() {
        ProductEntity productEntity = mock(ProductEntity.class);
        Product product = new Product();
        when(productEntity.toProduct()).thenReturn(product);
        doNothing().when(productEntity).setCategory(Mockito.<String>any());
        doNothing().when(productEntity).setPrice(Mockito.<BigDecimal>any());
        doNothing().when(productEntity).setProductDesc(Mockito.<String>any());
        doNothing().when(productEntity).setProductId(Mockito.<UUID>any());
        doNothing().when(productEntity).setProductName(Mockito.<String>any());
        productEntity.setCategory("Category");
        productEntity.setPrice(BigDecimal.valueOf(1L));
        productEntity.setProductDesc("Product Desc");
        productEntity.setProductId(UUID.randomUUID());
        productEntity.setProductName("Product Name");
        when(productRepositoryJpa.save(Mockito.<ProductEntity>any())).thenReturn(productEntity);
        assertSame(product, productRepositoryImpl.save(new Product()));
        verify(productRepositoryJpa).save(Mockito.<ProductEntity>any());
        verify(productEntity).toProduct();
        verify(productEntity).setCategory(Mockito.<String>any());
        verify(productEntity).setPrice(Mockito.<BigDecimal>any());
        verify(productEntity).setProductDesc(Mockito.<String>any());
        verify(productEntity).setProductId(Mockito.<UUID>any());
        verify(productEntity).setProductName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#save(Product)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSave3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.Product.getProductId()" because "product" is null
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.ProductEntity.<init>(ProductEntity.java:45)
        //       at br.com.fiap.api.pedidos.dataprovider.repository.impl.ProductRepositoryImpl.save(ProductRepositoryImpl.java:49)
        //   See https://diff.blue/R013 to resolve this issue.

        ProductEntity productEntity = mock(ProductEntity.class);
        when(productEntity.toProduct()).thenReturn(new Product());
        doNothing().when(productEntity).setCategory(Mockito.<String>any());
        doNothing().when(productEntity).setPrice(Mockito.<BigDecimal>any());
        doNothing().when(productEntity).setProductDesc(Mockito.<String>any());
        doNothing().when(productEntity).setProductId(Mockito.<UUID>any());
        doNothing().when(productEntity).setProductName(Mockito.<String>any());
        productEntity.setCategory("Category");
        productEntity.setPrice(BigDecimal.valueOf(1L));
        productEntity.setProductDesc("Product Desc");
        productEntity.setProductId(UUID.randomUUID());
        productEntity.setProductName("Product Name");
        when(productRepositoryJpa.save(Mockito.<ProductEntity>any())).thenReturn(productEntity);
        productRepositoryImpl.save(null);
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#update(Product)}
     */
    @Test
    public void testUpdate() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        BigDecimal price = BigDecimal.valueOf(1L);
        productEntity.setPrice(price);
        productEntity.setProductDesc("Product Desc");
        UUID productId = UUID.randomUUID();
        productEntity.setProductId(productId);
        productEntity.setProductName("Product Name");
        when(productRepositoryJpa.save(Mockito.<ProductEntity>any())).thenReturn(productEntity);
        Product actualUpdateResult = productRepositoryImpl.update(new Product());
        assertEquals("Category", actualUpdateResult.getCategory());
        assertEquals("Product Name", actualUpdateResult.getProductName());
        assertSame(productId, actualUpdateResult.getProductId());
        assertEquals("Product Desc", actualUpdateResult.getProductDesc());
        BigDecimal expectedPrice = price.ONE;
        BigDecimal price2 = actualUpdateResult.getPrice();
        assertSame(expectedPrice, price2);
        assertEquals("1", price2.toString());
        verify(productRepositoryJpa).save(Mockito.<ProductEntity>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#update(Product)}
     */
    @Test
    public void testUpdate2() {
        ProductEntity productEntity = mock(ProductEntity.class);
        Product product = new Product();
        when(productEntity.toProduct()).thenReturn(product);
        doNothing().when(productEntity).setCategory(Mockito.<String>any());
        doNothing().when(productEntity).setPrice(Mockito.<BigDecimal>any());
        doNothing().when(productEntity).setProductDesc(Mockito.<String>any());
        doNothing().when(productEntity).setProductId(Mockito.<UUID>any());
        doNothing().when(productEntity).setProductName(Mockito.<String>any());
        productEntity.setCategory("Category");
        productEntity.setPrice(BigDecimal.valueOf(1L));
        productEntity.setProductDesc("Product Desc");
        productEntity.setProductId(UUID.randomUUID());
        productEntity.setProductName("Product Name");
        when(productRepositoryJpa.save(Mockito.<ProductEntity>any())).thenReturn(productEntity);
        assertSame(product, productRepositoryImpl.update(new Product()));
        verify(productRepositoryJpa).save(Mockito.<ProductEntity>any());
        verify(productEntity).toProduct();
        verify(productEntity).setCategory(Mockito.<String>any());
        verify(productEntity).setPrice(Mockito.<BigDecimal>any());
        verify(productEntity).setProductDesc(Mockito.<String>any());
        verify(productEntity).setProductId(Mockito.<UUID>any());
        verify(productEntity).setProductName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#update(Product)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testUpdate3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "br.com.fiap.api.pedidos.core.Product.getProductId()" because "product" is null
        //       at br.com.fiap.api.pedidos.dataprovider.repository.entity.ProductEntity.<init>(ProductEntity.java:45)
        //       at br.com.fiap.api.pedidos.dataprovider.repository.impl.ProductRepositoryImpl.update(ProductRepositoryImpl.java:55)
        //   See https://diff.blue/R013 to resolve this issue.

        ProductEntity productEntity = mock(ProductEntity.class);
        when(productEntity.toProduct()).thenReturn(new Product());
        doNothing().when(productEntity).setCategory(Mockito.<String>any());
        doNothing().when(productEntity).setPrice(Mockito.<BigDecimal>any());
        doNothing().when(productEntity).setProductDesc(Mockito.<String>any());
        doNothing().when(productEntity).setProductId(Mockito.<UUID>any());
        doNothing().when(productEntity).setProductName(Mockito.<String>any());
        productEntity.setCategory("Category");
        productEntity.setPrice(BigDecimal.valueOf(1L));
        productEntity.setProductDesc("Product Desc");
        productEntity.setProductId(UUID.randomUUID());
        productEntity.setProductName("Product Name");
        when(productRepositoryJpa.save(Mockito.<ProductEntity>any())).thenReturn(productEntity);
        productRepositoryImpl.update(null);
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#delete(UUID)}
     */
    @Test
    public void testDelete() {
        doNothing().when(productRepositoryJpa).deleteById(Mockito.<UUID>any());
        productRepositoryImpl.delete(UUID.randomUUID());
        verify(productRepositoryJpa).deleteById(Mockito.<UUID>any());
    }
}

