package hooks;

import java.io.File;
import java.util.Collection;

import common.drivers.DriverType;
import common.utils.DriverUtils;
import common.utils.TaskManagerUtils;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;



public class Hooks {
	private static WebDriver driver;
	private static Collection<String> taggs;
	private static Scenario scenario;
	private static String TempDriverLocation;
	private static File TempDriver;
	private static DriverType runningDriver;
	
	@Before
	@org.junit.Before
	public void runBeforeWithOrder(Scenario scenario) throws Throwable {

		Hooks.setScenario(scenario);
		keepScenarion(scenario);
		DriverUtils.setScenario(scenario);
	}
	

	
	public static void tearDown() throws InterruptedException {
		String name = getScenario().getName();
		driver = DriverUtils.getDriver();
		try {
			if (name.contains("Contabilizacao")) {
				System.out.println(name + " - Cenário não abre instância do Browser");
			} else {
				driver.manage().deleteAllCookies();
				driver.quit();
				if (getRunningDriver() == DriverType.CHROME) {
					TaskManagerUtils.killProcess("chromedriver.exe");
				}
			}
		} catch (Exception e) {
			System.out.println("Methods Failed: tearDown, Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@After
	public void ScreenshotOnFailure(Scenario scenario) throws InterruptedException {
		String name = getScenario().getName();
		driver = DriverUtils.getDriver();
		try {
			if (getScenario().isFailed()) {
				DriverUtils.tirarScreenShot();
			} else if (name.contains("Contabilizacao")) {
				System.out.println(name + " - Cenário não abre instância do Browser");
			}
		} catch (Exception e) {
			System.out.println("Methods Failed: ScreenshotOnFailure, Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void keepScenarion(Scenario scenario) {
		setTaggs(scenario.getSourceTagNames());
	}

	public static Collection<String> getTaggs() {
		return taggs;
	}

	public static void setTaggs(Collection<String> taggs) {
		Hooks.taggs = taggs;
	}

	public static Scenario getScenario() {
		return scenario;
	}

	public static void setScenario(Scenario scenario) {
		Hooks.scenario = scenario;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		Hooks.driver = driver;
	}

	public static File getTempDriver() {
		return TempDriver;
	}

	public static void setTempDriver(File tempDriver) {
		TempDriver = tempDriver;
	}

	public static String getTempDriverLocation() {
		return TempDriverLocation;
	}

	public static void setTempDriverLocation(String tempDriverLocation) {
		TempDriverLocation = tempDriverLocation;
	}

	public static DriverType getRunningDriver() {
		return runningDriver;
	}

	public static void setRunningDriver(DriverType runningDriver) {
		Hooks.runningDriver = runningDriver;
	}

}