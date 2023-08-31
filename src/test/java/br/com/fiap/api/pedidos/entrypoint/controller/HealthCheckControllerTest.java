package br.com.fiap.api.pedidos.entrypoint.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {HealthCheckController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class HealthCheckControllerTest {
    @Autowired
    private HealthCheckController healthCheckController;

    /**
     * Method under test: {@link HealthCheckController#getAppHealth()}
     */
    @Test
    public void testGetAppHealth() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/health-check");
        MockMvcBuilders.standaloneSetup(healthCheckController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Working"));
    }

    /**
     * Method under test: {@link HealthCheckController#getAppHealth()}
     */
    @Test
    public void testGetAppHealth2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/health-check",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(healthCheckController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Working"));
    }
}

