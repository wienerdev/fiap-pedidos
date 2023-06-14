package br.com.fiap.api.pedidos.application.gateway;

import br.com.fiap.api.pedidos.domain.dto.request.CreateClientRequest;
import br.com.fiap.api.pedidos.domain.dto.response.ClientResponse;
import br.com.fiap.api.pedidos.domain.port.usecase.ClientUseCasePort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ClientGateway {

    private final ClientUseCasePort clientUseCasePort;

    public ClientGateway(ClientUseCasePort clientUseCasePort) {
        this.clientUseCasePort = clientUseCasePort;
    }

    public ClientResponse getClientByCpf(@PathVariable String cpf) {
        return clientUseCasePort.getClientByCpf(cpf);
    }

    public ClientResponse createClient(@RequestBody CreateClientRequest request) {
        return clientUseCasePort.saveClient(request);
    }
}
