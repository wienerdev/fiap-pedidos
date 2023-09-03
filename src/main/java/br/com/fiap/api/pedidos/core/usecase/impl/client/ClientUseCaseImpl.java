package br.com.fiap.api.pedidos.core.usecase.impl.client;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.exception.ClientNotFoundException;
import br.com.fiap.api.pedidos.core.usecase.ClientUseCase;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ClientRepository;

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
}
