package br.com.fiap.api.pedidos.entrypoint.controller.dto.response;

import br.com.fiap.api.pedidos.core.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(Product product) {

    public static ProductResponse fromEntityToResponse(Product product) {
        return new ProductResponse(product);
    }
}