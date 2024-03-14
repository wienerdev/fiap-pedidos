<br>
<h1 align="center">
FiapFood 🍟
</h1>
<br>

## ❗IMPORTANTE - Urls dos MS de Pagamento e Produção

- https://github.com/wienerdev/fiap-production
- https://github.com/RoAlencar/Fiap-Pagamento

## 💬 Sobre o repositório

O FiapFood consiste em um sistema que é utilizado em lanchonetes e restaurantes, que processa todas as informações do contexto do pedido, desde a identificação do cliente, a escolha do pedido e o pagamento, até a entrega do pedido ao cliente.

## ⚠ Pré-requisitos para execução do projeto

* Java 17 ou inferior
* Maven
* MySQL

## 📌 Como utilizar?

Para utilizar o FiapPedidos, é necessário ter uma instância de conexão do banco de dados ativa (no caso MySQL), que por padrão fica na porta 3306, caso sua porta esteja diferente, especifique no application.properties em:

```
spring.datasource.url=jdbc:mysql://localhost:<PORTA_BD>/fiap-pedidos?createDatabaseIfNotExist=true
spring.datasource.username=<USUARIO_BD>
spring.datasource.password=<SENHA_BD>
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
![image](https://github.com/wienerdev/fiap-pedidos/assets/43960331/f7eba8ef-ee89-4110-b9a3-677dfac46454)

---
