package br.com.project_default.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    public LoginPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "user-name")
    private WebElement inputUser ;

    @FindBy(how = How.ID, using = "password")
    private WebElement inputPassword ;

    @FindBy(how = How.ID, using = "login-button")
    private WebElement btnLogin ;

    public void logar(String user, String password){
        inputUser.sendKeys(user);
        inputPassword.sendKeys(password);
        btnLogin.click();
    }

}
