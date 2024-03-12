package br.com.fiap.api.pedidos.dataprovider.configuration.message;

import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage {

    private Order order;

    private OrderEvent orderEvent;
}
