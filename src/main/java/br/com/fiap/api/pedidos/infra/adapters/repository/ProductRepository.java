package br.com.fiap.api.pedidos.infra.adapters.repository;

import br.com.fiap.api.pedidos.infra.adapters.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
