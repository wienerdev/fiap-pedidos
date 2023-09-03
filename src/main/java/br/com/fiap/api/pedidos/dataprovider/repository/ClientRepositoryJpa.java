package br.com.fiap.api.pedidos.dataprovider.repository;

import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClientRepositoryJpa extends JpaRepository<ClientEntity, UUID> {

    Optional<ClientEntity> findByClientCpf(String cpf);

}
