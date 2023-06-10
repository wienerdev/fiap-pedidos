package br.com.fiap.api.pedidos.infra.adapters.repository;

import br.com.fiap.api.pedidos.domain.Client;
import br.com.fiap.api.pedidos.domain.exception.ClientNotFoundException;
import br.com.fiap.api.pedidos.domain.port.repository.ClientRepositoryPort;
import br.com.fiap.api.pedidos.infra.adapters.entity.ClientEntity;
import br.com.fiap.api.pedidos.infra.adapters.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientRepositoryAdapter implements ClientRepositoryPort {

    private final ClientRepository clientRepository;

    public ClientRepositoryAdapter(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client identificarClientePorCPF(String cpf) {
        Optional<ClientEntity> clientEntity = this.clientRepository.findByClientCpf(cpf);
        if (clientEntity.isPresent())
            return clientEntity.get().toClient();

        throw new ClientNotFoundException("Unidentified client");
    }


    @Override
    public Client cadastrarCliente(Client client) {
        ClientEntity clientEntity = new ClientEntity(client);
        return clientRepository.save(clientEntity).toClient();
    }
}
