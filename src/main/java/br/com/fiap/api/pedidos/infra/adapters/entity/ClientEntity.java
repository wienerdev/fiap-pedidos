package br.com.fiap.api.pedidos.infra.adapters.entity;

import br.com.fiap.api.pedidos.domain.Client;
import br.com.fiap.api.pedidos.domain.Product;
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

    public ClientEntity() {
    }

    public ClientEntity( Client client) {


        this.clientName = client.getClientName();
        this.clientEmail = client.getClientEmail();
        this.clientCpf = client.getClientCpf();
    }

    public Client toClient() {
        return new Client(this.clientId = clientId,
        this.clientCpf = clientCpf,
        this.clientName = clientName,
        this.clientEmail = clientEmail);
    }
}
