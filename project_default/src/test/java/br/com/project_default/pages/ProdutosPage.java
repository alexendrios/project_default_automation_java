package br.com.project_default.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

import static br.com.project_default.utils.Utils.*;
import static br.com.project_default.utils.Utils.findElement;

public class ProdutosPage {
    private String status = "";

    public ProdutosPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = ".product_sort_container")
    private WebElement selectOrdenar;

    @FindBy(how = How.ID, using = "inventory_container")
    private WebElement listprodutos;

    @FindBy(how = How.CSS, using = ".shopping_cart_link")
    private WebElement carrinho;

    @FindBy(how = How.ID, using = "last-name")
    private WebElement inputLastName;

    @FindBy(how = How.ID, using = "checkout")
    private WebElement btnCheck;

    @FindBy(how = How.ID, using = "first-name")
    private WebElement inputFistName;

    @FindBy(how = How.ID, using = "postal-code")
    private WebElement inputZipPostal;

    @FindBy(how = How.ID, using = "continue")
    private WebElement btnContinue;

    @FindBy(how = How.ID, using = "cancel")
    private WebElement btnCancelar;

    @FindBy(how = How.ID, using = "finish")
    private WebElement btnFinalizar;

    public String getStatus() {
        return this.status;
    }

    public void preencherDados(String nome, String sobrenome, String cep) {
        btnCheck.click();
        inputFistName.sendKeys(nome);
        inputLastName.sendKeys(sobrenome);
        inputZipPostal.sendKeys(cep);
        btnContinue.click();
    }

    public void finalizarCompra(String opcao) {
        if (opcao.equals("true")) {
            btnFinalizar.click();
        }else{
            btnCancelar.click();
        }
    }

        public void ordenar (String tipoOrdenar){
            int index = 0;
            String ordeTipo = "";
            Select produto = new Select(selectOrdenar);
            switch (tipoOrdenar) {
                case "Name (A to Z)":
                    index = 0;
                    ordeTipo = "nome";
                    break;
                case "Name (Z to A)":
                    index = 1;
                    ordeTipo = "nome";
                    break;
                case "Price (low to high)":
                    index = 2;
                    ordeTipo = "valor";
                    break;
                case "Price (high to low)":
                    index = 3;
                    ordeTipo = "valor";
                    break;
            }
            produto.selectByIndex(index);
            if (ordeTipo.equals("nome")) {
                ordenacaoNome(tipoOrdenar);
            } else {
                ordenacaoValor(tipoOrdenar);
            }

        }

        public void selecionarProduto ( int produto){
            for (int i = 1; i <= produto; i++) {
                findElement("/html/body/div/div/div/div[2]/div/div/div/div[" + i + "]/div[2]/div[2]/button").click();
            }
        }

        public void removerProdutos ( int quantidade){
            int index = 3;
            for (int i = 0; i < quantidade; i++) {
                findElement("/html/body/div/div/div/div[2]/div/div[1]/div[" + index + "]/div[2]/div[2]/button").click();
                index += 1;
            }
        }

        public void checarCarrinho () {
            carrinho.click();
        }

        public Integer quantidadeProdutos () {
            Integer produto = 0;
            if (textoExiste("Remove")) {
                int n = 3;
                for (int i = 0; i < 6; i++) {

                    boolean status = elementoExiste("/html/body/div/div/div/div[2]/div/div[1]/div[" + n + "]/div[2]/div[2]/button");
                    if (status) {
                        produto += 1;
                    }
                    n += 1;
                }
            } else {
                produto = 0;
            }
            return produto;
        }

        private void ordenacaoNome (String tipo){
            if (tipo.equals("Name (A to Z)") || tipo.equals("Name (Z to A)")) {
                ArrayList<Integer> ordenacaoNome = new ArrayList<Integer>();
                String[] texto = listprodutos.getText().split("\n");
                for (int i = 0; i < texto.length; i += 4) {
                    int num;
                    num = texto[i].charAt(0);
                    ordenacaoNome.add(num);
                }
                if (tipo.equals("Name (A to Z)")) {
                    if (ordenacaoNome.get(0) < ordenacaoNome.get(ordenacaoNome.size() - 1)) {
                        status = "true";
                    } else {
                        status = "false";
                    }
                } else if (tipo.equals("Name (Z to A)")) {
                    if (ordenacaoNome.get(0) > ordenacaoNome.get(ordenacaoNome.size() - 1)) {
                        status = "true";
                    } else {
                        status = "false";
                    }
                }
            }
        }

        private void ordenacaoValor (String tipo){
            if (tipo.equals("Price (low to high)") || tipo.equals("Price (high to low)")) {
                ArrayList<Float> ordenacaoNome = new ArrayList<Float>();
                String[] texto = listprodutos.getText().split("\n");
                for (int i = 0; i < texto.length; i += 2) {
                    if (texto[i].startsWith("$")) {
                        float num = Float.parseFloat(texto[i].substring(1, texto[i].length()));
                        ordenacaoNome.add(num);
                    }
                }

                if (tipo.equals("Price (low to high)")) {
                    if (ordenacaoNome.get(0) < ordenacaoNome.get(ordenacaoNome.size() - 1)) {
                        status = "true";
                    } else {
                        status = "false";
                    }
                } else if (tipo.equals("Price (high to low)")) {
                    if (ordenacaoNome.get(0) > ordenacaoNome.get(ordenacaoNome.size() - 1)) {
                        status = "true";
                    } else {
                        status = "false";
                    }
                }
            }
        }
    }


