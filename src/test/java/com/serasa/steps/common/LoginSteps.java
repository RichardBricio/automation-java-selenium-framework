package com.serasa.steps.common;

import com.serasa.common.utils.DriverUtils;
import com.serasa.properties.GetResourceBundle;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LoginSteps {

	GetResourceBundle props = new GetResourceBundle();
	final static Logger logger = LogManager.getLogger(LoginSteps.class.getName());
	
	boolean local = false;
	public static String environment = ""; 
		
	@Given("^Que eu acesso a pagina de login no ambiente de (HI|DEV|EXT|PROD|LOCAL)$")
	public void Eu_acesso_o_SerasaJud(String env) throws Exception {
		switch (env) {
		case "HI":
			DriverUtils.navegar("https://experian-serasajud-front-br-serasajud-qa.f.apps.experian.com/login");
			logger.info("Ambiente selecionado: HOMOLOGAÇÃO INTERNA");
			break;
		case "EXT":
			DriverUtils.navegar("https://experian-serasajud-front-br-serasajud-qa-ext.f.apps.experian.com/login");
			logger.info("Ambiente selecionado: HOMOLOGAÇÃO EXTERNA");
			break;
		case "DEV":
			DriverUtils.navegar("DEV");
			logger.info("Ambiente selecionado: DESENVOLVIMENTO");
			break;
		case "PROD":
			DriverUtils.navegar("PROD");
			logger.info("Ambiente selecionado: PRODUÇÃO");
			break;
		case "LOCAL":
			DriverUtils.navegar("http://localhost:8080/");
			logger.info("Ambiente selecionado: LOCAL");
			local = true;
			break;
		default:
			DriverUtils.navegar("https://experian-serasajud-front-br-serasajud-qa-ext.f.apps.experian.com/login");
			logger.info("Ambiente selecionado: DEFAULT");
			break;
		}
		environment = env;
		
	}

	@And("^Eu seleciono o primeiro CERTIFICADO_DIGITAL e insiro a senha \"([^\"]*)\"$")
	public void Eu_seleciono_o_primeiro_certificado_digital_e_insiro_senha(String env) throws Exception {
		Thread.sleep(5000);
		Robot robo = new Robot();
		robo.keyPress(KeyEvent.VK_ENTER);

		Thread.sleep(3000);
		robo.keyPress(KeyEvent.VK_ALT);
		robo.keyPress(KeyEvent.VK_SHIFT);
		robo.keyPress(KeyEvent.VK_TAB);
		robo.keyRelease(KeyEvent.VK_ALT);
		robo.keyRelease(KeyEvent.VK_SHIFT);
		robo.keyRelease(KeyEvent.VK_TAB);

		Thread.sleep(3000);
		writeStringRobot(env);
//		robo.keyPress(KeyEvent.VK_S);
//		robo.keyPress(KeyEvent.VK_E);
//		robo.keyPress(KeyEvent.VK_R);
//		robo.keyPress(KeyEvent.VK_A);
//		robo.keyPress(KeyEvent.VK_S);
//		robo.keyPress(KeyEvent.VK_A);
//		robo.keyPress(KeyEvent.VK_SHIFT);
//		robo.keyPress(KeyEvent.VK_2);
//		robo.keyRelease(KeyEvent.VK_SHIFT);
//		robo.keyPress(KeyEvent.VK_1);

		robo.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
	}

	private void writeStringRobot(String s) throws Exception {
		Robot robot = new Robot();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isUpperCase(c)) {
				robot.keyPress(KeyEvent.VK_SHIFT);
			}

			if (c == '@'){
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else {
				robot.keyPress(Character.toUpperCase(c));
				robot.keyRelease(Character.toUpperCase(c));
			}

			if (Character.isUpperCase(c)) {
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
		}
		//robot.delay(1000);
	}

}
