package br.com.fiap.api.pedidos.infra.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    CREATED("The order was created."),
    IN_PROGRESS("In Progress."),
    COMPLETED("The order has been completed."),
    CLOSED("The order has been closed."),
    CANCELED("The order has been canceled.");

    private String message;

}
