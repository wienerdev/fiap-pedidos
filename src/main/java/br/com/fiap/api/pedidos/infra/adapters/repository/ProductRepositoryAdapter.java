package br.com.fiap.api.pedidos.infra.adapters.repository;

import br.com.fiap.api.pedidos.domain.Product;
import br.com.fiap.api.pedidos.domain.port.repository.ProductRepositoryPort;
import br.com.fiap.api.pedidos.infra.adapters.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductRepository productRepository;

    public ProductRepositoryAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        List<ProductEntity> entities = this.productRepository.findAll();
        return entities.stream().map(ProductEntity::toProduct).toList();
    }

    @Override
    public List<Product> getAllById(List<UUID> productIds) {
        List<ProductEntity> entities = this.productRepository.findAllById(productIds);
        return entities.stream().map(ProductEntity::toProduct).toList();
    }

    @Override
    public List<Product> getByCategory(String category) {
        List<ProductEntity> entities = this.productRepository.findAllByCategory(category);
        return entities.stream().map(ProductEntity::toProduct).toList();
    }

    @Override
    public Product getById(UUID id) {
        Optional<ProductEntity> productEntity = this.productRepository.findById(id);

        return productEntity.map(ProductEntity::toProduct).orElse(null);
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = new ProductEntity(product);
        return productRepository.save(entity).toProduct();
    }

    @Override
    public Product update(Product product) {
        ProductEntity entity = new ProductEntity(product);
        return productRepository.save(entity).toProduct();
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }
}
