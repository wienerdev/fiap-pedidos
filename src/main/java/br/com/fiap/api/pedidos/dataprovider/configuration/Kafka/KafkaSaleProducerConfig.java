package br.com.fiap.api.pedidos.dataprovider.configuration.Kafka;
import br.com.fiap.api.pedidos.dataprovider.configuration.message.OrderMessage;
import br.com.fiap.api.pedidos.dataprovider.configuration.message.PaymentMessage;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.CommonClientConfigs.SECURITY_PROTOCOL_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.*;
import static org.apache.kafka.common.config.SaslConfigs.SASL_JAAS_CONFIG;
import static org.apache.kafka.common.config.SaslConfigs.SASL_MECHANISM;

@Configuration
public class KafkaSaleProducerConfig {

    // Confluent Cloud credentials
    @Value("${spring.kafka.bootstrap-servers}")
    private String CONFLUENT_CLOUD_BOOTSTRAP_SERVERS_VALUE;

    @Value("${spring.kafka.properties.sasl.jaas.config}")
    private String SASL_JAAS_CONFIG_VALUE;

    @Value("${spring.kafka.properties.sasl.mechanism}")
    private String SASL_SSL_VALUE;

    @Value("${spring.kafka.properties.security.protocol}")
    private String SASL_MECHANISM_VALUE;

    @Bean
    public ProducerFactory<String, OrderMessage> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(BOOTSTRAP_SERVERS_CONFIG, CONFLUENT_CLOUD_BOOTSTRAP_SERVERS_VALUE);
        configProps.put(SASL_JAAS_CONFIG, SASL_JAAS_CONFIG_VALUE);
        configProps.put(SECURITY_PROTOCOL_CONFIG, SASL_SSL_VALUE);
        configProps.put(SASL_MECHANISM, SASL_MECHANISM_VALUE);
        configProps.put(GROUP_ID_CONFIG, "orders");
        configProps.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, OrderMessage> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, PaymentMessage> producerFactoryPayment() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(BOOTSTRAP_SERVERS_CONFIG, CONFLUENT_CLOUD_BOOTSTRAP_SERVERS_VALUE);
        configProps.put(SASL_JAAS_CONFIG, SASL_JAAS_CONFIG_VALUE);
        configProps.put(SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        configProps.put(SASL_MECHANISM, "PLAIN");
        configProps.put(GROUP_ID_CONFIG, "payment");
        configProps.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializerPayment.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, PaymentMessage> kafkaPayment() {
        return new KafkaTemplate<>(producerFactoryPayment());
    }
}
