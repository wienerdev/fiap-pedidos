package br.com.fiap.api.pedidos.application.gateway;

import br.com.fiap.api.pedidos.domain.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateProductRequest;
import br.com.fiap.api.pedidos.domain.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.domain.dto.response.ProductResponse;
import br.com.fiap.api.pedidos.domain.port.usecase.ProductUseCasePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<BaseResponse<Iterable<ProductResponse>>> getAllProducts() {
        return new ResponseEntity<>(productUseCasePort.getAllProducts(), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse<Iterable<ProductResponse>>> getAllProductsByCategory(String category) {
        return new ResponseEntity<>(productUseCasePort.getByCategory(category), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse<ProductResponse>> getProductById(@PathVariable UUID id) {
        return new ResponseEntity<>(productUseCasePort.getProductById(id), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse<ProductResponse>> createProduct(@RequestBody CreateProductRequest request) {
        return new ResponseEntity<>(productUseCasePort.saveProduct(request), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse<ProductResponse>> updateProduct(@RequestBody UpdateProductRequest request) {
        return new ResponseEntity<>(productUseCasePort.updateProduct(request), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse> deleteProduct(@PathVariable UUID id) {
        return new ResponseEntity<>(productUseCasePort.deleteProduct(id), HttpStatus.OK);
    }
}
