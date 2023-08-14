package br.com.fiap.api.pedidos.domain.dto.request;

import br.com.fiap.api.pedidos.domain.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateProductRequest(UUID productId, String productName, String productDesc, BigDecimal price, String category) {
    public static Product fromRequestProduct(UpdateProductRequest request) {
        return new Product(request.productId,request.productName,request.productDesc,request.price,request.category);
    }
}
