package pages;

import common.utils.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageGamersClub {

	public HomePageGamersClub() {
		PageFactory.initElements(DriverUtils.getDriver(), this);
	}

	@FindBy(xpath = "/html/body/app-root/app-listar-ordem/div/div/div/table/tbody")
	WebElement listaOrdens;

	////table[contains(.,'Nº Processo')]
	////table[@class='mat-elevation-z8 mat-table']

//	/html/body/app-root/app-listar-ordem/div/div/div/table/tbody/tr[13]/td[7]/button
//
//	/html/body/app-root/app-listar-ordem/div/div/div/table/tbody/tr[13]/td[6]/div
//
//	driver.get("https://beta.gamersclub.com.br/jogador/932811");
//    driver.findElement(By.xpath("//td")).click();
//    driver.findElement(By.xpath("//tr[3]/td")).click();
//    driver.findElement(By.xpath("//tr[5]/td")).click();
//    driver.findElement(By.id("u653_img")).click();
//	// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_1 | ]]
//	// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
//    driver.findElement(By.cssSelector("button.mat-paginator-navigation-next.mat-icon-button > span.mat-button-wrapper > svg.mat-paginator-icon")).click();
//    driver.findElement(By.cssSelector("button.mat-paginator-navigation-last.mat-icon-button.ng-star-inserted > span.mat-button-wrapper > svg.mat-paginator-icon")).click();
//    driver.findElement(By.cssSelector("button.mat-paginator-navigation-previous.mat-icon-button > span.mat-button-wrapper > svg.mat-paginator-icon > path")).click();
//    driver.findElement(By.cssSelector("svg.mat-paginator-icon")).click();
//    driver.findElement(By.xpath("//mat-select[@id='mat-select-0']/div/div[2]/div")).click();
//    driver.findElement(By.xpath("//mat-option[@id='mat-option-4']/span")).click();

	@FindBy(xpath = "//div[@id='navbarSupportedContent']/ul/li/button/span")
	WebElement btnPaginaInicial;

	@FindBy(xpath = "//div[@id='navbarSupportedContent']/ul/li[2]/button/span")
	WebElement btnOrdem;

	@FindBy(xpath = "//h5")
	WebElement btnOrdensCadastradas;

	@FindBy(xpath = "//div/button/span")
	WebElement btnCadastrarOrdem;

	@FindBy(id = "mat-input-0")
	WebElement campoBuscaCnj;

	@FindBy(xpath = "//div[4]/button/span")
	WebElement btnBuscar;

	@FindBy(xpath = "//div[@id='navbarSupportedContent']/div/ul/li/button/span")
	WebElement btnUsuario;

	@FindBy(xpath = "//div[@id='cdk-overlay-0']/div/div/button")
	WebElement btnSair;

	@FindBy(xpath = "//a[@id='navegacao-links']/img")
	WebElement logoHome;

	public WebElement getBtnPaginaInicial() {
		return btnPaginaInicial;
	}

	public WebElement getBtnOrdem() {
		return btnOrdem;
	}

	public WebElement getBtnOrdensCadastradas() {
		return btnOrdensCadastradas;
	}

	public WebElement getBtnCadastrarOrdem() {
		return btnCadastrarOrdem;
	}

	public WebElement getCampoBuscaCnj() {
		return campoBuscaCnj;
	}

	public WebElement getBtnBuscar() {
		return btnBuscar;
	}

	public WebElement getBtnUsuario() {
		return btnUsuario;
	}

	public WebElement getBtnSair() { return btnSair; }

	public WebElement getLogoHome() {
		return logoHome;
	}
}
