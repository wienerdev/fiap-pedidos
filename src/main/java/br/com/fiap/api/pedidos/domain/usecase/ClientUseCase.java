package br.com.fiap.api.pedidos.domain.usecase;

import br.com.fiap.api.pedidos.domain.Client;
import br.com.fiap.api.pedidos.domain.Product;
import br.com.fiap.api.pedidos.domain.dto.request.CreateClientRequest;
import br.com.fiap.api.pedidos.domain.dto.response.ClientResponse;
import br.com.fiap.api.pedidos.domain.dto.response.ProductResponse;
import br.com.fiap.api.pedidos.domain.port.repository.ClientRepositoryPort;
import br.com.fiap.api.pedidos.domain.port.usecase.ClientUseCasePort;
import br.com.fiap.api.pedidos.domain.port.usecase.ProductUseCasePort;
import org.modelmapper.ModelMapper;

import java.util.UUID;

public class ClientUseCase implements ClientUseCasePort {

    private final ClientRepositoryPort clientRepository;

    private final ModelMapper mapper;

    public ClientUseCase(ClientRepositoryPort clientRepository, ModelMapper mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    @Override
    public ClientResponse getClientByCpf(String cpf) {

        return mapper.map(clientRepository.identificarClientePorCPF(cpf), ClientResponse.class);
    }

    @Override
    public ClientResponse saveClient(CreateClientRequest request) {
        Client entity = mapper.map(request, Client.class);
        entity.setClientId(UUID.randomUUID());
        clientRepository.cadastrarCliente(entity);
        return mapper.map(entity, ClientResponse.class);
    }
}
