package com.serasa.common.utils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TableUtils {

	static List<String> lista;
	final static Logger logger = LogManager.getLogger(TableUtils.class);

	public static WebElement retornaAsTabelas(String tabela) throws Exception {
		List<WebElement> allTables = DriverUtils.getDriver().findElements(By.xpath("//table"));
		lista = new ArrayList<>();
		int aux = 0;
		String formatarTitulo = null;
		for (int i = 0; i < allTables.size(); i++) {
			try {
				aux = i + 1;
				formatarTitulo = DriverUtils.getDriver()
						.findElement(By.xpath("//table[" + aux + "]/tbody/tr[1]/th/div")).getText();
				formatarTitulo = formatarTitulo.replace("?", "");
				formatarTitulo = formatarTitulo.trim();
				lista.add(formatarTitulo);
				if (formatarTitulo.equals(tabela)) {
					DriverUtils.scrollIntoView(DriverUtils.getDriver().findElement(By.xpath("//table[" + aux + "]")));
					DriverUtils.tirarScreenShot();
					return DriverUtils.getDriver().findElement(By.xpath("//table[" + aux + "]"));
				}
			} catch (NoSuchElementException e) {
				try {
					aux = i + 1;
					formatarTitulo = DriverUtils.getDriver()
							.findElement(By.xpath("//table[" + aux + "]/tbody/tr[1]/td/div")).getText();
					formatarTitulo = formatarTitulo.replace("?", "");
					formatarTitulo = formatarTitulo.trim();
					lista.add(formatarTitulo);
					if (formatarTitulo.equals(tabela)) {
						DriverUtils
								.scrollIntoView(DriverUtils.getDriver().findElement(By.xpath("//table[" + aux + "]")));
						DriverUtils.tirarScreenShot();
						return DriverUtils.getDriver().findElement(By.xpath("//table[" + aux + "]"));
					}
				} catch (NoSuchElementException e2) {
					aux = i + 1;
					formatarTitulo = DriverUtils.getDriver()
							.findElement(By.xpath("//table[" + aux + "]/tbody/tr[1]/td[1]")).getText();
					formatarTitulo = formatarTitulo.replace("?", "");
					formatarTitulo = formatarTitulo.trim();
					lista.add(formatarTitulo);
					if (formatarTitulo.equals(tabela)) {
						DriverUtils
								.scrollIntoView(DriverUtils.getDriver().findElement(By.xpath("//table[" + aux + "]")));
						DriverUtils.tirarScreenShot();
						return DriverUtils.getDriver().findElement(By.xpath("//table[" + aux + "]"));
					}
				}

			}
		}
		return null;
	}

	/**
	 * Valida a imagem contida em uma tabela.
	 * 
	 * @param table:
	 *            Tabela que vai realizar a busca. Deve ser do tipo WebElement
	 * @param linha:
	 *            a linha onde está a imagem. Linha 1 corresponde ao cabeçalho da
	 *            tabela (título).
	 * @param coluna:
	 *            a coluna onde está a imagem. Começando na coluna 1.
	 * @param conteudo:
	 *            conteúdo da imagem, podendo ser um pequeno segmento de texto.
	 */

	public static void validaImagemTabela(WebElement table, int linha, int coluna, String conteudo) {
		try {
			List<WebElement> trs = table.findElements(By.tagName("tr"));
			List<WebElement> tds = trs.get(linha - 1).findElements(By.tagName("td"));
			assertTrue(tds.get(coluna - 1).findElement(By.tagName("img")).getAttribute("src").contains(conteudo));
			logger.info("Imagem " + conteudo + " encontrada na linha: " + linha + "; e coluna: " + coluna);
		} catch (NoSuchElementException e) {
			logger.error("Não foi possível encontrar a imagem " + conteudo + " na linha: " + linha + "; e coluna: "
					+ coluna);
			fail();
		}
	}

	/**
	 * Valida o texto contido em uma tabela.
	 * 
	 * @param table:
	 *            Tabela que vai realizar a busca. Deve ser do tipo WebElement
	 * @param linha:
	 *            a linha onde está a imagem. Linha 1 corresponde ao cabeçalho da
	 *            tabela (título).
	 * @param coluna:
	 *            a coluna onde está a imagem. Começando na coluna 1.
	 * @param conteudo:
	 *            conteúdo do texto.
	 */

	public static void validaTextoTabela(WebElement table, int linha, int coluna, String conteudo) {
		try {
			List<WebElement> trs = table.findElements(By.tagName("tr"));
			List<WebElement> tds = trs.get(linha - 1).findElements(By.tagName("td"));
			String td = tds.get(coluna - 1).getText();
			Assert.assertEquals(conteudo, td);
			logger.info("Conteúdo Encontrado na linha: " + linha + "; e coluna: " + coluna + ": |" + conteudo + "|");
		} catch (AssertionError e) {
			List<WebElement> trs = table.findElements(By.tagName("tr"));
			List<WebElement> tds = trs.get(linha - 1).findElements(By.tagName("td"));
			String td = tds.get(coluna - 1).getText();
			logger.error("########### Não foi possível encontrar o conteúdo: |" + conteudo + "| na linha: " + linha
					+ "; e coluna: " + coluna + " ###########");
			logger.error("########### Conteudo exibido: |" + td + "| na linha: " + linha + "; e coluna: " + coluna
					+ " ###########");
			fail();
		}
	}

	/**
	 * Valida o texto contido em uma tr. Criado metodo para validação do histórico
	 * de pagamento - valores em reais e evolucao compromissos visao a vencer
	 * 
	 * @param trs:
	 *            Lista de Webelements com trs
	 * @param linha:
	 *            Linha onde será procurado as tds e seus textos.
	 * @param meses:
	 *            Lista de Strings com os meses.
	 * @param notas:
	 *            Lista de Strings com as notas.
	 * @param valores:
	 *            Lista de Strings com os valores.
	 * @param percentuais:
	 *            Lista de Strings com os percentuais.
	 */

	// iniciado variaveis como static para continuar contando para várias chamadas
	// do metodo
	public static int n = 0;
	public static int v = 0;
	public static int p = 0;
	public static int m = 0;

	public static void validaTextoTr(List<WebElement> trs, int linha, String[] meses, String[] notas, String[] valores,
			String[] percentuais) {

		for (int j = 0; j < trs.get(linha).findElements(By.tagName("td")).size(); j++) {
			List<WebElement> tds = trs.get(linha).findElements(By.tagName("td"));
			String td = tds.get(j).getText();
			if (j == 0) {
				Assert.assertEquals(meses[m], td);
				System.out.println(meses[m]);
				logger.info("Mes encontrado na linha: " + linha + "; e coluna: " + j + ": |" + td + "|");
				m++;
			} else if ((j % 2) == 0) {
				if (j < 12) {
					if (!valores[v].equals("-")) {
						String[] valoress = td.split("\\n");
						Assert.assertEquals(valores[v], valoress[0]);
						Assert.assertEquals(percentuais[p], valoress[1]);
						System.out.println(valores[v]);
						System.out.println(percentuais[p]);
						logger.info(
								"Valor Encontrado na linha: " + linha + "; e coluna: " + j + ": |" + valoress[0] + "|");
						logger.info("Percentual Encontrado na linha: " + linha + "; e coluna: " + j + ": |"
								+ valoress[1] + "|");
						v++;
						p++;
					} else {
						Assert.assertEquals(valores[v], td);
						System.out.println(valores[v]);
						logger.info("Valor Encontrado na linha: " + linha + "; e coluna: " + j + ": |" + td + "|");
						v++;
					}
				} else {
					Assert.assertEquals(valores[v], td);
					System.out.println(valores[v]);
					logger.info("Valor Encontrado na linha: " + linha + "; e coluna: " + j + ": |" + td + "|");
					v++;
				}
			} else if ((j % 2) == 1) {
				Assert.assertEquals(notas[n], td);
				System.out.println(notas[n]);
				logger.info("Mes encontrado na linha: " + linha + "; e coluna: " + j + ": |" + td + "|");
				n++;
			} else {
				Assert.fail();
				logger.error("########### Erro. Conteúdo encontrado: |" + td + "| na linha: " + linha + "; e coluna: "
						+ j + " ###########");
			}
		}
	}

	/**
	 * Clica em um Span de uma tabela.
	 * 
	 * @param table:
	 *            Tabela que vai realizar a busca. Deve ser do tipo WebElement
	 * @param linha:
	 *            a linha onde está o span. Linha 1 corresponde ao cabeçalho da
	 *            tabela (título).
	 * @param coluna:
	 *            a coluna onde está o span. Começando na coluna 1.
	 */

	public static void clicaSpanTabela(WebElement table, int linha, int coluna) {
		List<WebElement> trs;
		List<WebElement> tds;
		try {
			trs = table.findElements(By.tagName("tr"));
			tds = trs.get(linha - 1).findElements(By.tagName("td"));
			try {
				WebElement span = tds.get(coluna - 1).findElement(By.tagName("span"));
				span.click();
			} catch (Exception e) {
				WebElement span = tds.get(coluna - 1).findElement(By.tagName("a"));
				span.click();
			}
			logger.info("Lupa Clicada na linha: " + linha + "; e coluna: " + coluna);
		} catch (AssertionError e) {
			logger.error("Span não encontrado na linha: " + linha + "; e coluna: " + coluna);
		}
	}

	/**
	 * Clica no help de Entrada
	 * 
	 * @param we:
	 *            Webelement que terá o texto do help contido
	 */

	public static void clicaHelpEntrada(WebElement we) {
		try {
			DriverUtils.scrollIntoView(we);
			we.click();
			logger.info("########### Clicou no Help ###########");
		} catch (AssertionError e) {
			logger.error("########### Não foi possível clicar no help ###########");
			fail();
		}
	}

	/**
	 * Valida texto do help de Entrada
	 * 
	 * @param we:
	 *            Webelement que terá o texto do help contido
	 * @param text:
	 *            texto que será comparado
	 */

	public static void validaHelpEntrada(WebElement we, String text) {
		String txt = we.getText();
		try {
			Assert.assertEquals(text, txt);
			logger.info("Conteúdo Encontrado |" + txt + "|");
		} catch (AssertionError e) {
			logger.error("########### Não foi possível encontrar o conteúdo: |" + text + "| ###########");
			logger.error("########### Conteudo exibido: |" + txt + "| ###########");
			Assert.fail();
		}
	}

	/**
	 * Valida o texto contido no help
	 * 
	 * @param we:
	 *            Webelement que terá o texto do help contido
	 * @param text:
	 *            texto que será comparado
	 * @throws IOException 
	 */
	public static void validaTooltip(WebElement we, String text) throws IOException {
		
		Actions act = new Actions(DriverUtils.getDriver());
		
		try {
			String toolTipText = we.getAttribute("onmouseover");
			toolTipText = toolTipText.replace("tooltip.show('", "");
			toolTipText = toolTipText.replace("');", "");
			toolTipText = toolTipText.trim();
			Assert.assertEquals(toolTipText, text);
			logger.info("Conteúdo Encontrado |" + text + "|");
			act.moveToElement(we).perform();
			DriverUtils.tirarScreenShot();
		} catch (AssertionError e) {
			String toolTipText = we.getAttribute("onmouseover");
			toolTipText = toolTipText.replace("tooltip.show('", "");
			toolTipText = toolTipText.replace("');", "");
			toolTipText = toolTipText.trim();
			logger.error("########### Não foi possível encontrar o conteúdo: |" + text + "| ###########");
			logger.error("########### Conteudo exibido: |" + toolTipText + "| ###########");
			Assert.fail();
		}
	}

	/**
	 * Conta quantas linhas a tabela tem, contando com o cabeçalho
	 * 
	 * @param table
	 *            Tabela que vai realizar a busca. Deve ser do tipo WebElement
	 * @return Retorna o número de linhas, contando com o cabeçalho em Int
	 */
	public static int RowCount(WebElement table) {
		return table.findElements(By.xpath("//tr")).size();
	}

	/**
	 * Calcula a quantidade de colunas da linha específica
	 * 
	 * @param table
	 *            A tabela em WebElement que será utilizada
	 * @param row
	 *            A linha que será pesquisada. Caso o valor seja 0 será analisado o
	 *            cabeçalho. Caso o contrário será análisado o corpo do texto.
	 *            Portanto não conte o cabeçalho na hora de validar!
	 * @return Retorna o numero de colunas da linha escolhida em Int
	 */
	public static int ColCount(WebElement table, int row) {
		int nRow = row == 0 ? 1 : row;
		return table.findElements(By.xpath(xpathHelper(row) + "/tr[" + nRow + "]/td")).size();
	}

	/**
	 * Retorna o valor em texto da linha/coluna específica
	 * 
	 * @param table
	 *            A tabela em WebElement que será utilizada
	 * @param row
	 *            A linha que será pesquisada. Caso o valor seja 0 será analisado o
	 *            cabeçalho. Caso o contrário será análisado o corpo do texto.
	 *            Portanto não conte o cabeçalho na hora de validar!
	 * @param col
	 *            A coluna da qual quer verificar
	 * @return Retorna o valor em texto da linha/coluna específica em String
	 */
	public static String GetCellData(WebElement table, int row, int col) {
		int nRow = row == 0 ? 1 : row;
		return table.findElement(By.xpath(xpathHelper(row) + "/tr[" + nRow + "]/td[" + col + "]")).getText();
	}

	/**
	 * Retorna o link de uma célula específica
	 * 
	 * @param table
	 *            A tabela em WebElement que será utilizada
	 * @param row
	 *            A linha que será pesquisada. Caso o valor seja 0 será analisado o
	 *            cabeçalho. Caso o contrário será análisado o corpo do texto.
	 *            Portanto não conte o cabeçalho na hora de validar!
	 * @param col
	 *            A coluna da qual quer verificar
	 * @return Retorna o link de uma célula específica como WebElement
	 */
	public static WebElement GetCellLink(WebElement table, int row, int col) {
		int nRow = row == 0 ? 1 : row;
		return table.findElement(By.xpath(xpathHelper(row) + "/tr[" + nRow + "]/td[" + col + "]/a"));
	}

	/**
	 * Retorna o link de uma célula específica com o um texto específico. Usado caso
	 * aja mais de um link
	 * 
	 * @param table
	 *            A tabela em WebElement que será utilizada
	 * @param row
	 *            A linha que será pesquisada. Caso o valor seja 0 será analisado o
	 *            cabeçalho. Caso o contrário será análisado o corpo do texto.
	 *            Portanto não conte o cabeçalho na hora de validar!
	 * @param col
	 *            A coluna da qual quer verificar
	 * @param linkText
	 *            O texto do Link que você quer procurar
	 * @return Retorna o link de uma célula específica com o um texto específico
	 *         como WebElement.
	 */
	public static WebElement GetCellLink(WebElement table, int row, int col, String linkText) {
		int nRow = row == 0 ? 1 : row;
		return table.findElement(
				By.xpath(xpathHelper(row) + "/tr[" + nRow + "]/td[" + col + "]/a[text()='" + linkText + "']"));
	}

	private static String xpathHelper(int row) {
		String xpath;
		if (row == 0) {
			row = 1;
			xpath = "//thead";
		} else {
			xpath = "//tbody";
		}
		return xpath;
	}
	
	public static String editString(String a) {
		String s = Normalizer.normalize(a, Normalizer.Form.NFD);
		s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		String[] subs = s.split(" ");
		String string = "";
		for (int i = 0; i < subs.length; i++) {
			string = string + subs[i].substring(0, 1).toUpperCase() + subs[i].substring(1).toLowerCase();
		}
		string = string + "Page";
		return string;
	}

	@SuppressWarnings("deprecation")
	public static Object retornaClasse(String param) throws Exception {

		String className = TableUtils.editString(param);
		Object myObjeto = Class.forName("com.serasa.pages." + className).newInstance();
		
		return myObjeto;
	}
	
}
