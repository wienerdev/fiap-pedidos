package br.com.fiap.api.pedidos.dataprovider.repository.impl;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ClientRepository;
import br.com.fiap.api.pedidos.core.exception.ClientAlreadyRegistered;
import br.com.fiap.api.pedidos.core.exception.ClientNotFoundException;
import br.com.fiap.api.pedidos.dataprovider.repository.ClientRepositoryJpa;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientRepositoryJpa clientRepository;

    public ClientRepositoryImpl(ClientRepositoryJpa clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Optional<Client> identifyClientByCpf(String cpf) {
        Optional<ClientEntity> clientEntity = this.clientRepository.findByClientCpf(cpf);
        if (!clientEntity.isPresent()) {
            throw new ClientNotFoundException("client not found");
        }
        return Optional.ofNullable(clientEntity.get().toClient());

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
