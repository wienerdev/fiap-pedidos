package br.com.fiap.api.pedidos.dataprovider.configuration.Kafka;

import br.com.fiap.api.pedidos.dataprovider.configuration.message.OrderMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

@Component
public class CustomSerializer implements Serializer<OrderMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, OrderMessage orderMessage) {

        try {
            if (orderMessage == null){
                return null;
            }
            return objectMapper.writeValueAsBytes(orderMessage);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing OrderMessage to byte[]",e);
        }
    }

}
