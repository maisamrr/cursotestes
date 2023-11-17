package com.cursotestes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DesafioCadastro {
    private WebDriver driver;

    @Before
    public void initializeWebDriver() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().setPosition(new Point(1800, 20));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    }

    @After
    public void shutWebDriver() {
        driver.quit();
    }

    @Test
    public void testarCampoNome() {
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
    }

    @Test
    public void testarCampoSobrenome() {
        driver.findElement(By.id("elementosForm:nome")).click();
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Joana");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    }

    @Test
    public void testarCampoSexo() {
        driver.findElement(By.id("elementosForm:nome")).click();
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Joana");
        driver.findElement(By.id("elementosForm:sobrenome")).click();
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Lopes");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
    }

    @Test
    public void testarCampoCarneVeg() {
        driver.findElement(By.id("elementosForm:nome")).click();
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Joana");
        driver.findElement(By.id("elementosForm:sobrenome")).click();
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Lopes");
        driver.findElement(By.id("elementosForm:sexo:1")).click();

        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }

    @Test
    public void testarCampoFrangoVeg() {
        driver.findElement(By.id("elementosForm:nome")).click();
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Joana");
        driver.findElement(By.id("elementosForm:sobrenome")).click();
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Lopes");
        driver.findElement(By.id("elementosForm:sexo:1")).click();

        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }

    @Test
    public void testarCampoEsporte() {
        driver.findElement(By.id("elementosForm:nome")).click();
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Joana");
        driver.findElement(By.id("elementosForm:sobrenome")).click();
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Lopes");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        WebElement allSports = driver.findElement(By.id("elementosForm:esportes"));
        Select dropdownSelect = new Select(allSports);
        dropdownSelect.selectByVisibleText("Karate");
        dropdownSelect.selectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
    }
}
