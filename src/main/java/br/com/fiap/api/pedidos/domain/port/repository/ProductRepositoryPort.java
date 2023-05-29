package br.com.fiap.api.pedidos.domain.port.repository;

import br.com.fiap.api.pedidos.domain.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepositoryPort {

    List<Product> getAll();
    Product getById(UUID id);
    Product save(Product product);
    Product update(Product product);
    void delete(UUID id);

}
