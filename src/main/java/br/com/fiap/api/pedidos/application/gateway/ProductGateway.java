package br.com.fiap.api.pedidos.application.gateway;

import br.com.fiap.api.pedidos.domain.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.response.ProductResponse;
import br.com.fiap.api.pedidos.domain.port.usecase.ProductUseCasePort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class ProductGateway {

    private final ProductUseCasePort productUseCasePort;

    public ProductGateway(ProductUseCasePort productUseCasePort) {
        this.productUseCasePort = productUseCasePort;
    }

    public List<ProductResponse> getAllProducts() {
        return productUseCasePort.getAllProducts();
    }

    public List<ProductResponse> getAllProductsByCategory(String category) {
        return productUseCasePort.getByCategory(category);
    }

    public ProductResponse getProductById(@PathVariable UUID id) {
        return productUseCasePort.getProductById(id);
    }

    public ProductResponse createProduct(@RequestBody CreateProductRequest request) {
        return productUseCasePort.saveProduct(request);
    }

    public ProductResponse updateProduct(@RequestBody UpdateProductRequest request) {
        return productUseCasePort.updateProduct(request);
    }

    public void deleteProduct(@PathVariable UUID id) {
        productUseCasePort.deleteProduct(id);
    }
}
