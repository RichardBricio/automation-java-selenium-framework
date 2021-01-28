package com.serasa.common.utils.RunnerClass;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;

import com.serasa.common.report.ReportEsteira;
import com.serasa.hooks.Hooks;

//Runner para Execução Local colocar a tag desejada para execução

public class LocalRun{
	
	private static final Logger logger = LogManager.getLogger(LocalRun.class);

	@Test
	public void main() throws IOException {
		logger.info("----- EXECUÇÃO DE TESTES INICIADA MANUALMENTE ATRAVÉS DO LOCALRUN - JUNIT -----");

		ReportEsteira.runTestBasesOnTags("@test");
		
	}
	
	@AfterClass
	public static void tearDown() throws IOException, InterruptedException {
		Hooks.tearDown();
	}
}