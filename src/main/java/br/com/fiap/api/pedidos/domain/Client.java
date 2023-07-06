package br.com.fiap.api.pedidos.domain;

import java.util.UUID;

public class Client {
    private UUID clientId;

    private String clientCpf;

    private String clientName;

    private String clientEmail;

    public Client(UUID clientId, String clientCpf, String clientName, String clientEmail) {
        this.clientId = clientId;
        this.clientCpf = clientCpf;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
    }

    public Client() {

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
}
