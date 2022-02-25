#language: pt
#enconding: UTF-8


Funcionalidade: Login
  Eu como um standard_user do site
  Quero logar na aplicação
  Para fazer a compra de 3 ou mais itens

  @CT_001_006_login
  Esquema do Cenário: CT - 001 - 006 - Logar na Aplicação
    Quando ao informar ao Sistema as Credenciais usuário <user> e senha <password>
    Então é verificado que está logado no Sistema visulaizando a mensagem <mensagem_logada>

    Exemplos:
      | user            | password       | mensagem_logada                                                             |
      | ''              | ''             | 'Epic sadface: Username is required'                                        |
      | 'standard_user' | ''             | 'Epic sadface: Password is required'                                        |
      | ''              | 'secret_sauce' | 'Epic sadface: Username is required'                                        |
      | 'standard_user' | '12345678'     | 'Epic sadface: Username and password do not match any user in this service' |
      | 'frudo_pinho'   | '12345678'     | 'Epic sadface: Username and password do not match any user in this service' |
      | 'standard_user' | 'secret_sauce' | 'Products'                                                                  |