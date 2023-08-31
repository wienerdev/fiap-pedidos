package br.com.fiap.api.pedidos.entrypoint.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.usecase.OrderUseCase;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.UpdateOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OrderController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderControllerTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderUseCase orderUseCase;

    /**
     * Method under test: {@link OrderController#create(CreateOrderRequest)}
     */
    @Test
    public void testCreate() throws Exception {
        when(orderUseCase.getAllOrders()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<UUID> orderProductIds = new ArrayList<>();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(
                objectMapper.writeValueAsString(new CreateOrderRequest("Customer Order", orderProductIds, new Client())));
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"success\":true,\"response\":[]}"));
    }

    /**
     * Method under test: {@link OrderController#delete(UUID)}
     */
    @Test
    public void testDelete() throws Exception {
        doNothing().when(orderUseCase).deleteOrder(Mockito.<UUID>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/orders/{id}",
                UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"success\":true,\"response\":\"Order deleted successfully\"}"));
    }

    /**
     * Method under test: {@link OrderController#delete(UUID)}
     */
    @Test
    public void testDelete2() throws Exception {
        doNothing().when(orderUseCase).deleteOrder(Mockito.<UUID>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/orders/{id}",
                UUID.randomUUID());
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"success\":true,\"response\":\"Order deleted successfully\"}"));
    }

    /**
     * Method under test: {@link OrderController#create(CreateOrderRequest)}
     */
    @Test
    public void testCreate2() throws Exception {
        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(new Order());
        when(orderUseCase.getAllOrders()).thenReturn(orderList);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<UUID> orderProductIds = new ArrayList<>();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(
                objectMapper.writeValueAsString(new CreateOrderRequest("Customer Order", orderProductIds, new Client())));
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"orderId\":null,\"customerOrder\":null,\"active\":null,\"orderStatus\":null,"
                                        + "\"orderProducts\":null,\"orderPrice\":null,\"client\":null}]}"));
    }

    /**
     * Method under test: {@link OrderController#create(CreateOrderRequest)}
     */
    @Test
    public void testCreate3() throws Exception {
        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(new Order());
        orderList.add(new Order());
        when(orderUseCase.getAllOrders()).thenReturn(orderList);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<UUID> orderProductIds = new ArrayList<>();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(
                objectMapper.writeValueAsString(new CreateOrderRequest("Customer Order", orderProductIds, new Client())));
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"orderId\":null,\"customerOrder\":null,\"active\":null,\"orderStatus\":null,"
                                        + "\"orderProducts\":null,\"orderPrice\":null,\"client\":null},{\"orderId\":null,\"customerOrder\":null,\"active\""
                                        + ":null,\"orderStatus\":null,\"orderProducts\":null,\"orderPrice\":null,\"client\":null}]}"));
    }

    /**
     * Method under test: {@link OrderController#getAll()}
     */
    @Test
    public void testGetAll() throws Exception {
        when(orderUseCase.getAllOrders()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/orders");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"success\":true,\"response\":[]}"));
    }

    /**
     * Method under test: {@link OrderController#getAll()}
     */
    @Test
    public void testGetAll2() throws Exception {
        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(new Order());
        when(orderUseCase.getAllOrders()).thenReturn(orderList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/orders");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"orderId\":null,\"customerOrder\":null,\"active\":null,\"orderStatus\":null,"
                                        + "\"orderProducts\":null,\"orderPrice\":null,\"client\":null}]}"));
    }

    /**
     * Method under test: {@link OrderController#getAll()}
     */
    @Test
    public void testGetAll3() throws Exception {
        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(new Order());
        orderList.add(new Order());
        when(orderUseCase.getAllOrders()).thenReturn(orderList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/orders");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"orderId\":null,\"customerOrder\":null,\"active\":null,\"orderStatus\":null,"
                                        + "\"orderProducts\":null,\"orderPrice\":null,\"client\":null},{\"orderId\":null,\"customerOrder\":null,\"active\""
                                        + ":null,\"orderStatus\":null,\"orderProducts\":null,\"orderPrice\":null,\"client\":null}]}"));
    }

    /**
     * Method under test: {@link OrderController#getById(UUID)}
     */
    @Test
    public void testGetById() throws Exception {
        Optional<Order> ofResult = Optional.of(new Order());
        when(orderUseCase.getOrderById(Mockito.<UUID>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/orders/{id}",
                UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":{\"orderId\":null,\"customerOrder\":null,\"active\":null,\"orderStatus\":null,"
                                        + "\"orderProducts\":null,\"orderPrice\":null,\"client\":null}}"));
    }

    /**
     * Method under test: {@link OrderController#update(UpdateOrderRequest)}
     */
    @Test
    public void testUpdate() throws Exception {
        when(orderUseCase.getAllOrders()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new UpdateOrderRequest(UUID.randomUUID(), OrderStatusEnum.CREATED)));
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"success\":true,\"response\":[]}"));
    }

    /**
     * Method under test: {@link OrderController#update(UpdateOrderRequest)}
     */
    @Test
    public void testUpdate2() throws Exception {
        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(new Order());
        when(orderUseCase.getAllOrders()).thenReturn(orderList);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new UpdateOrderRequest(UUID.randomUUID(), OrderStatusEnum.CREATED)));
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"orderId\":null,\"customerOrder\":null,\"active\":null,\"orderStatus\":null,"
                                        + "\"orderProducts\":null,\"orderPrice\":null,\"client\":null}]}"));
    }

    /**
     * Method under test: {@link OrderController#update(UpdateOrderRequest)}
     */
    @Test
    public void testUpdate3() throws Exception {
        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(new Order());
        orderList.add(new Order());
        when(orderUseCase.getAllOrders()).thenReturn(orderList);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new UpdateOrderRequest(UUID.randomUUID(), OrderStatusEnum.CREATED)));
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"orderId\":null,\"customerOrder\":null,\"active\":null,\"orderStatus\":null,"
                                        + "\"orderProducts\":null,\"orderPrice\":null,\"client\":null},{\"orderId\":null,\"customerOrder\":null,\"active\""
                                        + ":null,\"orderStatus\":null,\"orderProducts\":null,\"orderPrice\":null,\"client\":null}]}"));
    }
}

