package com.serasa.steps.common;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.serasa.common.drivers.DriverType;
import com.serasa.common.utils.DriverUtils;
import com.serasa.common.utils.TableUtils;
import com.serasa.pages.AbstractPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CommonSteps {

	private static final Logger logger = LogManager.getLogger(CommonSteps.class);
	public static String brw = "";
	
	static WebDriverWait wait;

	@Given("^Que eu esteja usando o browser (CHROME|IE|DOCKER_CHROME)$")
	public void selectDriver(String browser) throws Throwable {
		logger.info("------ INICIANDO BATERIA DE TESTES - SERASAJUD ----------");
		switch (browser) {
		case "CHROME":
			DriverUtils.selecionaBrowser(DriverType.CHROME);
			logger.info("Selecionado o driver: " + DriverType.CHROME);
			break;
		case "IE":
			DriverUtils.selecionaBrowser(DriverType.IE);
			logger.info("Selecionado o driver: " + DriverType.IE);
			break;
		case "DOCKER_CHROME":
			DriverUtils.selecionaBrowser(DriverType.DOCKER_CHROME);
			logger.info("Selecionado o driver: " + DriverType.DOCKER_CHROME);
			break;
		default:
			DriverUtils.selecionaBrowser(DriverType.CHROME);
			logger.info("Selecionado o driver: " + DriverType.CHROME);
			break;
		}
		brw = browser;
	}

	@Then("^Printo a tela$")
	public void tirarScreenShot() throws IOException {
		DriverUtils.tirarScreenShot();
	}

	@Then("^Visualizo o bloco \"([^\"]*)\"$")
	public void visualizeTable(String featureName) throws Throwable {
		final Logger logger = LogManager.getLogger(featureName);
		try {
			AbstractPage page = (AbstractPage) TableUtils.retornaClasse(featureName);
			Assert.assertTrue(page.getTitle().isDisplayed());
			logger.info("%%% Bloco " + featureName + " encontrado com sucesso!!! %%%");
			DriverUtils.scrollIntoMiddleView(page.getTable());
		} catch (Exception e) {
			logger.error("### Bloco " + featureName + " NÃO encontrado!!! ###");
			fail();
		}
		DriverUtils.tirarScreenShot();
	}
	
}
