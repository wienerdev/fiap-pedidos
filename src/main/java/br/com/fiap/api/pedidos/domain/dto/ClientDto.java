package br.com.fiap.api.pedidos.domain.dto;

import br.com.fiap.api.pedidos.domain.Client;
import br.com.fiap.api.pedidos.infra.adapters.entity.ClientEntity;

import java.util.UUID;

public record ClientDto(UUID clientId, String clientName, String clientEmail, String clientCpf ) {
        public static ClientEntity fromClientToClientEntity(Client client) {
            return new ClientEntity(client.getClientId(), client.getClientName(), client.getClientEmail(),client.getClientCpf());
        }
    }