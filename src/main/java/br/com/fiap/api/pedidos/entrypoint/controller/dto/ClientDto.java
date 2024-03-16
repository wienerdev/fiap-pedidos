package br.com.fiap.api.pedidos.entrypoint.controller.dto;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ClientEntity;

import java.util.UUID;

public record ClientDto(UUID clientId, String clientCpf, String clientName,String clientEmail, String clientAddress, String clientPhoneNumber) {
        public static ClientEntity fromClientToClientEntity(Client client) {
            return new ClientEntity(client.getClientId(), client.getClientCpf(),client.getClientName(), client.getClientEmail(), client.getClientAddress(), client.getClientPhoneNumber());
        }
    }