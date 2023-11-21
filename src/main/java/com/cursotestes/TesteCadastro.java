package com.cursotestes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCadastro {
    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void initializeWebDriver() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().setPosition(new Point(1800, 20));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    @After
    public void shutWebDriver() {
        driver.quit();
    }
    
    @Test
    public void fazerCadastro() {
        page.setNome("Julia");
        page.setSobrenome("Barros");
        page.setSexoFeminino();
        page.setComidaPizza();
        page.setEscolaridade("Superior");
        page.setEsporte("Corrida");
        page.cadastrar();

        Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
        Assert.assertTrue(page.obterNomeCadastro().endsWith("Julia"));
        Assert.assertEquals("Sobrenome: Barros", page.obterSobrenomeCadastro());
        Assert.assertEquals("Sexo: Feminino", page.obterSexoCadastro());
        Assert.assertEquals("Comida: Pizza", page.obterComidaCadastro());
        Assert.assertEquals("Escolaridade: superior", page.obterEscolaridadeCadastro());
        Assert.assertEquals("Esportes: Corrida", page.obterEsporteCadastro());
    }

    @Test
    public void deveValidarNomeObrigatorio() {
        page.cadastrar();
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        page.setNome("ABC DEF");
        page.cadastrar();
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        page.setNome("ABC DEF");
        page.setSobrenome("GHI");
        page.cadastrar();
        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarComidaVegetariana() {
        page.setNome("ABC DEF");
        page.setSobrenome("GHI");
        page.setSexoFeminino();
        page.setComidaCarne();
        page.setComidaVegetariano();
        page.cadastrar();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarEsporteIndeciso() {
        page.setNome("ABC DEF");
        page.setSobrenome("GHI");
        page.setSexoFeminino();
        page.setComidaCarne();
        page.setEsporte("Corrida", "O que eh esporte?");
        page.cadastrar();
        Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
    }

}
