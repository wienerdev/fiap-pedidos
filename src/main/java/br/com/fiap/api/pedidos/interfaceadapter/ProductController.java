package br.com.fiap.api.pedidos.interfaceadapter;

import br.com.fiap.api.pedidos.application.gateway.ProductGateway;
import br.com.fiap.api.pedidos.domain.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.domain.dto.response.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductGateway productGateway;

    public ProductController(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<Iterable<ProductResponse>>> getAll() {
        return productGateway.getAllProducts();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<BaseResponse<Iterable<ProductResponse>>> getAll(@PathVariable String category) {
        return productGateway.getAllProductsByCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductResponse>> getById(@PathVariable UUID id) {
        return productGateway.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<ProductResponse>> create(@RequestBody CreateProductRequest request) {
        return productGateway.createProduct(request);
    }

    @PutMapping
    public ResponseEntity<BaseResponse<ProductResponse>> update(@RequestBody UpdateProductRequest request) {
        return productGateway.updateProduct(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable UUID id) {
        return productGateway.deleteProduct(id);
    }

}
