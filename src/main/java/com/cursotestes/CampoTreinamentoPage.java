package com.cursotestes;

import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
    private DSL dsl;

    public CampoTreinamentoPage(WebDriver driver) {
        dsl = new DSL(driver);
    }

    public void setNome(String texto) {
        dsl.preencherCampo("elementosForm:nome", texto);
    }

    public void setSobrenome(String texto) {
        dsl.preencherCampo("elementosForm:sobrenome", texto);
    }

    public void setSexoFeminino() {
        dsl.clicarRadio("elementosForm:sexo:1");
    }

    public void setSexoMasculino() {
        dsl.clicarRadio("elementosForm:sexo:0");
    }

    public void setComidaPizza() {
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
    }

    public void setComidaCarne() {
        dsl.clicarRadio("elementosForm:comidaFavorita:0");
    }

    public void setComidaFrango() {
        dsl.clicarRadio("elementosForm:comidaFavorita:1");
    }

    public void setComidaVegetariano() {
        dsl.clicarRadio("elementosForm:comidaFavorita:3");
    }

    public void setEscolaridade(String texto) {
        dsl.selecionarDropdown("elementosForm:escolaridade", texto);
    }

    public void setEsporte(String... valores) {
        for(String valor: valores){
            dsl.selecionarDropdown("elementosForm:esportes", valor);
        }
    }

    public void cadastrar() {
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    public String obterResultadoCadastro() {
        return dsl.obterTexto("resultado");
    }

    public String obterNomeCadastro() {
        return dsl.obterTexto("descNome");
    }

    public String obterSobrenomeCadastro() {
        return dsl.obterTexto("descSobrenome");
    }

    public String obterSexoCadastro() {
        return dsl.obterTexto("descSexo");
    }

    public String obterComidaCadastro() {
        return dsl.obterTexto("descComida");
    }

    public String obterEscolaridadeCadastro() {
        return dsl.obterTexto("descEscolaridade");
    }

    public String obterEsporteCadastro() {
        return dsl.obterTexto("descEsportes");
    }
}
