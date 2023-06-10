package br.com.fiap.api.pedidos.domain.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateProductRequest {

    private UUID productId;
    private String productName;
    private String productDesc;
    private BigDecimal price;
    private String category;

    public UpdateProductRequest(UUID productId, String productName, String productDesc, BigDecimal price, String category) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.price = price;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
