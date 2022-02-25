package br.com.project_default.steps;

import br.com.project_default.pages.LoginPage;
import br.com.project_default.pages.ProdutosPage;
import io.cucumber.java.gl.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static br.com.project_default.utils.Utils.esperarPagina;
import static br.com.project_default.utils.Utils.getDriver;
import static org.junit.Assert.assertEquals;

public class FiltroStep {

    ProdutosPage produto = new ProdutosPage(getDriver());

    @Dado("ao logar na aplicação com as credenciais usuário {string} e senha {string}")
    public void ao_logar_na_aplicação_com_as_credenciais_usuário_e_senha(String user, String password) {
        LoginPage login = new LoginPage(getDriver());
        login.logar(user, password);
    }

    @Quando("ao selecionar os produtos pelo tipo {string}")
    public void ao_selecionar_os_produtos_pelo_tipo(String tipo) throws Exception {
        produto.ordenar(tipo);
    }

    @Então("visualiza - se os produtos ordenados {string}")
    public void visualiza_se_os_produtos_ordenados(String retorno) {
       assertEquals(retorno, produto.getStatus());
    }
}
