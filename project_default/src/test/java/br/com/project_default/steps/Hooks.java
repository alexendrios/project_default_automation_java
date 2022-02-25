package br.com.project_default.steps;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.util.ArrayList;

import static br.com.project_default.utils.Utils.*;

public class Hooks {
    private ArrayList<String> infoSystem;

    @Before
    public void setup(){
        getDriver().get("https://www.saucedemo.com/");
    }

    @After
    public void tearDown(Scenario scenario) throws Exception{
        infoSystem = new ArrayList<String>();
        infoSystem.add("swaglab");
        infoSystem.add("© 2022");
        infoSystem.add("Produção");
        capturarScreenshot(scenario);
        addEnvironmentAllure(infoSystem);
        killDriver();
    }
}
