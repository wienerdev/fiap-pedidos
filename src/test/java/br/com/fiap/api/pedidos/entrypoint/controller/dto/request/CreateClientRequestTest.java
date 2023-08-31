package br.com.fiap.api.pedidos.entrypoint.controller.dto.request;

import static org.junit.Assert.assertEquals;

import br.com.fiap.api.pedidos.core.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {CreateClientRequest.class, String.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateClientRequestTest {
    @Autowired
    private CreateClientRequest createClientRequest;

    /**
     * Method under test: {@link CreateClientRequest#toClient()}
     */
    @Test
    public void testToClient() {
        Client actualToClientResult = createClientRequest.toClient();
        assertEquals("", actualToClientResult.getClientCpf());
        assertEquals("", actualToClientResult.getClientName());
        assertEquals("", actualToClientResult.getClientEmail());
    }
}

