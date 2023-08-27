package br.com.fiap.api.pedidos.domain.port.usecase;

import br.com.fiap.api.pedidos.domain.dto.request.CreateClientRequest;
import br.com.fiap.api.pedidos.domain.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.domain.dto.response.ClientResponse;


public interface ClientUseCasePort {

    BaseResponse<ClientResponse> getClientByCpf(String cpf);
    BaseResponse<ClientResponse> saveClient(CreateClientRequest request);
}
