package br.com.fiap.api.pedidos.infra.adapters.repository;

import br.com.fiap.api.pedidos.domain.Order;
import br.com.fiap.api.pedidos.infra.adapters.entity.OrderEntity;
import br.com.fiap.api.pedidos.infra.enumeration.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity o SET o.orderStatus = ?1 WHERE o.orderId = ?2")
    void updateByOrderStatusAndOrderId(OrderStatusEnum orderStatus, UUID orderId);
}
