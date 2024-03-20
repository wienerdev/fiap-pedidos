<br>
<h1 align="center">
FiapFood 🍟
</h1>
<br>

## ❗IMPORTANTE - Urls dos MS de Pagamento e Produção

- https://github.com/alissonit/fiap-pedidos
- https://github.com/alissonit/fiap-production
- https://github.com/alissonit/fiap-pagamento

## 💬 Sobre o repositório

O FiapFood consiste em um sistema que é utilizado em lanchonetes e restaurantes, que processa todas as informações do contexto do pedido, desde a identificação do cliente, a escolha do pedido e o pagamento, até a entrega do pedido ao cliente.

## ⚠ Pré-requisitos para execução do projeto

* Java 17 ou inferior
* Maven
* MySQL
* MongoDB
* Kafka

## 📌 Como utilizar?

Para utilizar o FiapPedidos, é necessário ter uma instância de conexão do banco de dados ativa (no caso MySQL), que por padrão fica na porta 3306, caso sua porta esteja diferente, especifique no application.properties em:

```
server.port=8080
server.servlet.context-path=/pedido
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=${MYSQL_HOST}
spring.datasource.username=${MYSQL_USERNAME}
spring.datasource.password=${MYSQL_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

# Specify the path of the OpenAPI documentation
springdoc.api-docs.path=/api-docs
# Specify the path of the Swagger UI
springdoc.swagger-ui.path=/swagger-ui.html
# Enable or disable Swagger UI
springdoc.swagger-ui.enabled=true 
spring.jpa.generate-ddl=true
```

Com o banco de dados devidamente configurado, rode o [back-end da aplicação](https://github.com/wienerdev/sds) através do seguinte comando:

*Disponível em http://localhost:8080/

```
mvn spring-boot:run 
```

Com a aplicação rodando, acesse o localhost (porta 4200), e navegue pelo sistema!

## 🧠 Links importantes

* [Documentação oficial do Angular](https://angular.io/)
* [Site oficial do NodeJS](https://nodejs.org/en/)
* [Site oficial do NPM](https://www.npmjs.com/)
* [Referência para o padrão arquitetural REST](https://restfulapi.net/)
* [Palheta de atalhos de comandos do IntelliJ](https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf)
* [Site oficial do Spring](https://spring.io/)
* [Site oficial do Spring Initialzr para setup do projeto](https://start.spring.io/)
* [SDKMan! para gerenciamento e instalação do Java e Maven](https://sdkman.io/)
* [Site oficial do MySQL](https://www.mysql.com/)
* 


---

## Entrega Tech Challenge fase 5

Entrega Tech challenge fase 05

### Pipeline

- Fiap Pedidos

https://github.com/alissonit/fiap-pedidos/actions/runs/8346757102/job/22844801809

![screenshot](/images/pipe_pedidos.png)

- Fiap Pagamento

https://github.com/alissonit/fiap-pagamento/actions/runs/8347692011/job/22848065559

![screenshot](/images/pipe_pagamento.png)

- Fiap Production

https://github.com/alissonit/fiap-production/actions/runs/8348231780/job/22849655157

![screenshot](/images/pipe_production.png)

## Deploy na Cloud AWS

Evidência de deploy do serviço de pedidos:

![screenshot](/images/deploy_pedidos.png)


Evidência de deploy do serviço de pagamento:

![screenshot](/images/deploy_pagamentos.png)

Evidência de deploy do serviço de produção:

![screenshot](/images/deploy_producao.png)

Execução no SONAR:

![screenshot](/images/sonar.png)

Evidência aplicação em execução:

Pedido:

![screenshot](/images/swagger_pedido.png)

Pagamento:

![screenshot](/images/swagger_pagamento.png)

Produção:

![screenshot](/images/swagger_producao.png)

## RDS Mysql

![screenshot](/images/mysql.png)

## MongoDB Atlas

Métricas de acesso:

![screenshot](/images/mongodb.png)
![screenshot](/images/mongodb_metrics.png)

## Evidências KAFKA SaaS

![screenshot](/images/kafka_orders.png)

![screenshot](/images/kafka_orders_2.png)

![screenshot](/images/kafka_payment.png)

![screenshot](/images/kafka_payment_2.png)

### Desenho SAGA:

Estou usando o padrão SAGA de coreografia com o Kafka em três serviços: Pedido, Pagamento e Produção, para coordenar transações distribuídas de forma descentralizada, garantir atomicidade nas operações, aproveitar a flexibilidade e escalabilidade do Kafka para comunicação entre os serviços, e obter auditoria e visibilidade através do registro de eventos do Kafka. Isso resulta em uma arquitetura resiliente e altamente escalável, com garantia de atomicidade e monitoramento detalhado do fluxo de pedidos.

![screenshot](/images/saga.png)

## Link OWASP ZAP

https://drive.google.com/drive/folders/1Ku1gZSvSISKbuN28j3_3Zm04C2y1zYQp?usp=sharing

### Link RIPD

https://docs.google.com/document/d/1LtdV65wnCPWYilI6BW09OqeHAyrsZLX-/edit?usp=drive_link&ouid=110299184513916109783&rtpof=true&sd=true


### IaC (Infra as Code)

ECS-PEDIDO

https://github.com/alissonit/fiap-pedidos-iac-ecs/actions/runs/8350325962/job/22856465495

ECS-PRODUCAO

https://github.com/alissonit/fiap-producao-iac-ecs/actions/runs/8350922073/job/22858314023

ECS-PAGAMENTO

https://github.com/alissonit/fiap-pagamento/actions/runs/8347692011/job/22848065559






