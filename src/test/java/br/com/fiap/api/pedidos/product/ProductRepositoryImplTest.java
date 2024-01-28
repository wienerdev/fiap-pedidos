package br.com.fiap.api.pedidos.product;

import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.dataprovider.repository.ProductRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ProductEntity;
import br.com.fiap.api.pedidos.dataprovider.repository.impl.ProductRepositoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@ActiveProfiles("teste")
class ProductRepositoryImplTest {

    @Mock
    private ProductRepositoryJpa productRepositoryJpa;

    private ProductRepositoryImpl productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productRepository = new ProductRepositoryImpl(productRepositoryJpa);
    }

    @Test
    void testGetAll() {
        // Given
        List<Product> expectedProducts = Arrays.asList(
                new Product(UUID.randomUUID(), "Product1", "Description1", BigDecimal.valueOf(100), "Category1"),
                new Product(UUID.randomUUID(), "Product2", "Description2", BigDecimal.valueOf(200), "Category2")
        );
        List<ProductEntity> productEntities = expectedProducts.stream()
                .map(ProductEntity::new)
                .collect(Collectors.toList());
        when(productRepositoryJpa.findAll()).thenReturn(productEntities);

        // When
        List<Product> result = productRepository.getAll();

        // Then
        verify(productRepositoryJpa, times(1)).findAll();
    }


    @Test
    void testGetAllById() {
        // Given
        List<UUID> productIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
        List<Product> expectedProducts = Arrays.asList(
                new Product(UUID.randomUUID(), "Product1", "Description1", BigDecimal.valueOf(100), "Category1"),
                new Product(UUID.randomUUID(), "Product2", "Description2", BigDecimal.valueOf(200), "Category2")
        );
        List<ProductEntity> productEntities = expectedProducts.stream()
                .map(ProductEntity::new)
                .collect(Collectors.toList());
        when(productRepositoryJpa.findAllById(productIds)).thenReturn(productEntities);

        // When
        List<Product> result = productRepository.getAllById(productIds);

        // Then
        verify(productRepositoryJpa, times(1)).findAllById(productIds);
    }

    @Test
    void testGetByCategory() {
        // Given
        String category = "Category1";
        List<Product> expectedProducts = Arrays.asList(
                new Product(UUID.randomUUID(), "Product1", "Description1", BigDecimal.valueOf(100), "Category1"),
                new Product(UUID.randomUUID(), "Product3", "Description3", BigDecimal.valueOf(300), "Category1")
        );
        List<ProductEntity> productEntities = expectedProducts.stream()
                .map(ProductEntity::new)
                .collect(Collectors.toList());
        when(productRepositoryJpa.findAllByCategory(category)).thenReturn(productEntities);

        // When
        List<Product> result = productRepository.getByCategory(category);

        // Then
        verify(productRepositoryJpa, times(1)).findAllByCategory(category);
    }

    @Test
    void testGetById() {
        // Given
        UUID id = UUID.randomUUID();
        Product expectedProduct = new Product(id, "Product1", "Description1", BigDecimal.valueOf(100), "Category1");
        ProductEntity productEntity = new ProductEntity(expectedProduct);
        Optional<ProductEntity> optionalProductEntity = Optional.of(productEntity);
        when(productRepositoryJpa.findById(id)).thenReturn(optionalProductEntity);

        // When
        Optional<Product> result = productRepository.getById(id);

        // Then
        assertEquals(Optional.of(expectedProduct).get().getProductId(), result.get().getProductId());
        verify(productRepositoryJpa, times(1)).findById(id);
    }

    @Test
    void testSave() {
        // Given
        Product product = new Product(UUID.randomUUID(), "Product1", "Description1", BigDecimal.valueOf(100), "Category1");
        ProductEntity productEntity = new ProductEntity(product);
        when(productRepositoryJpa.save(productEntity)).thenReturn(productEntity);

    }

    @Test
    void testUpdate() {
        // Given
        Product product = new Product(UUID.randomUUID(), "Product1", "Description1", BigDecimal.valueOf(100), "Category1");
        ProductEntity productEntity = new ProductEntity(product);
        when(productRepositoryJpa.save(productEntity)).thenReturn(productEntity);

    }

    @Test
    void testDelete() {
        // Given
        UUID id = UUID.randomUUID();

        // When
        productRepository.delete(id);

        // Then
        verify(productRepositoryJpa, times(1)).deleteById(id);
    }

}