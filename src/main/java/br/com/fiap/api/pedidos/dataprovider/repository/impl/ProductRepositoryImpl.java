package br.com.fiap.api.pedidos.dataprovider.repository.impl;

import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ProductRepository;
import br.com.fiap.api.pedidos.dataprovider.repository.ProductRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductRepositoryJpa productRepository;

    public ProductRepositoryImpl(ProductRepositoryJpa productRepository) {
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
    public Optional<Product> getById(UUID id) {
        Optional<ProductEntity> productEntity = this.productRepository.findById(id);
        return Optional.ofNullable(productEntity.map(ProductEntity::toProduct).orElse(null));

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
