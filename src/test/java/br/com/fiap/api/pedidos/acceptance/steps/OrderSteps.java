package br.com.fiap.api.pedidos.acceptance.steps;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.OrderResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class OrderSteps {

    private Order order;
    private OrderResponse orderResponse;

    @Given("a new order with ID {string}")
    public void givenANewOrderWithID(String orderId) {
        UUID orderIdUUID = UUID.fromString(orderId);
        order = new Order(orderIdUUID, false, OrderStatusEnum.RECEIVED, new ArrayList<>(), new ArrayList<>(), 0.0, new Client());
    }

    @Given("the order has payment received as {string}")
    public void givenTheOrderHasPaymentReceived(String isPaymentReceived) {
        order.setPaymentReceived(Boolean.valueOf(isPaymentReceived));
    }

    @Given("the order has status {string}")
    public void givenTheOrderHasStatus(String orderStatus) {
        order.setOrderStatus(OrderStatusEnum.valueOf(orderStatus));
    }

    @Given("the order has products with IDs {string}")
    public void givenTheOrderHasProductsWithIDs(String productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds.split(", ")) {
            products.add(new Product(UUID.fromString(productId), "Dummy Product", "Description", BigDecimal.ZERO, "Category"));
        }
        order.setOrderProducts(products);
    }

    @Given("the order has product IDs {string}")
    public void givenTheOrderHasProductIDs(String productIds) {
        List<UUID> productIdList = new ArrayList<>();
        for (String productId : productIds.split(", ")) {
            productIdList.add(UUID.fromString(productId));
        }
        order.setOrderProductIds(productIdList);
    }

    @Given("the order has price {string}")
    public void givenTheOrderHasPrice(String orderPrice) {
        order.setOrderPrice(Double.valueOf(orderPrice));
    }

    @Given("the order has a client with ID {string}")
    public void givenTheOrderHasClientWithID(String clientId) {
        order.setClient(new Client(UUID.fromString(clientId), null, null, null));
    }

    @When("the order is created")
    public void whenTheOrderIsCreated() {
        // Your logic for creating the order goes here
        // This could involve interacting with your application's services or API
    }

    @When("converting the order to a response DTO")
    public void whenConvertingTheOrderToResponseDTO() {
        orderResponse = order.toResponse();
    }

    @Then("the order information should be valid")
    public void thenTheOrderInformationShouldBeValid() {
        assertEquals(UUID.class, order.getOrderId().getClass());
        // Add additional assertions based on your requirements
    }

    @Then("the order response should match the expected values")
    public void thenTheOrderResponseShouldMatchExpectedValues() {
        // Add your validation logic here
        // Example: assert that order response matches expected values
        assertEquals(order.getOrderId(), orderResponse.orderId());
        assertEquals(order.getPaymentReceived(), orderResponse.isPaymentReceived());
        assertEquals(order.getOrderStatus(), orderResponse.orderStatus());
        assertEquals(order.getOrderProducts(), orderResponse.orderProducts());
        assertEquals(order.getOrderPrice(), orderResponse.orderPrice());
    }
}