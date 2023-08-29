package br.com.fiap.api.pedidos.core.dataprovider.repository;

import br.com.fiap.api.pedidos.core.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    List<Product> getAll();
    List<Product> getByCategory(String category);
    Optional<Product> getById(UUID id);
    List<Product> getAllById(List<UUID> productIds);
    Product save(Product product);
    Product update(Product product);
    void delete(UUID id);

}
