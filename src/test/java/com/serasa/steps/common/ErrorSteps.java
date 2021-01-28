package com.serasa.steps.common;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.serasa.common.exception.ConnectionErrorException;
import com.serasa.common.exception.DesbloqueioDeSenhaException;
import com.serasa.common.exception.ErroNoMainframeException;
import com.serasa.common.exception.SomethingWrongException;
import com.serasa.common.exception.WithoutAccessException;
import com.serasa.common.utils.DriverUtils;

public class ErrorSteps {

	final static Logger logger = LogManager.getLogger(ErrorSteps.class);

	public static boolean checkIfSomethingWentWrongIsDisplayed() throws IOException {
		boolean error = false;
		try {
			if (DriverUtils.getDriver().getPageSource().contains("Something went wrong")) {
				DriverUtils.tirarScreenShot();
				logger.error(new SomethingWrongException());
				error = true;
			} 
		} catch (NullPointerException e1) {
			logger.info("Nenhum erro encontrado!");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return error;
	}

	public static boolean checkIfErroNoMainframeIsDisplayed() throws IOException {
		boolean error = false;

		try {
			if (DriverUtils.getDriver().getPageSource().contains("Erro no retorno mainFrame")) {
				DriverUtils.tirarScreenShot();
				logger.error(new ErroNoMainframeException());
				error = true;
			}
		} catch (NullPointerException e1) {
			logger.info("Nenhum erro encontrado!");
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		

		return error;
	}

	public static boolean checkIfUserInputInvalidData() throws IOException {
		boolean error = false;
		try {
			if (DriverUtils.getDriver().getPageSource().contains("Usuário e/ou senha inválido")) {
				error = true;
				DriverUtils.tirarScreenShot();
				fail();
			}
		} catch (NullPointerException e1) {
			logger.info("Nenhum erro encontrado!");
		} catch (AssertionError e2) {
			throw new WithoutAccessException();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return error;
	}
	
	public static boolean checkIfUserHasConnectionProblem() throws IOException {
		boolean error = false;
		try {
			if (DriverUtils.getDriver().getPageSource().contains("Ocorreu um erro na conexão")) {
				error = true;
				DriverUtils.tirarScreenShot();
				fail();
			}
		} catch (NullPointerException e1) {
			logger.info("Nenhum erro encontrado!");
		} catch (AssertionError e2) {
			throw new ConnectionErrorException("Connections problem! Environment out.");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return error;
	}
	
	public static boolean checkIfInvalidAccess() throws IOException {
		boolean error = false;
		try {
			if (DriverUtils.getDriver().getPageSource().contains("Acesso inválido")) {
				error = true;
				DriverUtils.tirarScreenShot();
				fail();
			}
		} catch (NullPointerException e1) {
			logger.info("Nenhum erro encontrado!");
		} catch (AssertionError e2) {
			throw new ConnectionErrorException("Invalid acess!");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return error;
	}
	
	public static boolean desbloqueioComALteracaoDeSenhaError() {
		boolean error = false;
		try {
			if (DriverUtils.getDriver().getPageSource().contains("Desbloqueio com Altera")) {
				DriverUtils.tirarScreenShot();
				logger.error(new DesbloqueioDeSenhaException());
				error = true;
			} else {
				Set<String> handles = DriverUtils.getDriver().getWindowHandles();
				String currentHandle = DriverUtils.getDriver().getWindowHandle();
				for (String handle : handles) {
					if (!handle.equals(currentHandle)) {
						DriverUtils.getDriver().switchTo().window(handle);
						if (DriverUtils.getDriver().getPageSource().contains("Desbloqueio com Altera")) {
							DriverUtils.tirarScreenShot();
							logger.error(new DesbloqueioDeSenhaException());
							error = true;
						}
					}
				}
			}
		} catch (NullPointerException e1) {
			logger.info("Nenhum erro encontrado!");
		}  catch (Exception e) {
			logger.error(e.getMessage());
		}
		return error;
	}
	
	public static boolean breakError() throws IOException, InterruptedException {
		
		if (checkIfUserInputInvalidData() || checkIfInvalidAccess() ||  
			checkIfUserHasConnectionProblem() || checkIfSomethingWentWrongIsDisplayed() || 
			checkIfErroNoMainframeIsDisplayed() || desbloqueioComALteracaoDeSenhaError()) {
			
			return true;
		}
		return false;
		
	}
	
}