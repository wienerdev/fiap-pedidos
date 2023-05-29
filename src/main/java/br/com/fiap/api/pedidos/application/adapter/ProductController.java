package br.com.fiap.api.pedidos.application.adapter;

import br.com.fiap.api.pedidos.domain.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.response.ProductResponse;
import br.com.fiap.api.pedidos.domain.port.usecase.ProductUseCasePort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductUseCasePort productUseCasePort;

    public ProductController(ProductUseCasePort productUseCasePort) {
        this.productUseCasePort = productUseCasePort;
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return productUseCasePort.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable UUID id) {
        return productUseCasePort.getProductById(id);
    }

    @PostMapping
    public ProductResponse create(@RequestBody CreateProductRequest request) {
        return productUseCasePort.saveProduct(request);
    }

    @PutMapping
    public ProductResponse update(@RequestBody UpdateProductRequest request) {
        return productUseCasePort.updateProduct(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        productUseCasePort.deleteProduct(id);
    }

}
