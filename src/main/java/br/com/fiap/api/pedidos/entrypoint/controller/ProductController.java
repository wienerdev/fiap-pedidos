package br.com.fiap.api.pedidos.entrypoint.controller;


import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.usecase.ProductUseCase;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.UpdateProductRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductUseCase productGateway;

    public ProductController(ProductUseCase productGateway) {
        this.productGateway = productGateway;
    }


    @GetMapping
    public ResponseEntity<BaseResponse<List<ProductResponse>>> getAll() {
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                productGateway.getAllProducts().stream()
                        .map(ProductResponse::fromEntityToResponse)
                        .toList()), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<BaseResponse<Iterable<ProductResponse>>> getAll(@PathVariable String category) {
        List<ProductResponse> listProdCategory = productGateway.getByCategory(category).stream()
                .map(ProductResponse::fromEntityToResponse)
                .toList();
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                listProdCategory), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductResponse>> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                ProductResponse.fromEntityToResponse(productGateway.getProductById(id).get())), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<ProductResponse>> create(@RequestBody CreateProductRequest request) {
        Product productSave = productGateway.saveProduct(request.fromRequestProduct());
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                ProductResponse.fromEntityToResponse(productSave)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BaseResponse<ProductResponse>> update(@RequestBody UpdateProductRequest request) {
        Product entity = UpdateProductRequest.fromRequestProduct(request);
        productGateway.updateProduct(entity);

        return new ResponseEntity<>(new BaseResponse<>(
                true,
                ProductResponse.fromEntityToResponse(entity)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable UUID id) {
       // return productGateway.deleteProduct(id);

            productGateway.deleteProduct(id);
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                null), HttpStatus.OK);

    }

}
