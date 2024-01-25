package br.com.fiap.api.pedidos.acceptance;


import br.com.fiap.api.pedidos.ApiPedidosApplicationTests;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
@SpringBootTest(classes = ApiPedidosApplicationTests.class)
public class CucumberTestRunner {

}