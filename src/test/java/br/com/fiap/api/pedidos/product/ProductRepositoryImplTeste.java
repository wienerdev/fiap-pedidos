package br.com.fiap.api.pedidos.product;

import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ProductRepository;
import br.com.fiap.api.pedidos.dataprovider.repository.ProductRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ProductEntity;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.ProductRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductRepositoryImplTeste {

    List<ProductEntity> products = Arrays.asList(
            new ProductEntity(UUID.randomUUID(), "Product1","produto teste1",new BigDecimal(100), "Category1"),
            new ProductEntity(UUID.randomUUID(), "Product2","produto teste2",new BigDecimal(200), "Category2")
    );
    @Test
    void testGetAll() {
        // Given
        ProductRepositoryJpa productRepository = mock(ProductRepositoryJpa.class);
        when(productRepository.findAll()).thenReturn(products);

        ProductRepository productRepositoryImpl = new ProductRepositoryImpl(productRepository);

        // When
        List<Product> result = productRepositoryImpl.getAll();

        // Then
        assertEquals(products.size(), result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetAllById() {
        // Given
        ProductRepositoryJpa productRepository = mock(ProductRepositoryJpa.class);
        List<UUID> productIds = Collections.singletonList(UUID.randomUUID());
        List<ProductEntity> productEntities = Collections.singletonList(new ProductEntity());
        when(productRepository.findAllById(productIds)).thenReturn(productEntities);

        ProductRepository productRepositoryImpl = new ProductRepositoryImpl(productRepository);

        // When
        List<Product> result = productRepositoryImpl.getAllById(productIds);

        // Then
        assertEquals(productEntities.size(), result.size());
        verify(productRepository, times(1)).findAllById(productIds);
    }

    @Test
    void testGetByCategory() {
        // Given
        ProductRepositoryJpa productRepository = mock(ProductRepositoryJpa.class);
        String category = "Electronics";
        List<ProductEntity> productEntities = Collections.singletonList(new ProductEntity());
        when(productRepository.findAllByCategory(category)).thenReturn(productEntities);

        ProductRepository productRepositoryImpl = new ProductRepositoryImpl(productRepository);

        // When
        List<Product> result = productRepositoryImpl.getByCategory(category);

        // Then
        assertEquals(productEntities.size(), result.size());
        verify(productRepository, times(1)).findAllByCategory(category);
    }



    @Test
    void testGetById_ProductNotFound() {
        // Given
        UUID productId = UUID.randomUUID();
        ProductRepositoryJpa productRepository = mock(ProductRepositoryJpa.class);
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        ProductRepository productRepositoryImpl = new ProductRepositoryImpl(productRepository);

        // When & Then
        assertFalse(productRepositoryImpl.getById(productId).isPresent());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testSave() {
        // Given
        ProductRepositoryJpa productRepository = mock(ProductRepositoryJpa.class);
        Product product = new Product(UUID.randomUUID(), "Laptop", "Powerful laptop", BigDecimal.valueOf(1000), "Electronics");
        when(productRepository.save(any(ProductEntity.class))).thenAnswer(invocation -> {
            ProductEntity savedEntity = invocation.getArgument(0);
            savedEntity.setProductId(UUID.randomUUID());
            return savedEntity;
        });

        ProductRepository productRepositoryImpl = new ProductRepositoryImpl(productRepository);

        // When
        Product result = productRepositoryImpl.save(product);

        // Then
        assertNotNull(result.getProductId());
        assertEquals(product.getProductName(), result.getProductName());
        // Add more assertions as needed
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testUpdate() {
        // Given
        ProductRepositoryJpa productRepository = mock(ProductRepositoryJpa.class);
        Product product = new Product(UUID.randomUUID(), "Updated Laptop", "Powerful laptop", BigDecimal.valueOf(1200), "Electronics");
        when(productRepository.save(any(ProductEntity.class))).thenAnswer(invocation -> {
            ProductEntity savedEntity = invocation.getArgument(0);
            return savedEntity;
        });

        ProductRepository productRepositoryImpl = new ProductRepositoryImpl(productRepository);

        // When
        Product result = productRepositoryImpl.update(product);

        // Then
        assertEquals(product.getProductId(), result.getProductId());
        assertEquals(product.getProductName(), result.getProductName());
        // Add more assertions as needed
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testDelete() {
        // Given
        UUID productId = UUID.randomUUID();
        ProductRepositoryJpa productRepository = mock(ProductRepositoryJpa.class);

        ProductRepository productRepositoryImpl = new ProductRepositoryImpl(productRepository);

        // When
        productRepositoryImpl.delete(productId);

        // Then
        verify(productRepository, times(1)).deleteById(productId);
    }

    // Add tests for other methods as needed
}

