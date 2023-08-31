package br.com.fiap.api.pedidos.entrypoint.controller;

import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.usecase.ProductUseCase;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.CreateProductRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.UpdateProductRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductUseCase productUseCase;

    /**
     * Method under test: {@link ProductController#create(CreateProductRequest)}
     */
    @Test
    public void testCreate() throws Exception {
        when(productUseCase.getAllProducts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(
                new CreateProductRequest("Product Name", "Product Desc", BigDecimal.valueOf(1L), "Category")));
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"success\":true,\"response\":[]}"));
    }

    /**
     * Method under test: {@link ProductController#delete(UUID)}
     */
    @Test
    public void testDelete() throws Exception {
        when(productUseCase.deleteProduct(Mockito.<UUID>any())).thenReturn(new BaseResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/products/{id}",
                UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"success\":true,\"response\":null}"));
    }

    /**
     * Method under test: {@link ProductController#create(CreateProductRequest)}
     */
    @Test
    public void testCreate2() throws Exception {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productUseCase.getAllProducts()).thenReturn(productList);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(
                new CreateProductRequest("Product Name", "Product Desc", BigDecimal.valueOf(1L), "Category")));
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"product\":{\"productId\":null,\"productName\":null,\"productDesc\":null,\"price"
                                        + "\":null,\"category\":null}}]}"));
    }

    /**
     * Method under test: {@link ProductController#create(CreateProductRequest)}
     */
    @Test
    public void testCreate3() throws Exception {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productUseCase.getAllProducts()).thenReturn(productList);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(
                new CreateProductRequest("Product Name", "Product Desc", BigDecimal.valueOf(1L), "Category")));
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"product\":{\"productId\":null,\"productName\":null,\"productDesc\":null,\"price"
                                        + "\":null,\"category\":null}},{\"product\":{\"productId\":null,\"productName\":null,\"productDesc\":null,\"price\""
                                        + ":null,\"category\":null}}]}"));
    }

    /**
     * Method under test: {@link ProductController#getAll()}
     */
    @Test
    public void testGetAll() throws Exception {
        when(productUseCase.getAllProducts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/products");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"success\":true,\"response\":[]}"));
    }

    /**
     * Method under test: {@link ProductController#getAll()}
     */
    @Test
    public void testGetAll2() throws Exception {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productUseCase.getAllProducts()).thenReturn(productList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/products");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"product\":{\"productId\":null,\"productName\":null,\"productDesc\":null,\"price"
                                        + "\":null,\"category\":null}}]}"));
    }

    /**
     * Method under test: {@link ProductController#getAll()}
     */
    @Test
    public void testGetAll3() throws Exception {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productUseCase.getAllProducts()).thenReturn(productList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/products");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"product\":{\"productId\":null,\"productName\":null,\"productDesc\":null,\"price"
                                        + "\":null,\"category\":null}},{\"product\":{\"productId\":null,\"productName\":null,\"productDesc\":null,\"price\""
                                        + ":null,\"category\":null}}]}"));
    }

    /**
     * Method under test: {@link ProductController#getAll(String)}
     */
    @Test
    public void testGetAll4() throws Exception {
        when(productUseCase.getByCategory(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/products/category/{category}",
                "Category");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"success\":true,\"response\":[]}"));
    }

    /**
     * Method under test: {@link ProductController#getAll(String)}
     */
    @Test
    public void testGetAll5() throws Exception {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productUseCase.getByCategory(Mockito.<String>any())).thenReturn(productList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/products/category/{category}",
                "Category");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"product\":{\"productId\":null,\"productName\":null,\"productDesc\":null,\"price"
                                        + "\":null,\"category\":null}}]}"));
    }

    /**
     * Method under test: {@link ProductController#getAll(String)}
     */
    @Test
    public void testGetAll6() throws Exception {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productUseCase.getByCategory(Mockito.<String>any())).thenReturn(productList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/products/category/{category}",
                "Category");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"product\":{\"productId\":null,\"productName\":null,\"productDesc\":null,\"price"
                                        + "\":null,\"category\":null}},{\"product\":{\"productId\":null,\"productName\":null,\"productDesc\":null,\"price\""
                                        + ":null,\"category\":null}}]}"));
    }

    /**
     * Method under test: {@link ProductController#getById(UUID)}
     */
    @Test
    public void testGetById() throws Exception {
        Optional<Product> ofResult = Optional.of(new Product());
        when(productUseCase.getProductById(Mockito.<UUID>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/products/{id}",
                UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":{\"product\":{\"productId\":null,\"productName\":null,\"productDesc\":null,\"price"
                                        + "\":null,\"category\":null}}}"));
    }

    /**
     * Method under test: {@link ProductController#update(UpdateProductRequest)}
     */
    @Test
    public void testUpdate() throws Exception {
        when(productUseCase.getAllProducts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        UUID productId = UUID.randomUUID();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(
                new UpdateProductRequest(productId, "Product Name", "Product Desc", BigDecimal.valueOf(1L), "Category")));
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"success\":true,\"response\":[]}"));
    }

    /**
     * Method under test: {@link ProductController#update(UpdateProductRequest)}
     */
    @Test
    public void testUpdate2() throws Exception {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productUseCase.getAllProducts()).thenReturn(productList);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        UUID productId = UUID.randomUUID();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(
                new UpdateProductRequest(productId, "Product Name", "Product Desc", BigDecimal.valueOf(1L), "Category")));
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"product\":{\"productId\":null,\"productName\":null,\"productDesc\":null,\"price"
                                        + "\":null,\"category\":null}}]}"));
    }

    /**
     * Method under test: {@link ProductController#update(UpdateProductRequest)}
     */
    @Test
    public void testUpdate3() throws Exception {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productUseCase.getAllProducts()).thenReturn(productList);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        UUID productId = UUID.randomUUID();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(
                new UpdateProductRequest(productId, "Product Name", "Product Desc", BigDecimal.valueOf(1L), "Category")));
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":[{\"product\":{\"productId\":null,\"productName\":null,\"productDesc\":null,\"price"
                                        + "\":null,\"category\":null}},{\"product\":{\"productId\":null,\"productName\":null,\"productDesc\":null,\"price\""
                                        + ":null,\"category\":null}}]}"));
    }
}

