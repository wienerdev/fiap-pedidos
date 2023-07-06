package br.com.fiap.api.pedidos.domain.dto.request;

import br.com.fiap.api.pedidos.domain.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductRequest(String productName, String productDesc, BigDecimal price, String category) {
    public static Product fromRequestProduct(CreateProductRequest request) {
        return new Product(UUID.randomUUID(),request.productName,request.productDesc,request.price,request.category);
    }
}
