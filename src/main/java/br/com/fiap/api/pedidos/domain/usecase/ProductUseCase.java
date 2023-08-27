package br.com.fiap.api.pedidos.domain.usecase;

import br.com.fiap.api.pedidos.domain.Product;
import br.com.fiap.api.pedidos.domain.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.domain.dto.response.ProductResponse;
import br.com.fiap.api.pedidos.domain.port.repository.ProductRepositoryPort;
import br.com.fiap.api.pedidos.domain.port.usecase.ProductUseCasePort;

import java.util.List;
import java.util.UUID;

public class ProductUseCase implements ProductUseCasePort {

    private final ProductRepositoryPort productRepository;


    public ProductUseCase(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public BaseResponse<Iterable<ProductResponse>> getAllProducts() {
        return new BaseResponse<>(
                true,
                productRepository.getAll().stream()
                        .map(product -> ProductResponse.fromEntityToResponse(product))
                        .toList());
    }

    @Override
    public BaseResponse<Iterable<ProductResponse>> getByCategory(String category) {
        return new BaseResponse<>(
                true,
                productRepository.getByCategory(category).stream()
                        .map(product -> ProductResponse.fromEntityToResponse(product))
                        .toList()
        );
    }

    @Override
    public BaseResponse<ProductResponse> getProductById(UUID id) {
        return new BaseResponse<>(
                true,
                ProductResponse.fromEntityToResponse(productRepository.getById(id))
        );
    }

    @Override
    public BaseResponse<ProductResponse> saveProduct(CreateProductRequest request) {
        Product entity = CreateProductRequest.fromRequestProduct(request);
        return new BaseResponse<>(
                true,
                ProductResponse.fromEntityToResponse(productRepository.save(entity))
        );
    }

    @Override
    public BaseResponse<ProductResponse> updateProduct(UpdateProductRequest request) {
        Product entity = UpdateProductRequest.fromRequestProduct(request);
        return new BaseResponse<>(
                true,
                ProductResponse.fromEntityToResponse(productRepository.update(entity))
        );
    }

    @Override
    public BaseResponse deleteProduct(UUID id) {
        try {
            productRepository.delete(id);
            return new BaseResponse(
                    true,
                    null
            );
        } catch (Exception e) {
            return new BaseResponse(
                    false,
                    e.getMessage()
            );
        }
    }
}
