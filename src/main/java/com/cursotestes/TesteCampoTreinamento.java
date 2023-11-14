package com.cursotestes;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {
    @Test
    public void testarTextField() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        // Campos de texto
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
        Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void testarTextArea() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste");
        Assert.assertEquals("teste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void testarRadioButton() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

        driver.quit();
    }

    @Test
    public void testarCheckBox() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:3")).isSelected());

        driver.quit();
    }

    @Test
    public void testarDropdown() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        WebElement elementEscolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
        Select dropdownSelect = new Select(elementEscolaridade);
        //dropdownSelect.selectByIndex(3);
        //dropdownSelect.selectByValue("superior");
        dropdownSelect.selectByVisibleText("2o grau completo");

        Assert.assertEquals("2o grau completo", dropdownSelect.getFirstSelectedOption().getText());
        driver.quit();
    }

    @Test
    public void verificarValorDropdown() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        WebElement elementEscolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
        Select dropdownSelect = new Select(elementEscolaridade);
        List<WebElement> options = dropdownSelect.getOptions();

        Assert.assertEquals(8, options.size());

        boolean encontrou = false;
        for (WebElement option : options) {
            if(option.getText().equals("Mestrado")) {
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);

        driver.quit();
    }

    @Test
    public void selecionarMultiplosDropdown() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        WebElement elementEsportes = driver.findElement(By.id("elementosForm:esportes"));
        Select dropdownSelect = new Select(elementEsportes);
        dropdownSelect.selectByVisibleText("O que eh esporte?");
        dropdownSelect.selectByVisibleText("Karate");

        List<WebElement> selectedOptions = dropdownSelect.getAllSelectedOptions();
        Assert.assertEquals(2, selectedOptions.size());

        dropdownSelect.deselectByVisibleText("O que eh esporte?");
        dropdownSelect.deselectByVisibleText("Karate");
        selectedOptions = dropdownSelect.getAllSelectedOptions();
        Assert.assertEquals(0, selectedOptions.size());

        driver.quit();
    }

    @Test
    public void testarBotao() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.manage().window().setPosition(new Point(1800, 20));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();

        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
        driver.quit();
    }

    @Test
    @Ignore
    public void testarLinks() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().setPosition(new Point(1800, 20));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.linkText("Voltar")).click();
        // Não deixar testes incompletos passarem. Esse ainda não faz nada, então:
        // Assert.fail();
        // ou anotar com o @Ignore
        //driver.quit();
    }

}
