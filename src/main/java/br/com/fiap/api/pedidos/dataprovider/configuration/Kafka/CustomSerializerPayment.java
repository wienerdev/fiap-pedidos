package br.com.fiap.api.pedidos.dataprovider.configuration.Kafka;


import br.com.fiap.api.pedidos.dataprovider.configuration.message.PaymentMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

@Component
public class CustomSerializerPayment implements Serializer<PaymentMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, PaymentMessage paymentMessage) {
       try {
            if (paymentMessage == null){
                return null;
            }
            return objectMapper.writeValueAsBytes(paymentMessage);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing SaleMessage to byte[]");
        }
    }

}
