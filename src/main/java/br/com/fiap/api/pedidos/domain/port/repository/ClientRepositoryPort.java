package br.com.fiap.api.pedidos.domain.port.repository;

import br.com.fiap.api.pedidos.domain.Client;


public interface ClientRepositoryPort {

    Client identifyClientByCpf(String cpf);
    Client registerClient(Client client);

}
