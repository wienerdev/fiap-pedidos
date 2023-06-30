package br.com.fiap.api.pedidos.domain.dto.response;

import br.com.fiap.api.pedidos.domain.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID productId, String productName, String productDesc, BigDecimal price, String category) {

    public static ProductResponse fromEntityToResponse(Product product) {
        return new ProductResponse(product.getProductId(),product.getProductName(), product.getProductDesc(),product.getPrice(),product.getCategory());
    }
}