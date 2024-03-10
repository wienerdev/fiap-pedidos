package br.com.fiap.api.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiPedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPedidosApplication.class, args);
	}

}
