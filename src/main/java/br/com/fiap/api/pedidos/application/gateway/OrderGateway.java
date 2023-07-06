package br.com.fiap.api.pedidos.application.gateway;

import br.com.fiap.api.pedidos.domain.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.response.OrderResponse;
import br.com.fiap.api.pedidos.domain.port.usecase.OrderUseCasePort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Service
public class OrderGateway {

    private final OrderUseCasePort orderUseCasePort;


    public OrderGateway(OrderUseCasePort orderUseCasePort) {
        this.orderUseCasePort = orderUseCasePort;
    }

    public List<OrderResponse> getAllOrders() {
        return orderUseCasePort.getAllOrders();
    }

    public OrderResponse getOrderById(@PathVariable UUID id) {
        return orderUseCasePort.getOrderById(id);
    }

    public OrderResponse createOrder(@RequestBody CreateOrderRequest request){
        return orderUseCasePort.saveOrder(request);
    }

    public OrderResponse updateOrder(@RequestBody UpdateOrderRequest request){
        return  orderUseCasePort.updateOrder(request);
    }

    public void deleteOrder(@PathVariable UUID id){
        orderUseCasePort.deleteOrder(id);
    }
}
