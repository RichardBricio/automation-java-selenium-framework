package com.serasa.common.drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DockerChromeManager {
	
	public static WebDriver DockerChromeDriver() throws MalformedURLException {
/*		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability("version", "");
		cap.setCapability("platform", "LINUX");*/
		
		
		ChromeOptions options = new ChromeOptions();
		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
		options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
		options.addArguments("--start-maximized");
		
		//Indicar on caminho do container com a imagem do Hub
		return new RemoteWebDriver(new URL("http://selenium-hub.f-internal.br.appcanvas.net:80/wd/hub"), options);
	}
	
}
