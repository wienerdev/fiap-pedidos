package br.com.fiap.api.pedidos.core.usecase;

import br.com.fiap.api.pedidos.core.Client;


public interface ClientUseCase {

    Client getClientByCpf(String cpf);
    Client saveClient(Client client);
}
