package br.com.fiap.api.pedidos.domain.usecase;

import br.com.fiap.api.pedidos.domain.Client;
import br.com.fiap.api.pedidos.domain.dto.request.CreateClientRequest;
import br.com.fiap.api.pedidos.domain.dto.response.ClientResponse;
import br.com.fiap.api.pedidos.domain.port.repository.ClientRepositoryPort;
import br.com.fiap.api.pedidos.domain.port.usecase.ClientUseCasePort;

public class ClientUseCase implements ClientUseCasePort {

    private final ClientRepositoryPort clientRepository;


    public ClientUseCase(ClientRepositoryPort clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientResponse getClientByCpf(String cpf) {
        return ClientResponse.fromClientResponse(clientRepository.identifyClientByCpf(cpf));
    }

    @Override
    public ClientResponse saveClient(CreateClientRequest request) {
        Client entity = CreateClientRequest.fromClient(request);
        clientRepository.registerClient(entity);
        return ClientResponse.fromClientResponse(entity);
    }
}
