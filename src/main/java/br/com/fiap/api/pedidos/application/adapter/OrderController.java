package br.com.fiap.api.pedidos.application.adapter;

import br.com.fiap.api.pedidos.domain.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.response.OrderResponse;
import br.com.fiap.api.pedidos.domain.port.usecase.OrderUseCasePort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderUseCasePort orderUseCasePort;

    public OrderController(OrderUseCasePort orderUseCasePort) {
        this.orderUseCasePort = orderUseCasePort;
    }

    @GetMapping
    public List<OrderResponse> getAll() {
        return orderUseCasePort.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable UUID id) {
        return orderUseCasePort.getOrderById(id);
    }

    @PostMapping
    public OrderResponse create(@RequestBody CreateOrderRequest request){
        return orderUseCasePort.saveOrder(request);
    }

    @PutMapping
    public OrderResponse update(@RequestBody UpdateOrderRequest request){
        return  orderUseCasePort.updateOrder(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        orderUseCasePort.deleteOrder(id);
    }
}
