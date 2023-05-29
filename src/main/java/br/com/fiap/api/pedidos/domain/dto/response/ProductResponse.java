package br.com.fiap.api.pedidos.domain.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductResponse {

    private UUID productId;
    private String productName;
    private String productDesc;
    private BigDecimal price;

    public ProductResponse() {
    }

    public ProductResponse(UUID productId, String productName, String productDesc, BigDecimal price) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.price = price;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
