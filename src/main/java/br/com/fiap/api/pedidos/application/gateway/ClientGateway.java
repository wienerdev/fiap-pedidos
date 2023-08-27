package br.com.fiap.api.pedidos.application.gateway;

import br.com.fiap.api.pedidos.domain.dto.request.CreateClientRequest;
import br.com.fiap.api.pedidos.domain.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.domain.dto.response.ClientResponse;
import br.com.fiap.api.pedidos.domain.port.usecase.ClientUseCasePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ClientGateway {

    private final ClientUseCasePort clientUseCasePort;

    public ClientGateway(ClientUseCasePort clientUseCasePort) {
        this.clientUseCasePort = clientUseCasePort;
    }

    public ResponseEntity<BaseResponse<ClientResponse>> getClientByCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(clientUseCasePort.getClientByCpf(cpf), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse<ClientResponse>> createClient(@RequestBody CreateClientRequest request) {
        return new ResponseEntity<>(clientUseCasePort.saveClient(request), HttpStatus.CREATED);
    }
}
