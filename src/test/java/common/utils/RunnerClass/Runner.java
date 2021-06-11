package common.utils.RunnerClass;

import java.io.IOException;

import common.report.ReportEsteira;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;

import hooks.Hooks;

//Runner para esteira DevOps (Execução dos testes regressios) Tag @Regression

public class Runner{
	private static final Logger logger = LogManager.getLogger(Runner.class);
	
	@Test
	public void main() throws IOException {
		logger.info("----- EXECUÇÃO DE TESTES INICIADA ATRAVÉS DA ESTEIRA DEVOPS - RUNNER - JUNIT -----");
		ReportEsteira.runTestBasesOnTags("@Regression");
	}
	
	@AfterClass
	public static void tearDown() throws IOException, InterruptedException {
		Hooks.tearDown();
	}
}