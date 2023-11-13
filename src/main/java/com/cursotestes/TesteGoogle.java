package com.cursotestes;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {

    @Test
    public void teste() {
        System.setProperty("webdriver.gecko.driver", "/Users/maisamoreira/Desktop/CNJ/SNA_TESTES/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("http://www.google.com");
        Assert.assertEquals("Google", driver.getTitle());
        driver.quit();
    }
}
