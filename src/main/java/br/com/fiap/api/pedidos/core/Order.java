package br.com.fiap.api.pedidos.core;

import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private UUID orderId;
    private String customerOrder;
    private Boolean active;
    private OrderStatusEnum orderStatus;
    private List<Product> orderProducts;
    private List<UUID> orderProductIds;
    private Double orderPrice;
    private Client client;

    public Order() {

    }

    public Order(UUID orderId, String customerOrder, Boolean active, OrderStatusEnum orderStatus, List<Product> orderProducts,List<UUID> orderProductIds, Double orderPrice,Client client) {
        this.orderId = orderId;
        this.customerOrder = customerOrder;
        this.active = active;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
        this.orderProductIds = orderProductIds;
        this.orderPrice = orderPrice;
        this.client = client;
    }
//    public List<UUID> getProductsIds(List<Product> orderProducts){
//    List<UUID> uuidList = new ArrayList<>();
//        for (Product orderList : orderProducts) {
//            uuidList.add(orderList.getProductId());
//        }
//        return uuidList;
//    }

    public List<UUID> getOrderProductIds() {
        return orderProductIds;
    }

    public void setOrderProductIds(List<UUID> orderProductIds) {
        this.orderProductIds = orderProductIds;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(customerOrder, order.customerOrder) && Objects.equals(active, order.active) && orderStatus == order.orderStatus && Objects.equals(orderProducts, order.orderProducts) && Objects.equals(orderPrice, order.orderPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerOrder, active, orderStatus, orderProducts, orderPrice);
    }
}
