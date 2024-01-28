package br.com.fiap.api.pedidos.product;

import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.usecase.ProductUseCase;
import br.com.fiap.api.pedidos.entrypoint.controller.ProductController;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.UpdateProductRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
class ProductControllerTest {

    @Mock
    private ProductUseCase productGateway;

    private ProductController productController;
    UUID idSave = UUID.randomUUID();
    UUID idSaveList1 = UUID.randomUUID();
    UUID idSaveList2 = UUID.randomUUID();
    Product product = new Product(idSave, "Laptop", "Powerful laptop", BigDecimal.valueOf(1000), "Category1");
    List<Product> products;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productGateway);
        products = Arrays.asList(
                new Product(idSaveList1, "Product1", "produto teste1", new BigDecimal(100), "Category1"),
                new Product(idSaveList1, "Product2", "produto teste2", new BigDecimal(200), "Category2")
        );
        productGateway.saveProduct(new Product(idSaveList1, "Product1", "produto teste1", new BigDecimal(100), "Category1"));
        productGateway.saveProduct(new Product(idSaveList2, "Product2", "produto teste2", new BigDecimal(200), "Category2"));
    }

    @Test
    void testGetAll() {
        // Given
        List<ProductResponse> productList = products.stream()
                .map(product -> ProductResponse.fromEntityToResponse(product))
                .collect(Collectors.toList());
        when(productGateway.getAllProducts()).thenReturn(products);

        // When
        ResponseEntity<BaseResponse<List<ProductResponse>>> response = productController.getAll();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody().getResponse());
        verify(productGateway, times(1)).getAllProducts();
    }

    @Test
    void testGetAllByCategory() {
        // Given
        String category = "electronics";
        List<ProductResponse> productList = products.stream()
                .map(product -> ProductResponse.fromEntityToResponse(product))
                .collect(Collectors.toList());
        when(productGateway.getByCategory(category)).thenReturn(products);

        // When
        ResponseEntity<BaseResponse<Iterable<ProductResponse>>> response = productController.getAll(category);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody().getResponse());
        verify(productGateway, times(1)).getByCategory(category);
    }

    @Test
    void testGetById() {
        // Given
        ProductResponse productResponse = new ProductResponse(product);
        when(productGateway.getProductById(idSave)).thenReturn(Optional.of(productResponse.product()));

        // When
        ResponseEntity<BaseResponse<ProductResponse>> response = productController.getById(idSave);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals(productResponse, response.getBody().getData());
        verify(productGateway, times(1)).getProductById(idSave);
    }

    @Test
    void testCreate() {
        // Given
        CreateProductRequest createRequest = new CreateProductRequest("Laptop", "Powerful laptop", BigDecimal.valueOf(1000), "Category1");
        ProductResponse productResponse = new ProductResponse(createRequest.fromRequestProduct());
        when(productGateway.saveProduct(createRequest.fromRequestProduct())).thenReturn(productResponse.product());

        // When
        ResponseEntity<BaseResponse<ProductResponse>> response = productController.create(createRequest);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testUpdate() {
        // Given
        UpdateProductRequest updateRequest = new UpdateProductRequest(idSave, "Laptop", "Powerful laptop", BigDecimal.valueOf(1000), "Category1");
        ProductResponse productResponse = new ProductResponse(product);
        when(productGateway.updateProduct(product)).thenReturn(productResponse.product());

        // When
        ResponseEntity<BaseResponse<ProductResponse>> response = productController.update(updateRequest);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productResponse.product().getProductId(), response.getBody().getResponse().product().getProductId());
    }

    @Test
    void testDelete() {

        // When
        ResponseEntity<BaseResponse> response = productController.delete(idSave);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productGateway, times(1)).deleteProduct(idSave);
    }
}