package br.com.fiap.api.pedidos.application.gateway;

import br.com.fiap.api.pedidos.domain.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.domain.dto.response.OrderResponse;
import br.com.fiap.api.pedidos.domain.port.usecase.OrderUseCasePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Service
public class OrderGateway {

    private final OrderUseCasePort orderUseCasePort;


    public OrderGateway(OrderUseCasePort orderUseCasePort) {
        this.orderUseCasePort = orderUseCasePort;
    }

    public ResponseEntity<BaseResponse<Iterable<OrderResponse>>> getAllOrders() {
        return new ResponseEntity<>(orderUseCasePort.getAllOrders(), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse<OrderResponse>> getOrderById(@PathVariable UUID id) {
        return new ResponseEntity<>(orderUseCasePort.getOrderById(id), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse<OrderResponse>> createOrder(@RequestBody CreateOrderRequest request){
        return new ResponseEntity<>(orderUseCasePort.saveOrder(request), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse> updateOrder(@RequestBody UpdateOrderRequest request){
        return new ResponseEntity<>(orderUseCasePort.updateOrder(request), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse> deleteOrder(@PathVariable UUID id){
        return new ResponseEntity<>(orderUseCasePort.deleteOrder(id), HttpStatus.OK);
    }
}
