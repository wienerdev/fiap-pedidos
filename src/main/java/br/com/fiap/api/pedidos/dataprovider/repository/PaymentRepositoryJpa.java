package br.com.fiap.api.pedidos.dataprovider.repository;

import br.com.fiap.api.pedidos.dataprovider.repository.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepositoryJpa extends JpaRepository<PaymentEntity, UUID>{
}
