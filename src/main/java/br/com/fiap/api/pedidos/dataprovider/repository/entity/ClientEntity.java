package br.com.fiap.api.pedidos.dataprovider.repository.entity;

import br.com.fiap.api.pedidos.core.Client;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    private UUID clientId;

    private String clientCpf;

    private String clientName;

    private String clientEmail;

    private String clientAddress;

    private String clientPhoneNumber;

    public ClientEntity() {
    }

    public ClientEntity(UUID clientId, String clientCpf, String clientName, String clientEmail, String clientAddress, String clientPhoneNumber) {
        this.clientId = clientId;
        this.clientCpf = clientCpf;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientAddress = clientAddress;
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public ClientEntity(Client client) {
        this.clientId = client.getClientId();
        this.clientName = client.getClientName();
        this.clientEmail = client.getClientEmail();
        this.clientCpf = client.getClientCpf();
        this.clientAddress = client.getClientAddress();
        this.clientPhoneNumber = client.getClientPhoneNumber();
    }

    public Client toClient() {
        return new Client(
                this.clientId,
                this.clientCpf,
                this.clientName,
                this.clientEmail,
                this.clientAddress,
                this.clientPhoneNumber);
    }
}
