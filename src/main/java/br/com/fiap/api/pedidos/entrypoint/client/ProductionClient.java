package br.com.fiap.api.pedidos.entrypoint.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "orderClient", url = "http://localhost:8080/api/v1/orders")
public interface ProductionClient {

    @GetMapping
    ResponseEntity<String> getAllOrders();
}