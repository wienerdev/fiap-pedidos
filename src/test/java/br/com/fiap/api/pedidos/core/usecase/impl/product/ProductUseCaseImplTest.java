package br.com.fiap.api.pedidos.core.usecase.impl.product;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ProductRepository;
import br.com.fiap.api.pedidos.dataprovider.repository.ProductRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.ProductRepositoryImpl;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {ProductUseCaseImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductUseCaseImplTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductUseCaseImpl productUseCaseImpl;

    /**
     * Method under test: {@link ProductUseCaseImpl#ProductUseCaseImpl(ProductRepository)}
     */
    @Test
    public void testConstructor() {
        assertTrue((new ProductUseCaseImpl(new ProductRepositoryImpl(mock(ProductRepositoryJpa.class)))).getAllProducts()
                .isEmpty());
    }

    /**
     * Method under test: {@link ProductUseCaseImpl#getAllProducts()}
     */
    @Test
    public void testGetAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.getAll()).thenReturn(productList);
        List<Product> actualAllProducts = productUseCaseImpl.getAllProducts();
        assertSame(productList, actualAllProducts);
        assertTrue(actualAllProducts.isEmpty());
        verify(productRepository).getAll();
    }

    /**
     * Method under test: {@link ProductUseCaseImpl#getByCategory(String)}
     */
    @Test
    public void testGetByCategory() {
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.getByCategory(Mockito.<String>any())).thenReturn(productList);
        List<Product> actualByCategory = productUseCaseImpl.getByCategory("Category");
        assertSame(productList, actualByCategory);
        assertTrue(actualByCategory.isEmpty());
        verify(productRepository).getByCategory(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ProductUseCaseImpl#getProductById(UUID)}
     */
    @Test
    public void testGetProductById() {
        Optional<Product> ofResult = Optional.of(new Product());
        when(productRepository.getById(Mockito.<UUID>any())).thenReturn(ofResult);
        Optional<Product> actualProductById = productUseCaseImpl.getProductById(UUID.randomUUID());
        assertSame(ofResult, actualProductById);
        assertTrue(actualProductById.isPresent());
        verify(productRepository).getById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link ProductUseCaseImpl#saveProduct(Product)}
     */
    @Test
    public void testSaveProduct() {
        Product product = new Product();
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product);
        assertSame(product, productUseCaseImpl.saveProduct(new Product()));
        verify(productRepository).save(Mockito.<Product>any());
    }

    /**
     * Method under test: {@link ProductUseCaseImpl#updateProduct(Product)}
     */
    @Test
    public void testUpdateProduct() {
        when(productRepository.update(Mockito.<Product>any())).thenReturn(new Product());
        Product product = new Product();
        assertSame(product, productUseCaseImpl.updateProduct(product));
        verify(productRepository).update(Mockito.<Product>any());
    }

    /**
     * Method under test: {@link ProductUseCaseImpl#deleteProduct(UUID)}
     */
    @Test
    public void testDeleteProduct() {
        doNothing().when(productRepository).delete(Mockito.<UUID>any());
        BaseResponse actualDeleteProductResult = productUseCaseImpl.deleteProduct(UUID.randomUUID());
        assertNull(actualDeleteProductResult.getResponse());
        assertTrue(actualDeleteProductResult.isSuccess());
        verify(productRepository).delete(Mockito.<UUID>any());
    }
}

