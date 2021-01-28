package com.serasa.pages;

import com.serasa.common.utils.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SerajaJudHomePage {

	public SerajaJudHomePage() {
		PageFactory.initElements(DriverUtils.getDriver(), this);
	}
	
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
