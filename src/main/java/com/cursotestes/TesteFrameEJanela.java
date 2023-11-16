package com.cursotestes;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFrameEJanela {

    @Test
    public void testarFrames() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().setPosition(new Point(1800, 20));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alert = driver.switchTo().alert();

        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);
        alert.accept();
        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).click();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);

        driver.quit();
    }

    @Test
    public void testarJanela() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().setPosition(new Point(1800, 20));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        String janelaOriginal = driver.getWindowHandle();

        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.close();

        driver.switchTo().window(janelaOriginal);
        driver.findElement(By.tagName("textarea")).sendKeys("Oi, mundo");

        driver.quit();
    }

    @Test
    public void testarJanelaSemTitulo() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().setPosition(new Point(1800, 20));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("buttonPopUpHard")).click();
        // System.out.println(driver.getWindowHandle()); = qual a janela corrente; id da janela
        // System.out.println(driver.getWindowHandles()); = todas as janelas que estão sendo gerenciadas

        String janelaPopUpHard = (String) driver.getWindowHandles().toArray()[1];
        driver.switchTo().window(janelaPopUpHard);
        driver.findElement(By.tagName("textarea")).click();
        driver.findElement(By.tagName("textarea")).sendKeys("Oi de novo");

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).click();
        driver.findElement(By.tagName("textarea")).sendKeys("Agora vai");

        driver.quit();
    }
}
