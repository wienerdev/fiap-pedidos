package br.com.fiap.api.pedidos.dataprovider.configuration.Kafka;


import br.com.fiap.api.pedidos.dataprovider.configuration.message.OrderMessage;
import br.com.fiap.api.pedidos.dataprovider.configuration.message.PaymentMessage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.CommonClientConfigs.SECURITY_PROTOCOL_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.apache.kafka.common.config.SaslConfigs.SASL_JAAS_CONFIG;
import static org.apache.kafka.common.config.SaslConfigs.SASL_MECHANISM;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

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
    public ConsumerFactory<String, PaymentMessage> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, CONFLUENT_CLOUD_BOOTSTRAP_SERVERS_VALUE);
        props.put(SASL_JAAS_CONFIG, SASL_JAAS_CONFIG_VALUE);
        props.put(SECURITY_PROTOCOL_CONFIG, SASL_SSL_VALUE);
        props.put(SASL_MECHANISM, SASL_MECHANISM_VALUE);
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class);
        props.put(AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentMessage> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PaymentMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
