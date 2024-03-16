package br.com.fiap.api.pedidos.core;

import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.ClientResponse;

import java.util.UUID;

public class Client {
    private UUID clientId;
    private String clientCpf;
    private String clientName;
    private String clientAddress;
    private String clientPhoneNumber;
    private String clientEmail;

    public Client(UUID clientId, String clientCpf, String clientName,String clientEmail, String clientAddress, String clientPhoneNumber) {
        this.clientId = clientId;
        this.clientCpf = clientCpf;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientAddress = clientAddress;
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public Client() {

    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getClientCpf() {
        return clientCpf;
    }

    public void setClientCpf(String clientCpf) {
        this.clientCpf = clientCpf;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }


    public ClientResponse toResponse() {
        return new ClientResponse(this.clientId, this.clientCpf, this.clientName, this.clientEmail, this.clientAddress, this.clientPhoneNumber);
    }

    public ClientEntity toEntity() {
        return new ClientEntity(this.clientId, this.clientCpf, this.clientName, this.clientEmail, this.clientAddress, this.clientPhoneNumber);
    }
}
