package br.com.fiap.api.pedidos.dataprovider.configuration.message;


import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage {

    private String productionId;

    private String clientCpf;

    private UUID orderId;

    private Boolean isPaymentReceived;

    private Double orderPrice;

    private List<UUID> productId;

    private OrderEvent orderEvent;
}
