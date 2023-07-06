package br.com.fiap.api.pedidos.infra.adapters.repository;

import br.com.fiap.api.pedidos.domain.Client;
import br.com.fiap.api.pedidos.domain.exception.ClientAlreadyRegistered;
import br.com.fiap.api.pedidos.domain.exception.ClientNotFoundException;
import br.com.fiap.api.pedidos.domain.port.repository.ClientRepositoryPort;
import br.com.fiap.api.pedidos.infra.adapters.entity.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientRepositoryAdapter implements ClientRepositoryPort {

    private final ClientRepository clientRepository;

    public ClientRepositoryAdapter(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client identifyClientByCpf(String cpf) {
        Optional<ClientEntity> clientEntity = this.clientRepository.findByClientCpf(cpf);
        if (clientEntity.isPresent())
            return clientEntity.get().toClient();

        throw new ClientNotFoundException("Unidentified client");
    }


    @Override
    public Client registerClient(Client client) {
        Optional<ClientEntity> clientEntity = this.clientRepository.findByClientCpf(client.getClientCpf());
        if (clientEntity.isPresent()) {
            throw new ClientAlreadyRegistered("Customer already registered");
        }
        ClientEntity clientEntitySave = new ClientEntity(client);
        return clientRepository.save(clientEntitySave).toClient();
    }
}
