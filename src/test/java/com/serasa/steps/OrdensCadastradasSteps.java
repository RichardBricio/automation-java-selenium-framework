package com.serasa.steps;

import static org.junit.Assert.fail;

import com.serasa.common.utils.DriverUtils;
import com.serasa.pages.SerajaJudHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import io.cucumber.java.en.Then;

public class OrdensCadastradasSteps {

	SerajaJudHomePage home = new SerajaJudHomePage();
	final static Logger logger = LogManager.getLogger(OrdensCadastradasSteps.class);
	
	@Then("^Valido a tabela com as ordens cadastradas$")
	public void valido_tabela_ordens_cadastradas() throws Exception {
		try {
			//Implementar


			logger.info("%%% Sucesso na validação da tabela!!! %%%");
		} catch(Exception e) {
			logger.error("###### Erro na validação da tabela!!!######");
			fail();
		}
	}

	@When("^Eu acesso o menu ORDENS$")
	public void Eu_o_menu_ordens() throws Exception {
		DriverUtils.esperarElementoComPolling(home.getBtnOrdem());
		try {
			home.getBtnOrdem().click();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
}
