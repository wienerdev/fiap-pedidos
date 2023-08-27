package br.com.fiap.api.pedidos.interfaceadapter;

import br.com.fiap.api.pedidos.application.gateway.OrderGateway;
import br.com.fiap.api.pedidos.domain.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.domain.dto.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    
    private final OrderGateway orderGateway;

    public OrderController(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<Iterable<OrderResponse>>> getAll() {
        return orderGateway.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<OrderResponse>> getById(@PathVariable UUID id) {
        return orderGateway.getOrderById(id);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<OrderResponse>> create(@RequestBody CreateOrderRequest request){
        return orderGateway.createOrder(request);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody UpdateOrderRequest request){
        return orderGateway.updateOrder(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable UUID id){
        return orderGateway.deleteOrder(id);
    }
}
