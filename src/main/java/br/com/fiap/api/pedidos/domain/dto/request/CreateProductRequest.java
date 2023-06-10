package br.com.fiap.api.pedidos.domain.dto.request;

import java.math.BigDecimal;

public class CreateProductRequest {

    private String productName;
    private String productDesc;
    private BigDecimal price;
    private String category;

    public CreateProductRequest(String productName, String productDesc, BigDecimal price, String category) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.price = price;
        this.category = category;
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
