package br.com.fiap.api.pedidos.interfaceadapter;

import br.com.fiap.api.pedidos.application.gateway.ProductGateway;
import br.com.fiap.api.pedidos.domain.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateProductRequest;
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
    public ResponseEntity<List<ProductResponse>> getAll() {
        return new ResponseEntity<>(productGateway.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(productGateway.getProductById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody CreateProductRequest request) {
        return new ResponseEntity<>(productGateway.createProduct(request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductResponse> update(@RequestBody UpdateProductRequest request) {
        return new ResponseEntity<>(productGateway.updateProduct(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        try {
            productGateway.deleteProduct(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }

}
