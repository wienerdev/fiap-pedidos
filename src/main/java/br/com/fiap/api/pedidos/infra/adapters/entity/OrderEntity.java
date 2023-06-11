package br.com.fiap.api.pedidos.infra.adapters.entity;

import br.com.fiap.api.pedidos.domain.Order;
import br.com.fiap.api.pedidos.domain.Product;
import br.com.fiap.api.pedidos.infra.enumeration.OrderStatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "order")
public class OrderEntity {

    @Id
    private UUID orderId;
    private String customerOrder;
    private Boolean active;
    private OrderStatusEnum orderStatus;
    private List<Product> orderProducts; // TODO -> Arrumar esse BO
    private Double orderPrice;

    public OrderEntity() {
    }

    public OrderEntity(Order order) {
        this.orderId = order.getOrderId();
        this.customerOrder = order.getCustomerOrder();
        this.active = order.getActive();
        this.orderStatus = order.getOrderStatus();
        this.orderProducts = order.getOrderProducts();
        this.orderPrice = order.getOrderPrice();
    }

    public Order toOrder() {
        return new Order(this.orderId,
                this.customerOrder,
                this.active,
                this.orderStatus,
                this.orderProducts,
                this.orderPrice);
    }
}


