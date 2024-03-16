package br.com.fiap.api.pedidos.core.dataprovider.repository;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;

import java.util.Optional;


public interface ClientRepository {

    Optional<Client> identifyClientByCpf(String cpf);
    Client registerClient(Client client);
    void deleteClient(String clientName, String clientAddress, String phoneNumber);

}
