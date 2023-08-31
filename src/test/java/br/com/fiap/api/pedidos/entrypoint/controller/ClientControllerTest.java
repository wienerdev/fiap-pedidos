package br.com.fiap.api.pedidos.entrypoint.controller;

import static org.mockito.Mockito.when;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.usecase.ClientUseCase;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.CreateClientRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ClientController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientControllerTest {
    @Autowired
    private ClientController clientController;

    @MockBean
    private ClientUseCase clientUseCase;

    /**
     * Method under test: {@link ClientController#create(CreateClientRequest)}
     */
    @Test
    public void testCreate() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(
                objectMapper.writeValueAsString(new CreateClientRequest("Client Cpf", "Dr Jane Doe", "jane.doe@example.org")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link ClientController#getByCpf(String)}
     */
    @Test
    public void testGetByCpf() throws Exception {
        when(clientUseCase.getClientByCpf(Mockito.<String>any())).thenReturn(new Client());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/customers/{cpf}", "Cpf");
        MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"response\":{\"clientId\":null,\"clientCpf\":null,\"clientName\":null,\"clientEmail\":null}}"));
    }
}

