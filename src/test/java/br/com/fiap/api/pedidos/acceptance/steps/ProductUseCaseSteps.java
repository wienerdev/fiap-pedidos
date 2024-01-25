package br.com.fiap.api.pedidos.acceptance.steps;

import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ProductRepository;
import br.com.fiap.api.pedidos.core.usecase.impl.product.ProductUseCaseImpl;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductUseCaseSteps {
    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    private final ProductUseCaseImpl productUseCase = new ProductUseCaseImpl(productRepository);
    private List<Product> produtos;
    private String categoria;
    private final UUID idProduto = UUID.randomUUID();
    private Product novoProduto;

    @Quando("Obter todos os produto")
    public void quandoEuObtenhoTodosOsProdutos() {
        produtos = productUseCase.getAllProducts();
    }

    @Quando("eu obtenho todos os produtos")
    public void eu_obtenho_todos_os_produtos() {

    }

    @Então("a lista de produtos não deve estar vazia")
    public void entaoAListaDeProdutosNaoDeveEstarVazia() {
        List<Product> expectedProducts = Arrays.asList(
                new Product(UUID.randomUUID(), "Product1","produto teste1",new BigDecimal(100), "Category1"),
                new Product(UUID.randomUUID(), "Product2","produto teste2",new BigDecimal(200), "Category2")
        );
        when(productRepository.getAll()).thenReturn(expectedProducts);
        List<Product> actualProducts = productUseCase.getAllProducts();
        assertEquals(expectedProducts, actualProducts);
        Assert.assertNotNull(actualProducts);
        Assert.assertFalse(actualProducts.isEmpty());
    }

    @Dado("uma categoria {string}")
    public void dadaUmaCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Quando("eu obtenho produtos por categoria")
    public void quandoEuObtenhoProdutosPorCategoria() {
        String category = "Category1";
        List<Product> expectedProducts = Arrays.asList(
                new Product(UUID.randomUUID(), "Product1","produto teste1",new BigDecimal(100), "Category1"),
                new Product(UUID.randomUUID(), "Product2","produto teste2",new BigDecimal(200), "Category1")
        );
        when(productRepository.getByCategory(category)).thenReturn(expectedProducts);
        List<Product> actualProducts = productUseCase.getByCategory(category);
        assertEquals(expectedProducts, actualProducts);
    }


}