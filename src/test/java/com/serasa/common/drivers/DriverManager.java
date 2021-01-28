package com.serasa.common.drivers;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

import com.serasa.hooks.Hooks;


public class DriverManager {

static String driverPath;
	
	public static WebDriver getManager(DriverType type) throws MalformedURLException {
		switch (type) {
		case CHROME: ChromeDriverManager chrDrvMng = new ChromeDriverManager();
			Hooks.setRunningDriver(type);
			return chrDrvMng.getDriver();
		case IE: IEDriverManager ieDrvMng = new IEDriverManager();
			Hooks.setRunningDriver(type);
			return ieDrvMng.getDriver();
		case DOCKER_CHROME: 
			return DockerChromeManager.DockerChromeDriver();
		default:
			return null;
		}
	}
}