#language: pt
#enconding: UTF-8

Funcionalidade: Comprar  Produtos
  Eu como um standard_user do site
  Deve ser possível filtrar os itens do preço mais baixo
  Deve ser possível remover quaisquer itens do carrinho antes de fazer checkout.
  Os campos First Name, Last name e Zip/Postal code são de preenchimento obrigatório.
  Deve ser possível cancelar a compra antes de finalizar.

  Contexto: Logar na Aplicação
    Dado ao logar na aplicação com as credenciais usuário "standard_user" e senha "secret_sauce"

  @CT_0012_018_Filtrar_Produtos
  Esquema do Cenário: CT - 0012 - 018 - Remover Produtos
    Quando ao selecionar os produtos pelo tipo <tipo_ordenacao>
    E ao escolher a quantidade de produtos <quantidade>
    E ao checar no carrino
    E remover a quantidade de produtos <qtd_remover>
    Então deve se visualizada a quantidade de produtos na tela <qtd_produt_tela>


    Exemplos:
      | tipo_ordenacao        | quantidade | qtd_remover | qtd_produt_tela |
      | 'Price (low to high)' | 1          | 1           | 0               |
      | 'Price (low to high)' | 2          | 1           | 1               |
      | 'Price (low to high)' | 3          | 1           | 2               |
      | 'Price (low to high)' | 4          | 3           | 1               |
      | 'Price (low to high)' | 5          | 4           | 1               |
      | 'Price (low to high)' | 6          | 3           | 3               |

  @CT_0019_20_Realizar_Compra
  Esquema do Cenário: CT - 0019 - 020 - Realizar a Compra
    Quando ao selecionar os produtos pelo tipo <tipo_ordenacao>
    E ao escolher a quantidade de produtos <quantidade>
    E ao checar no carrino
    E ao prencher o primeiro nome <fistname>, o sobrenome <lastname>e o CEP<zipPostal>
    E ao decidir se irá finalizar a operação <operacao>
    Então visualizo a seguinte nebsagen <mensagem>

    Exemplos:
      | tipo_ordenacao        | quantidade | fistname  | lastname   | zipPostal   | operacao | mensagem   |
      | 'Price (low to high)' | 3          | "Ricardo" | "Pinheiro" | "86820-000" | "false"  | "Products" |
      | 'Price (low to high)' | 3          | "Ricardo" | "Pinheiro" | "86820-000" | "true"  | "THANK YOU FOR YOUR ORDER" |


