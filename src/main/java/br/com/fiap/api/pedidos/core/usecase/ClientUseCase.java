package br.com.fiap.api.pedidos.core.usecase;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.DeleteClientDataRequest;


public interface ClientUseCase {

    Client getClientByCpf(String cpf);
    Client saveClient(Client client);
    String deleteClient(DeleteClientDataRequest request);
}
