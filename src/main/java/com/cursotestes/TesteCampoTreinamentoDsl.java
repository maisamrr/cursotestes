package com.cursotestes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamentoDsl {
    private WebDriver driver;
    private DSL dsl;

    @Before
    public void initializeWebDriver() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().setPosition(new Point(1800, 20));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void shutWebDriver() {
        driver.quit();
    }

    @Test
    public void testarTextField() {
        dsl.preencherCampo("elementosForm:nome", "Teste de escrita");
        Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void testarTextArea() {
        dsl.preencherCampo("elementosForm:sugestoes", "Teste");
        Assert.assertEquals("Teste", dsl.obterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void testarRadioButton() {
       dsl.clicarRadio("elementosForm:sexo:0");
       Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void testarCheckBox() {
        dsl.clicarRadio("elementosForm:comidaFavorita:3");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:3"));
    }

    @Test
    public void testarDropdown() {
       dsl.selecionarDropdown("elementosForm:escolaridade", "2o grau completo");
        Assert.assertEquals("2o grau completo", dsl.obterValorDropdown("elementosForm:escolaridade"));
    }

    @Test
    public void verificarValorDropdown() {
        WebElement elementEscolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
        Select dropdownSelect = new Select(elementEscolaridade);
        List<WebElement> options = dropdownSelect.getOptions();

        Assert.assertEquals(8, options.size());
    }

    @Test
    public void selecionarMultiplosDropdown() {
        dsl.selecionarDropdown("elementosForm:esportes", "Corrida");
        dsl.selecionarDropdown("elementosForm:esportes", "Karate");

        WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
        Select dropdownSelect = new Select(elemento);
        List<WebElement> selectedOptions = dropdownSelect.getAllSelectedOptions();
        Assert.assertEquals(2, selectedOptions.size());
    }

    @Test
    public void testarBotao() {
        dsl.clicarBotao("buttonSimple");
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void testarLinks() {
        dsl.clicarLink("Voltar");
        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
    }

    @Test
    public void buscarTexto() {
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
    }

}
