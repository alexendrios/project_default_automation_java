#language: pt
#enconding: UTF-8


Funcionalidade: Filtrar Produtos
  Eu como um standard_user do site
  Deve ser possível filtrar os itens do preço mais baixo para o preço mais alto
  Para fazer a compra de 3 ou mais itens

  Contexto: Logar na Aplicação
    Dado ao logar na aplicação com as credenciais usuário "standard_user" e senha "secret_sauce"

  @CT_007_011_Filtrar_Produtos
  Esquema do Cenário: CT - 007 - 010 - Filtrar Produtos
    Quando ao selecionar os produtos pelo tipo <tipo_ordenacao>
    Então visualiza - se os produtos ordenados <produtos_ordenados>

    Exemplos:
      | tipo_ordenacao      | produtos_ordenados |
      | 'Name (A to Z)'       | 'true'             |
      | 'Name (Z to A)'       | 'true'             |
      | 'Price (low to high)' | 'true'             |
      | 'Price (high to low)' | 'true'             |

