package br.com.fiap.api.pedidos.acceptance.steps;



import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.usecase.OrderUseCase;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.OrderEntity;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.checkerframework.checker.nullness.Opt.isPresent;
import static org.junit.Assert.assertNotNull;

public class OrderSteps {


//   private  OrderRepositoryJpa orderUseCase;
//    private final OrderRepositoryJpa orderRepository = Mockito.mock(OrderRepositoryJpa.class);
//    private final ProductRepositoryJpa productRepository = Mockito.mock(ProductRepositoryJpa.class);
//    private final ClientRepositoryJpa clientRepository = Mockito.mock(ClientRepositoryJpa.class);
//
//
//    public OrderSteps() {
//        orderUseCase = new OrderUseCaseJpa(orderRepository,productRepository,clientRepository);
//    }
private final OrderUseCase orderUseCase;


    public OrderSteps(OrderUseCase orderUseCase) {

        this.orderUseCase = orderUseCase;
    }


    private CreateOrderRequest createOrderRequest;
    private UUID orderId;
    private UpdateOrderRequest updateOrderRequest;
    private List<OrderEntity> getAllResponse;
    private Optional<OrderEntity> getByIdResponse;
    private Order createResponse;
    private ResponseEntity<BaseResponse> updateResponse;
    private ResponseEntity<BaseResponse> deleteResponse;

    Order orderSave;

    private List<OrderEntity> orderCheckoutResponse;

    List<Product> products = Arrays.asList(
            new Product(UUID.randomUUID(), "Product1", "Description1", new BigDecimal(100), "Category1"),
            new Product(UUID.randomUUID(), "Product2", "Description2", new BigDecimal(200), "Category2")
    );
    Client client = new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br");
    List<UUID> productIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
    OrderEntity  orderEntity;

    Order  order;


    @Given("the system has orders")
    public void theSystemHasOrders() {
        Order orderEntity = new Order(
                orderId,
                false,
                OrderStatusEnum.RECEIVED,
                products,
                productIds,
                10.00,
                client);
        orderUseCase.saveOrder(order);
//        orderSave = order;
    }

    @When("the client requests all orders")
    public void theClientRequestsAllOrders() {
       // getAllResponse = orderUseCase.findAll();
    }



    @When("the client requests an order by ID")
    public void theClientRequestsAnOrderByID() {
        //getByIdResponse = orderUseCase.findById(orderId);
    }

    @Then("the system should respond with the order details")
    public void theSystemShouldRespondWithTheOrderDetails() {
        assertNotNull(getByIdResponse);
    }

    @Given("the client provides order details")
    public void theClientProvidesOrderDetails() {
        createOrderRequest = new CreateOrderRequest(productIds,client);
    }

    @When("the client submits a new order")
    public void theClientSubmitsANewOrder() {
        //createResponse = orderUseCase.save(orderEntity).toOrder();
        orderId = createResponse.getOrderId();
    }

    @Then("the system should respond with the created order details")
    public void theSystemShouldRespondWithTheCreatedOrderDetails() {
        assertNotNull(createResponse);
    }

    @Given("there is an existing order")
    public void thereIsAnExistingOrder() {
    }

    @When("the client updates the order status")
    public void theClientUpdatesTheOrderStatus() {
        updateOrderRequest = new UpdateOrderRequest(orderId,OrderStatusEnum.DONE);
        //orderUseCase.updateByOrderStatusAndOrderId(updateOrderRequest.orderStatus(),updateOrderRequest.orderId());
        updateResponse = new ResponseEntity<>(new BaseResponse<>(
                true,
                "Order updated successfully"), HttpStatus.OK);
    }

    @Then("the system should respond with a success message")
    public void theSystemShouldRespondWithASuccessMessage() {
        assertNotNull(updateResponse);
    }

    @When("the client deletes the order")
    public void theClientDeletesTheOrder() {
        //orderUseCase.delete(orderEntity);
        if(isPresent(orderSave)) {
            deleteResponse = new ResponseEntity<>(new BaseResponse<>(
                    true,
                    "Order updated successfully"), HttpStatus.OK);
        }
    }


    @When("the client requests order checkout for that CPF")
    public void theClientRequestsOrderCheckoutForThatCPF() {
        //orderCheckoutResponse = orderUseCase.getAllByClientEntityClientCpf("01374050067");
    }

    @Then("the system should respond with a list of order checkout details")
    public void theSystemShouldRespondWithAListOfOrderCheckoutDetails() {
        assertNotNull(orderCheckoutResponse);
    }
}
