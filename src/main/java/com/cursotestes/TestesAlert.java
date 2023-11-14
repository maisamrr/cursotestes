package com.cursotestes;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestesAlert {
    @Test
    public void testarAlertSimples() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().setPosition(new Point(1800, 20));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("alert")).click();
        // Conseguir mudar o foco para o alert e interagir com ele
        Alert alert = driver.switchTo().alert();
        String texto = alert.getText();
        Assert.assertEquals("Alert Simples", alert.getText());
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
        driver.quit();
    }
}
