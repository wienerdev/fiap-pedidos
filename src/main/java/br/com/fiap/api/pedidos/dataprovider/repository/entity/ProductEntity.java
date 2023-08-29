package br.com.fiap.api.pedidos.dataprovider.repository.entity;

import br.com.fiap.api.pedidos.core.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    private UUID productId;

    private String productName;

    private String productDesc;

    private BigDecimal price;

    private String category;

    public ProductEntity() {

    }

    public ProductEntity(UUID productId, String productName, String productDesc, BigDecimal price, String category) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.price = price;
        this.category = category;
    }

    public ProductEntity(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productDesc = product.getProductDesc();
        this.price = product.getPrice();
        this.category = product.getCategory();
    }

    public Product toProduct() {
        return new Product(this.productId, this.productName, this.productDesc, this.price, this.category);
    }

    public static List<ProductEntity> toProductEntityList(List<Product> products) {
        return products.stream()
                .map(ProductEntity::new)
                .collect(Collectors.toList());
    }

}
