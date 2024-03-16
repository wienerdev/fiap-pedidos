package br.com.fiap.api.pedidos.entrypoint.controller;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.usecase.ClientUseCase;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.CreateClientRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.DeleteClientDataRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.ClientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/customers")
public class ClientController {

    private final ClientUseCase clientGateway;

    public ClientController(ClientUseCase clientGateway) {
        this.clientGateway = clientGateway;
    }


    @GetMapping("/{cpf}")
    public ResponseEntity<BaseResponse<ClientResponse>> getByCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                ClientResponse.fromClientResponse(clientGateway.getClientByCpf(cpf))), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<ClientResponse>> create(@RequestBody CreateClientRequest request) {
       Client client = clientGateway.saveClient(request.toClient());
        return new ResponseEntity<>(new BaseResponse<>(
                true,
               ClientResponse.fromClientResponse(client)), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<String>> delete(@RequestBody DeleteClientDataRequest request) {
        return new ResponseEntity<>(new BaseResponse<>(
                true,
                        clientGateway.deleteClient(request)), HttpStatus.OK);
    }
}
