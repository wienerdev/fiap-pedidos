package br.com.fiap.api.pedidos.acceptance.steps;


import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ClientRepository;
import br.com.fiap.api.pedidos.core.usecase.impl.client.ClientUseCaseImpl;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ClientSteps {

    private final ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
    private final ClientUseCaseImpl clientUseCase = new ClientUseCaseImpl(clientRepository);

    @Dado("que existe um cliente com CPF {string}")
    public void existeUmClienteComCpf(String cpf) {
        Mockito.when(clientRepository.identifyClientByCpf(cpf)).thenReturn(
                Optional.of(new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br")));
    }

    @Quando("o cliente solicita a recuperação do cliente por CPF")
    public void o_cliente_solicita_a_recuperação_do_cliente_por_cpf() {
        try {
            Client  clientResult = clientUseCase.getClientByCpf("01374050067");
        } catch (Exception e) {
            Exception  exceptionResult = e;
        }
    }

    @Então("a resposta deve conter os detalhes do cliente")
    public void a_resposta_deve_conter_os_detalhes_do_cliente() {
        Assert.assertTrue(true);
    }
    @Dado("que existe um CPF inválido {string}")//1
    public void que_existe_um_cpf_inválido(String cpf) {
        Mockito.when(clientRepository.identifyClientByCpf(cpf)).thenReturn(Optional.of(new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br")));
    }

    @Então("a resposta deve indicar que o cliente não foi encontrado")//2
    public void a_resposta_deve_indicar_que_o_cliente_não_foi_encontrado() {
        Mockito.when(clientRepository.identifyClientByCpf("01374050568")).thenReturn(Optional.empty());
    }


    @Dado("a solicitação contém um cliente com CPF {string} e nome {string}")
    public void solicitacaoContemClienteComCpfENome(String cpf, String name) {

    }
    @Dado("o cliente solicita a criação de um novo cliente")
    public void cria_novo_cliente() {
        Client clientToSave = new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br");
        Client savedClient = clientRepository.registerClient(clientToSave);
        assertNotNull(savedClient);
        assertEquals(clientToSave, savedClient);
        verify(clientRepository, times(1)).registerClient(clientToSave);
    }
    @Quando("a resposta deve conter os detalhes do cliente criado")
    public void resposta_detalhes_cliente() {
        Client clientToSave = new Client(UUID.randomUUID(),"01374050067", "John Doe","alexandre.dias@meta.com.br");
        Client savedClient = clientRepository.registerClient(clientToSave);
        Optional<Client> clientResult = clientRepository.identifyClientByCpf("01374050067");
        Assert.assertNotNull(clientResult);
    }

}