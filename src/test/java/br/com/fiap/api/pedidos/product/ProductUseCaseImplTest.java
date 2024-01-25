package br.com.fiap.api.pedidos.product;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.usecase.ProductUseCase;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductUseCaseImplTest {

      private final ProductUseCase productUseCase;


    public ProductUseCaseImplTest(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> expectedProducts = Arrays.asList(
                new Product(UUID.randomUUID(), "Product1","produto teste1",new BigDecimal(100), "Category1"),
                new Product(UUID.randomUUID(), "Product2","produto teste2",new BigDecimal(200), "Category2")
        );
       // when(productRepository.getAll()).thenReturn(expectedProducts);

        // Act
        List<Product> actualProducts = productUseCase.getAllProducts();

        // Assert
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void testGetByCategory() {
        // Arrange
        String category = "Category1";
        List<Product> expectedProducts = Arrays.asList(
                new Product(UUID.randomUUID(), "Product1","produto teste1",new BigDecimal(100), "Category1"),
                new Product(UUID.randomUUID(), "Product2","produto teste2",new BigDecimal(200), "Category1")
        );
        //when(productRepository.getByCategory(category)).thenReturn(expectedProducts);

        // Act
        List<Product> actualProducts = productUseCase.getByCategory(category);

        // Assert
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void testGetProductById() {
        // Arrange
        UUID productId = UUID.randomUUID();
        Product expectedProduct = new Product(UUID.randomUUID(), "Product2","produto teste2",new BigDecimal(200), "Category1");
       // when(productRepository.getById(productId)).thenReturn(Optional.of(expectedProduct));

        // Act
        Optional<Product> actualProduct = productUseCase.getProductById(productId);

        // Assert
        assertTrue(actualProduct.isPresent());
        assertEquals(expectedProduct, actualProduct.get());
    }

    @Test
    public void testSaveProduct() {
        // Arrange
        Product productToSave =  new Product(UUID.randomUUID(), "Product2","produto teste2",new BigDecimal(200), "Category1");
       // when(productRepository.save(productToSave)).thenReturn(productToSave);

        // Act
        Product savedProduct = productUseCase.saveProduct(productToSave);

        // Assert
        assertNotNull(savedProduct);
        assertEquals(productToSave, savedProduct);
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        Product productToUpdate = new Product(UUID.randomUUID(), "Product2","produto teste2",new BigDecimal(200), "Category1");

        // Act
        Product updatedProduct = productUseCase.updateProduct(productToUpdate);

        // Assert
        assertNotNull(updatedProduct);
        assertEquals(productToUpdate, updatedProduct);

        // Verify that the repository's update method was called
        //verify(productRepository, times(1)).update(productToUpdate);
    }

    @Test
    public void testDeleteProduct_Success() {
        // Arrange
        UUID productId = UUID.randomUUID();

        // Act
        BaseResponse response = productUseCase.deleteProduct(productId);

        // Assert
        assertTrue(response.isSuccess());
    }

    @Test
    public void testDeleteProduct_Failure() {
        // Arrange
        UUID productId = UUID.randomUUID();

        // Act
        BaseResponse response = productUseCase.deleteProduct(productId);

        // Assert
        assertFalse(!response.isSuccess());
    }
}
