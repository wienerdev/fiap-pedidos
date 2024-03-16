package br.com.fiap.api.pedidos.dataprovider.configuration.Kafka;

import br.com.fiap.api.pedidos.dataprovider.configuration.message.PaymentMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class CustomDeserializerPayment implements Deserializer<PaymentMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PaymentMessage deserialize(String topic, byte[] data) {
       try {
            if (data == null || data.length == 0) {
                return null;
            }
            return objectMapper.readValue(data, PaymentMessage.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to OrderMessage", e);
        }
    }
}