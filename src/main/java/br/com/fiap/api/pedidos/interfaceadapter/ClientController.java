package br.com.fiap.api.pedidos.interfaceadapter;

import br.com.fiap.api.pedidos.application.gateway.ClientGateway;
import br.com.fiap.api.pedidos.domain.dto.request.CreateClientRequest;
import br.com.fiap.api.pedidos.domain.dto.response.ClientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/customers")
public class ClientController {

    private final ClientGateway clientGateway;


public ClientController(ClientGateway clientGateway) {
    this.clientGateway = clientGateway;
}

    @GetMapping("/{cpf}")
    public ResponseEntity<ClientResponse> getByCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(clientGateway.getClientByCpf(cpf), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody CreateClientRequest request) {
        return new ResponseEntity<>(clientGateway.createClient(request), HttpStatus.CREATED);
    }
}
