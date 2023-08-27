package br.com.fiap.api.pedidos.domain.port.usecase;

import br.com.fiap.api.pedidos.domain.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.domain.dto.response.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductUseCasePort {

    BaseResponse<Iterable<ProductResponse>> getAllProducts();
    BaseResponse<Iterable<ProductResponse>> getByCategory(String category);
    BaseResponse<ProductResponse> getProductById(UUID id);
    BaseResponse<ProductResponse> saveProduct(CreateProductRequest request);
    BaseResponse<ProductResponse> updateProduct(UpdateProductRequest request);
    BaseResponse deleteProduct(UUID id);


}
