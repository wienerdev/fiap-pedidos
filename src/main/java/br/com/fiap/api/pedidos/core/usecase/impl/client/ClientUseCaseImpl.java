package br.com.fiap.api.pedidos.core.usecase.impl.client;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.exception.ClientNotFoundException;
import br.com.fiap.api.pedidos.core.usecase.ClientUseCase;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ClientRepository;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.DeleteClientDataRequest;
import org.springframework.transaction.annotation.Transactional;

public class ClientUseCaseImpl implements ClientUseCase {

    private final ClientRepository clientRepository;


    public ClientUseCaseImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Client getClientByCpf(String cpf) {
        return clientRepository.identifyClientByCpf(cpf).orElseThrow(() -> new ClientNotFoundException("client not found"));
    }

    @Override
    public Client saveClient(Client client) {
        clientRepository.registerClient(client);
        return client;
    }

    @Override
    @Transactional
    public String deleteClient(DeleteClientDataRequest request) {
        try {
            clientRepository.deleteClient(request.clientName(), request.clientAddress(), request.clientPhoneNumber());
            return "Dados do cliente "+request.clientName()+" excluídos com sucesso!";
        } catch (Exception e) {
            return "Não foi possível excluir os dados do cliente, por favor tente novamente";
        }
    }
}
