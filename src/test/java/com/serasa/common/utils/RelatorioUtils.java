package com.serasa.common.utils;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class RelatorioUtils {
	
	final static Logger logger = LogManager.getLogger(RelatorioUtils.class);
	
	//Formatar nome da feature
	public static String tituloBloco(WebElement elemento) {
		
		String titulo = null;
		
		try {
			DriverUtils.WaitUntilWebElementIsVisible(elemento);
			titulo = elemento.getText();
			titulo = titulo.replace("?", "");
			titulo = titulo.trim();
			logger.info("Texto formatado com sucesso: " + titulo);
		} catch (Exception e) {
			logger.error("Não foi possível encontrar o elemento: " + e);
			fail();
		}
		return titulo;
	}
	
	//Formatar nome da feature com data de atualização 
	public static String tituloBlocoComData(WebElement elemento) {
		
		String titulo = null;
		
		try {
			DriverUtils.WaitUntilWebElementIsVisible(elemento);
			titulo = elemento.getText();
			titulo = titulo.replace("?", "");
			titulo = titulo.trim();
			String[] tits = titulo.split("[(]");
			titulo = tits[0].trim();
			logger.info("Texto formatado com sucesso: " + titulo);
		} catch (Exception e) {
			logger.error("Não foi possível encontrar o elemento: " + e);
			fail();
		}
		return titulo;
	}
	
	public static String formataString(WebElement elemento) {
		
		String txt = null;
		try {
			DriverUtils.esperarElementoComPolling(elemento);
			txt = elemento.getText().trim();
			txt = txt.replace("\n", " ");
			txt = txt.toLowerCase().replaceAll("[ ()0-9]", "");
			logger.info("Texto formatado com sucesso: " + txt);
		} catch (Exception e) {
			logger.error("Não foi possível encontrar o elemento: " + e);
			fail();
		}
		return txt;
	}
	
	public static String[] dataImageScore(String img) {
		String[] txtImg = null;
		try {
			txtImg = img.split("\\.");
			logger.info("Data da imagem formatada com sucesso: " + txtImg[0] + " " + txtImg[1] + " " + txtImg[2]);
		} catch (Exception e) {
			logger.error("Não foi possível formatar os dados da imagem: " + e);
			fail();
		}
		return txtImg;
	}
	
	public static String obterCodSegmento(String segmento) {
		String segCod = null;
		String seg = segmento.toUpperCase().trim();
		
		Map<String,String> segmentos = new HashMap<String,String>();
		
		segmentos.put("MERCADO", new String("000"));
		segmentos.put("CREDINFOR", new String("005"));
		segmentos.put("ABAFARMA", new String("011"));
		segmentos.put("ATACADISTAS", new String("017"));
		segmentos.put("FACTORINGS", new String("028"));
		segmentos.put("ASPACER", new String("019"));
		segmentos.put("TEXTIL", new String("002"));
		segmentos.put("CEDENTE", new String("CEDENTE"));
		
		if (segmentos.containsKey(seg)) {
			segCod = segmentos.get(seg);
			 logger.info("Codigo segmento encontrado, chave: "+ seg +  " = " + segmentos.get(seg));
            }else{
            	logger.error("Chave não existe: " + seg);
            }
		return segCod;
	}
	
	public static String MapEstados(String estado) {
		
		String sigla = null;
		
		Map<String,String> estados = new HashMap<String,String>();
		
		estados.put("Acre" , new String("ac"));
		estados.put("Alagoas" , new String("al"));
		estados.put("Amapá" , new String("ap"));
		estados.put("Amazonas" , new String("am"));
		estados.put("Bahia" , new String("ba"));
		estados.put("Ceará" , new String("ce"));
		estados.put("Distrito Federal" , new String("df"));
		estados.put("Espírito Santo" , new String("es"));
		estados.put("Goiás" , new String("go"));
		estados.put("Maranhão" , new String("ma"));
		estados.put("Mato Grosso" , new String("mt"));
		estados.put("Mato Grosso do Sul" , new String("ms"));
		estados.put("Minas Gerais" , new String("mg"));
		estados.put("Pará" , new String("pa"));
		estados.put("Paraíba" , new String("pb"));
		estados.put("Paraná" , new String("pr"));
		estados.put("Pernambuco" , new String("pe"));
		estados.put("Piauí" , new String("pi"));
		estados.put("Rio de Janeiro" , new String("rj"));
		estados.put("Rio Grande do Norte" , new String("rn"));
		estados.put("Rio Grande do Sul" , new String("rs"));
		estados.put("Rondônia" , new String("ro"));
		estados.put("Roraima" , new String("rr"));
		estados.put("Santa Catarina" , new String("sc"));
		estados.put("São Paulo" , new String("sp"));
		estados.put("Sergipe" , new String("se"));
		estados.put("Tocantins" , new String("to"));
		
		 if ( estados.containsKey(estado)) {
			 sigla = estados.get(estado);
			 logger.info("Chave encontrada, chave: "+ estado +  " = " + estados.get(estado));
            }else{
            	logger.error("Chave não existe");
            }
		return sigla;
	}
	
	public static void consultarCNJ(WebElement elemSendKey, String cnpj) throws Exception {
//		try {
//			DriverUtils.WaitUntilWebElementIsVisible(pageMenuFeatures.bntConsultar());
//		    DriverUtils.sendKeysToWebElement(elemSendKey, cnpj);
//			DriverUtils.tirarScreenShot();
//			logger.info("Documento consultado com sucesso");
//		} catch (Exception e) {
//			logger.error("Erro na consulta do documento" + e.getMessage());
//		}
	}
}
