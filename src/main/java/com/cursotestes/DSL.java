package com.cursotestes;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
    private WebDriver driver;

    public DSL(WebDriver driver) {

        this.driver = driver;
    }

    public void preencherCampo(String id_campo, String texto) {

        driver.findElement(By.id(id_campo)).sendKeys(texto);
    }

    public String obterValorCampo(String id_campo) {

        return driver.findElement(By.id(id_campo)).getAttribute("value");
    }

    public void clicarRadio(String id_campo) {
        driver.findElement(By.id(id_campo)).click();
    }

    public boolean isRadioMarcado(String id_radio) {
        return driver.findElement(By.id(id_radio)).isSelected();
    }

    public void selecionarDropdown(String id, String valor) {
        WebElement element = driver.findElement(By.id(id));
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(valor);
    }

    public String obterValorDropdown(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select dropdown = new Select(element);
        return dropdown.getFirstSelectedOption().getText();
    }

    public void clicarBotao(String id_botao) {
        driver.findElement(By.id(id_botao)).click();
    }

    public void clicarLink(String texto) {
        driver.findElement(By.linkText(texto)).click();
    }

    public String obterTexto(By by) {

        return driver.findElement(by).getText();
    }

    public String obterTexto(String id) {
        return obterTexto(By.id(id));
    }

    public String alertaObterTextoEAceita(){
        Alert alert = driver.switchTo().alert();
        String valor = alert.getText();
        alert.accept();
        return valor;
    }

}
