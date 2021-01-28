package com.serasa.pages;

import org.openqa.selenium.WebElement;

public abstract class AbstractPage {
	
	public abstract WebElement getTitle();
	
	public abstract WebElement getTable();
	
	public abstract WebElement getPositiveValue();
	
	public abstract WebElement getDisclaimer();
	
	public abstract WebElement getToolTipIn();
	
	public abstract WebElement getToolTipOut();
	
	public abstract WebElement getMsgNaoCalc();
	
}
