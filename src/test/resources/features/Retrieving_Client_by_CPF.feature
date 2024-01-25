# language: pt
Funcionalidade: Recuperar um Cliente por CPF

  Cenário: Recuperação bem-sucedida do cliente por CPF
    Dado que existe um cliente com CPF "01374050067"
    Quando o cliente solicita a recuperação do cliente por CPF
    Então  a resposta deve conter os detalhes do cliente

  Cenário: Recuperação de cliente por CPF inválido
    Dado que existe um CPF inválido "invalid_cpf"
    Quando o cliente solicita a recuperação do cliente por CPF
    Então  a resposta deve indicar que o cliente não foi encontrado


  Cenário: o cliente solicita a criação de um novo cliente
    Dado   a resposta deve conter os detalhes do cliente criado


