package br.com.fiap.api.pedidos.product;

import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ProductRepository;
import br.com.fiap.api.pedidos.core.usecase.ProductUseCase;
import br.com.fiap.api.pedidos.core.usecase.impl.product.ProductUseCaseImpl;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@ActiveProfiles("teste")
class ProductUseCaseImplTest {

    @Mock
    private ProductRepository productRepository;

    private ProductUseCase productUseCase;

    UUID idSave = UUID.randomUUID();
    UUID idSaveList1 = UUID.randomUUID();
    UUID idSaveList2 = UUID.randomUUID();
    Product product = new Product(idSave, "Laptop", "Powerful laptop", BigDecimal.valueOf(1000), "Category1");
    List<Product> products;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productUseCase = new ProductUseCaseImpl(productRepository);
        products = Arrays.asList(
                new Product(idSaveList1, "Product1", "produto teste1", new BigDecimal(100), "Category1"),
                new Product(idSaveList1, "Product2", "produto teste2", new BigDecimal(200), "Category2")
        );
        productUseCase.saveProduct(new Product(idSaveList1, "Product1", "produto teste1", new BigDecimal(100), "Category1"));
        productUseCase.saveProduct(new Product(idSaveList2, "Product2", "produto teste2", new BigDecimal(200), "Category2"));

    }

    @Test
    void testGetAllProducts() {
        // Given
        List<Product> productList = new ArrayList<>();
        when(productRepository.getAll()).thenReturn(productList);

        // When
        List<Product> result = productUseCase.getAllProducts();

        // Then
        assertEquals(productList, result);
        verify(productRepository, times(1)).getAll();
    }

    @Test
    void testGetByCategory() {
        // Given
        String category = "electronics";
        List<Product> productList = new ArrayList<>();
        when(productRepository.getByCategory(category)).thenReturn(productList);

        // When
        List<Product> result = productUseCase.getByCategory(category);

        // Then
        assertEquals(productList, result);
        verify(productRepository, times(1)).getByCategory(category);
    }

    @Test
    void testGetProductById() {
        // Given
        when(productRepository.getById(idSave)).thenReturn(Optional.of(product));

        // When
        Optional<Product> result = productUseCase.getProductById(idSave);

        // Then
        assertTrue(result.isPresent());
        assertEquals(product, result.get());
        verify(productRepository, times(1)).getById(idSave);
    }

    @Test
    void testSaveProduct() {
        // Given
        Product product = new Product(UUID.randomUUID(), "Laptop", "Powerful laptop", BigDecimal.valueOf(1000), "Category1");
        when(productRepository.save(product)).thenReturn(product);

        // When
        Product result = productUseCase.saveProduct(product);

        // Then
        assertEquals(product, result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProduct() {
        // When
        Product result = productUseCase.updateProduct(product);

        // Then
        assertEquals(product, result);
        verify(productRepository, times(1)).update(product);
    }

    @Test
    void testDeleteProduct() {

        // When
        BaseResponse result = productUseCase.deleteProduct(idSave);

        // Then
        assertTrue(result.isSuccess());
       // assertNull(result.getMessage());
        verify(productRepository, times(1)).delete(idSave);
    }
}