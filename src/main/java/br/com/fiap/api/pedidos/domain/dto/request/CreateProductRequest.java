package br.com.fiap.api.pedidos.domain.dto.request;

import java.math.BigDecimal;

public class CreateProductRequest {

    private String productName;
    private String productDesc;
    private BigDecimal price;

    public CreateProductRequest(String productName, String productDesc, BigDecimal price) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.price = price;
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
