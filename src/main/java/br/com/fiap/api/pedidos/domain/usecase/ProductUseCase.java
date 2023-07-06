package br.com.fiap.api.pedidos.domain.usecase;

import br.com.fiap.api.pedidos.domain.Product;
import br.com.fiap.api.pedidos.domain.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateProductRequest;
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
    public List<ProductResponse> getAllProducts() {
        return productRepository.getAll().stream()
                .map(product -> ProductResponse.fromEntityToResponse(product))
                .toList();
    }

    @Override
    public List<ProductResponse> getByCategory(String category) {
        return productRepository.getByCategory(category).stream()
                .map(product -> ProductResponse.fromEntityToResponse(product))
                .toList();
    }

    @Override
    public ProductResponse getProductById(UUID id) {
        return ProductResponse.fromEntityToResponse(productRepository.getById(id));
    }

    @Override
    public ProductResponse saveProduct(CreateProductRequest request) {
        Product entity = CreateProductRequest.fromRequestProduct(request);
        return ProductResponse.fromEntityToResponse(productRepository.save(entity));
    }

    @Override
    public ProductResponse updateProduct(UpdateProductRequest request) {
        Product entity = UpdateProductRequest.fromRequestProduct(request);
        return ProductResponse.fromEntityToResponse( productRepository.update(entity));
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.delete(id);
    }
}
