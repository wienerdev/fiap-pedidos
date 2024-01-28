# language: pt
Funcionalidade: Funcionalidades do ProductUseCase

  Cenário: Obter todos os produtos
    Quando eu obtenho todos os produtos
    Então a lista de produtos não deve estar vazia

  Cenário: Obter produtos por categoria
    Dado uma categoria "Eletrônicos"
    Quando eu obtenho produtos por categoria
    Então a lista de produtos não deve estar vazia


