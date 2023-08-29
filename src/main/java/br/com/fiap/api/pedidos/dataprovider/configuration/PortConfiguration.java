package br.com.fiap.api.pedidos.dataprovider.configuration;

import br.com.fiap.api.pedidos.core.dataprovider.repository.OrderRepository;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ProductRepository;
import br.com.fiap.api.pedidos.core.usecase.OrderUseCase;
import br.com.fiap.api.pedidos.core.usecase.ProductUseCase;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ClientRepository;
import br.com.fiap.api.pedidos.core.usecase.ClientUseCase;
import br.com.fiap.api.pedidos.core.usecase.impl.client.ClientUseCaseImpl;
import br.com.fiap.api.pedidos.core.usecase.impl.order.OrderUseCaseImpl;
import br.com.fiap.api.pedidos.core.usecase.impl.payments.MercadoPagoWebhookUseCase;
import br.com.fiap.api.pedidos.core.usecase.impl.product.ProductUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortConfiguration {

    @Bean
    ProductUseCase productUseCase(ProductRepository productRepositoryPort) {
        return new ProductUseCaseImpl(productRepositoryPort);
    }

    @Bean
    OrderUseCase orderUseCase(
            OrderRepository orderRepositoryPort,
            ProductRepository productRepositoryPort,
            ClientRepository clientRepositoryPort) {
        return new OrderUseCaseImpl(orderRepositoryPort, productRepositoryPort, clientRepositoryPort);
    }

    @Bean
    ClientUseCase clientUseCase(ClientRepository clientRepository) {
        return new ClientUseCaseImpl(clientRepository);
    }

    @Bean
    MercadoPagoWebhookUseCase mercadoPagoWebhookUseCase() {
        return new MercadoPagoWebhookUseCase();
    }
}
