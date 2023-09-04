package br.com.fiap.api.pedidos.dataprovider.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    RECEIVED("The order was received."),
    PREPARING("In Progress."),
    DONE("The order has been done."),
    FINISHED("The order has been finished."),
    CANCELED("The order has been canceled.");

    private final String message;

}
