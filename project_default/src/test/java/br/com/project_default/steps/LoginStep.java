package br.com.project_default.steps;

import br.com.project_default.pages.LoginPage;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static br.com.project_default.utils.Utils.*;
import static org.junit.Assert.assertEquals;

public class LoginStep {

    LoginPage login = new LoginPage(getDriver());

    @Quando("ao informar ao Sistema as Credenciais usuário {string} e senha {string}")
    public void ao_informar_ao_Sistema_as_Credenciais_usuário_e_senha(String user, String password) {
       login.logar(user, password);
    }

    @Então("é verificado que está logado no Sistema visulaizando a mensagem {string}")
    public void é_verificado_que_está_logado_no_Sistema_visulaizando_a_mensagem(String texto) throws Exception {

        assertEquals(true, textoExiste(texto));
    }
}
