package br.com.fiap.api.pedidos.entrypoint.controller;


import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.usecase.OrderUseCase;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.OrderCheckoutResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.OrderResponse;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {
    
    private final OrderUseCase orderUseCase;

    public OrderController(OrderUseCase orderGateway) {
        this.orderUseCase = orderGateway;
    }


    @GetMapping
    public ResponseEntity<BaseResponse<List<OrderResponse>>> getAll() {
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                orderUseCase.getAllOrders().stream().map(OrderResponse::fromEntityToResponse).toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<OrderResponse>> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                OrderResponse.fromEntityToResponse(orderUseCase.getOrderById(id).get())), HttpStatus.OK);
    }

    @GetMapping("/payment-received/{id}")
    public ResponseEntity<Boolean> getPaymentReceivedById(@PathVariable UUID id) {
        return new ResponseEntity<>(orderUseCase.isPaymentReceivedByOrderId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<OrderResponse>> create(@RequestBody CreateOrderRequest request){
        Order orderSave = orderUseCase.saveOrder(CreateOrderRequest.fromResponseToOrder(request));
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                OrderResponse.fromEntityToResponse(orderSave)), HttpStatus.CREATED);
    }


    @PutMapping("/update-status")
    public ResponseEntity<BaseResponse> update(@RequestBody UpdateOrderRequest request){
        orderUseCase.updateOrder(request.orderStatus(), request.orderId());
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                "Order updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable UUID id){
         orderUseCase.deleteOrder(id);
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                "Order deleted successfully"), HttpStatus.OK);
    }

    @GetMapping("/order-checkout/{cpf}")
    public ResponseEntity<BaseResponse<List<OrderCheckoutResponse>>> orderCheckout(@PathVariable String cpf) {
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                orderUseCase.orderCheckout(cpf).stream().map(OrderCheckoutResponse::fromEntityToResponse).toList()), HttpStatus.OK);
    }
}
