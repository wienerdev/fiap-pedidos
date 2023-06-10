package br.com.fiap.api.pedidos.domain.port.usecase;

import br.com.fiap.api.pedidos.domain.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.response.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductUseCasePort {

    List<ProductResponse> getAllProducts();
    List<ProductResponse> getByCategory(String category);
    ProductResponse getProductById(UUID id);
    ProductResponse saveProduct(CreateProductRequest request);
    ProductResponse updateProduct(UpdateProductRequest request);
    void deleteProduct(UUID id);


}
