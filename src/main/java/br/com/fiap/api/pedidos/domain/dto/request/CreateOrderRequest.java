package br.com.fiap.api.pedidos.domain.dto.request;

import br.com.fiap.api.pedidos.domain.dto.response.ProductResponse;
import br.com.fiap.api.pedidos.infra.enumeration.OrderStatusEnum;

import java.util.List;

public class CreateOrderRequest {

    private String customerOrder;
    private Boolean active;
    private OrderStatusEnum orderStatus;
    private List<ProductResponse> orderProducts;

    public CreateOrderRequest(String customerOrder, Boolean active, OrderStatusEnum orderStatus, List<ProductResponse> orderProducts) {
        this.customerOrder = customerOrder;
        this.active = active;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
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

    public List<ProductResponse> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<ProductResponse> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
