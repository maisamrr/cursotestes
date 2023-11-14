package com.cursotestes;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CadastroCompleto {
    private WebDriver driver;

    private WebDriver initializeWebDriver() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().setPosition(new Point(1800, 20));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        return driver;
    }

    private void fillField(WebDriver driver, String id, String text) {
        WebElement field = driver.findElement(By.id(id));
        field.click();
        field.sendKeys(text);
        Assert.assertEquals(text, field.getAttribute("value"));
    }

    private void checkOption(WebDriver driver, String id) {
        driver.findElement(By.id(id)).click();
        Assert.assertTrue(driver.findElement(By.id(id)).isSelected());
    }

    private void selectMultipleOptions(WebDriver driver, String id, String text) {
        WebElement dropdownOptions = driver.findElement(By.id(id));
        Select selectDropdown = new Select(dropdownOptions);
        selectDropdown.selectByVisibleText(text);
        Assert.assertEquals(text, selectDropdown.getFirstSelectedOption().getText());
    }

    @Test
    public void cadastrar() {
        WebDriver myDriver = initializeWebDriver();

        fillField(myDriver, "elementosForm:nome", "Maisa");
        fillField(myDriver, "elementosForm:sobrenome", "Moreira");
        checkOption(myDriver, "elementosForm:sexo:1");
        checkOption(myDriver, "elementosForm:comidaFavorita:2");
        selectMultipleOptions(myDriver, "elementosForm:escolaridade", "Superior");
        selectMultipleOptions(myDriver, "elementosForm:esportes", "Corrida");

        myDriver.findElement(By.id("elementosForm:cadastrar")).click();
        String resultadoCadastro = myDriver.findElement(By.id("resultado")).getText();
        Assert.assertTrue(resultadoCadastro.startsWith("Cadastrado!"));

        myDriver.quit();
    }

}
