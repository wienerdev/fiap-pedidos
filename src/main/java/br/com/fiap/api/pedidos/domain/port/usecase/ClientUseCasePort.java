package br.com.fiap.api.pedidos.domain.port.usecase;

import br.com.fiap.api.pedidos.domain.dto.request.CreateClientRequest;
import br.com.fiap.api.pedidos.domain.dto.response.ClientResponse;


public interface ClientUseCasePort {

    ClientResponse getClientByCpf(String cpf);
    ClientResponse saveClient(CreateClientRequest request);
}
