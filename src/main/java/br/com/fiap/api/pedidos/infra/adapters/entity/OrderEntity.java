package br.com.fiap.api.pedidos.infra.adapters.entity;

import br.com.fiap.api.pedidos.domain.Order;
import br.com.fiap.api.pedidos.domain.dto.ClientDto;
import br.com.fiap.api.pedidos.infra.enumeration.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "order_food")
public class OrderEntity {

    @Id
    private UUID orderId;
    private String customerOrder;
    private Boolean active;
    private OrderStatusEnum orderStatus;
    @OneToMany
    private List<ProductEntity> orderProducts;
    private Double orderPrice;
    @OneToOne(optional = true)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;



    public OrderEntity() {
    }

    public OrderEntity(Order order) {
        this.orderId = order.getOrderId();
        this.customerOrder = order.getCustomerOrder();
        this.active = order.getActive();
        this.orderStatus = order.getOrderStatus();
        this.orderProducts = order.getOrderProducts();
        this.orderPrice = order.getOrderPrice();
        this.clientEntity = order.getClient() != null ?  ClientDto.fromClientToClientEntity(order.getClient()) : null;
    }


    public Order toOrder() {
        return new Order(this.orderId,
                this.customerOrder,
                this.active,
                this.orderStatus,
                this.orderProducts,
                this.orderPrice,
                this.clientEntity != null ?this.clientEntity.toClient() : null);
    }
}


