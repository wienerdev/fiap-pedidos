package br.com.fiap.api.pedidos.dataprovider.repository;

import br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepositoryJpa extends JpaRepository<OrderEntity, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity o SET o.orderStatus = ?1 WHERE o.orderId = ?2")
    void updateByOrderStatusAndOrderId(OrderStatusEnum orderStatus, UUID orderId);

    @Transactional
    List<OrderEntity> getAllByClientEntityClientCpf(String cpf);

}
