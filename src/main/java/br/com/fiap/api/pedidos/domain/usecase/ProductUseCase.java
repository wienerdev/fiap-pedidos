package br.com.fiap.api.pedidos.domain.usecase;

import br.com.fiap.api.pedidos.domain.Product;
import br.com.fiap.api.pedidos.domain.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.response.ProductResponse;
import br.com.fiap.api.pedidos.domain.port.repository.ProductRepositoryPort;
import br.com.fiap.api.pedidos.domain.port.usecase.ProductUseCasePort;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;

public class ProductUseCase implements ProductUseCasePort {

    private final ProductRepositoryPort productRepository;

    private final ModelMapper mapper;

    public ProductUseCase(ProductRepositoryPort productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.getAll().stream().map(product ->mapper.map(product, ProductResponse.class)).toList();
    }

    @Override
    public ProductResponse getProductById(UUID id) {
        return mapper.map(productRepository.getById(id), ProductResponse.class);
    }

    @Override
    public ProductResponse saveProduct(CreateProductRequest request) {
        Product entity = mapper.map(request, Product.class);
        entity.setProductId(UUID.randomUUID());
        productRepository.save(entity);
        return mapper.map(entity, ProductResponse.class);
    }

    @Override
    public ProductResponse updateProduct(UpdateProductRequest request) {
        Product entity = mapper.map(request, Product.class);
        productRepository.update(entity);
        return mapper.map(entity, ProductResponse.class);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.delete(id);
    }
}
