package br.com.fiap.api.pedidos.dataprovider.configuration.message;



import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMessage {

    private UUID orderId;
    private String paymentMethod;
    private OrderEvent orderEvent;
}