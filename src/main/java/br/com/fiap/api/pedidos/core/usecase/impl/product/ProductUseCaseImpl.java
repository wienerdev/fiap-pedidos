package br.com.fiap.api.pedidos.core.usecase.impl.product;

import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.exception.ClientNotFoundException;
import br.com.fiap.api.pedidos.core.usecase.ProductUseCase;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductRepository productRepository;

    public ProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return  productRepository.getAll();
    }

    @Override
    public List<Product> getByCategory(String category) {
        return productRepository.getByCategory(category);
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productRepository.getById(id).orElseThrow(() -> new ClientNotFoundException("client not found")));
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        productRepository.update(product);
        return product;
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
