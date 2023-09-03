package br.com.fiap.api.pedidos.core.usecase;

import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.UpdateProductRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.ProductResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductUseCase {

    List<Product> getAllProducts();
    List<Product> getByCategory(String category);
    Optional<Product> getProductById(UUID id);
    Product saveProduct(Product request);
    Product updateProduct(Product request);
    BaseResponse deleteProduct(UUID id);


}
