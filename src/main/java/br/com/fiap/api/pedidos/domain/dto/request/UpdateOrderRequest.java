package br.com.fiap.api.pedidos.domain.dto.request;

import br.com.fiap.api.pedidos.domain.Product;
import br.com.fiap.api.pedidos.infra.enumeration.OrderStatusEnum;
import jakarta.persistence.ManyToOne;

import java.util.List;
import java.util.UUID;

public class UpdateOrderRequest {

    private UUID orderId;
    private String customerOrder;
    private Boolean active;
    private OrderStatusEnum orderStatus;
    @ManyToOne
    private List<Product> orderProducts;
    private Double orderPrice;

    public UpdateOrderRequest(UUID orderId, String customerOrder, Boolean active, OrderStatusEnum orderStatus, List<Product> orderProducts, Double orderPrice) {
        this.orderId = orderId;
        this.customerOrder = customerOrder;
        this.active = active;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
        this.orderPrice = orderPrice;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(String customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
