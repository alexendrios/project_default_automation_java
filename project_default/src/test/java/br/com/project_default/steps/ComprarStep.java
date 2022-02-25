package br.com.project_default.steps;

import br.com.project_default.pages.ProdutosPage;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import static br.com.project_default.utils.Utils.*;
import static org.junit.Assert.assertEquals;

public class ComprarStep {
    ProdutosPage produto = new ProdutosPage(getDriver());

    @Quando("ao escolher a quantidade de produtos {int}")
    public void ao_escolher_a_quantidade_de_produtos(Integer qtd) throws Exception {
        produto.selecionarProduto(qtd);
    }

    @Quando("ao checar no carrino")
    public void ao_checar_no_carrino() throws Exception {
       produto.checarCarrinho();
    }

    @Quando("remover a quantidade de produtos {int}")
    public void remover_a_quantidade_de_produtos(Integer qtd) {
       produto.removerProdutos(qtd);
    }

    @Quando("ao prencher o primeiro nome {string}, o sobrenome {string}e o CEP{string}")
    public void ao_prencher_o_primeiro_nome_o_sobrenome_e_o_CEP(String nome, String sobrenome, String cep) {
        produto.preencherDados(nome, sobrenome, cep);
    }

    @Quando("ao decidir se irá finalizar a operação {string}")
    public void ao_decidir_se_irá_finalizar_a_operação(String opcao) {

       produto.finalizarCompra(opcao);
    }


    @Então("deve se visualizada a quantidade de produtos na tela {int}")
    public void deve_se_visualizada_a_quantidade_de_produtos_na_tela(Integer qtd) {
         Assert.assertEquals(qtd, produto.quantidadeProdutos());
    }

    @Então("visualizo a seguinte nebsagen {string}")
    public void visualizo_a_seguinte_nebsagen(String texto) throws Exception {
        esperarPagina(2);
        assertEquals(true, textoExiste(texto));
    }
}
