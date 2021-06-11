package steps.common;

import common.utils.DriverUtils;
import properties.GetResourceBundle;
import io.cucumber.java.en.Given;
import java.awt.*;
import java.awt.event.KeyEvent;

public class LoginSteps {

	GetResourceBundle props = new GetResourceBundle();
	
	boolean local = false;
	public static String environment = ""; 
		
	@Given("^Que eu acesso a pagina de login no ambiente de (HI|DEV|EXT|PROD|LOCAL)$")
	public void Eu_acesso_o_GamersClub(String env) throws Exception {
		switch (env) {
		case "HI":
			DriverUtils.navegar("https://beta.gamersclub.com.br/jogador/932811");
			break;
		case "EXT":
			DriverUtils.navegar("EXT");
			break;
		case "DEV":
			DriverUtils.navegar("DEV");
			break;
		case "PROD":
			DriverUtils.navegar("PROD");
			break;
		case "LOCAL":
			DriverUtils.navegar("http://localhost:8080/");
			local = true;
			break;
		default:
			DriverUtils.navegar("https://beta.gamersclub.com.br/jogador/932811");
			break;
		}
		environment = env;
		
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
