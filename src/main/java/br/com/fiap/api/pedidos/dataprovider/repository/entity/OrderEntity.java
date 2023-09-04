package br.com.fiap.api.pedidos.dataprovider.repository.entity;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.ClientDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "order_food")
public class OrderEntity {

    @Id
    private UUID orderId;
    private Boolean isPaymentReceived;
    private OrderStatusEnum orderStatus;
    @ManyToMany
    @JoinTable(
            name = "orderProducts",
            joinColumns = @JoinColumn(name = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "productId")
    )
    private List<ProductEntity> orderProducts;
    @ElementCollection
    private List<UUID> orderProductIds;
    private Double orderPrice;
    @OneToOne(optional = true)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    public OrderEntity() {
    }

    public OrderEntity(Order order) {
        this.orderId = order.getOrderId();
        this.isPaymentReceived = order.getPaymentReceived();
        this.orderStatus = order.getOrderStatus();
        this.orderProducts = order.getOrderProducts().stream()
                .map(product -> new ProductEntity(product.getProductId(), product.getProductName(), product.getProductDesc(),
                        product.getPrice(), product.getCategory()))
                .toList(); this.orderPrice = order.getOrderPrice();
        this.clientEntity = order.getClient() != null ?  ClientDto.fromClientToClientEntity(order.getClient()) : null;
    }


    public Order toOrder() {
        return new Order(this.orderId,
                this.isPaymentReceived,
                toProductList(this.orderProducts),
                this.orderProductIds,
                this.orderPrice,
                this.clientEntity != null ?this.clientEntity.toClient() : null);
    }

    public static List<Product> toProductList(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(ProductEntity::toProduct)
                .collect(Collectors.toList());
    }

}


