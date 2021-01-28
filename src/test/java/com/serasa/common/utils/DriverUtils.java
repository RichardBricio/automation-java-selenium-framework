package com.serasa.common.utils;

import java.awt.AWTException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.Normalizer;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.serasa.common.drivers.DriverManager;
import com.serasa.common.drivers.DriverType;
import com.serasa.hooks.Hooks;

import io.cucumber.java.Scenario;

public class DriverUtils {


    static WebDriver driver;
    static Scenario scenario;
    static WebDriverWait wait;
    static String screenshotName;
    protected static JavascriptExecutor jsExecutor;
    final static Logger logger = LogManager.getLogger(DriverUtils.class);

    public static void selecionaBrowser(DriverType selDriver) throws MalformedURLException {
        driver = DriverManager.getManager(selDriver);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public static void setScenario(Scenario sce) {
        scenario = sce;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    /**********************************************************************************
     ** CLICK METHODS
     *
     * @throws IOException
     **********************************************************************************/

    public static void waitAndClickElement(WebElement element) throws InterruptedException, IOException {

        boolean clicked = false;
        int attempts = 0;
        wait = new WebDriverWait(driver, 30);
        while (!clicked && attempts < 5) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                logger.info("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");
                clicked = true;
            } catch (Exception e) {
                logger.error("Unable to wait and click on WebElement, Exception: " + e.getMessage());
                Assert.fail(
                        "Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
            }
            attempts++;
        }
    }

    public static WebElement waitElementToBeClickable(Object element, int timeoutInSeconds, int poolingInSeconds)
            throws IOException {
        WebElement el = null;
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(poolingInSeconds)).ignoring(NoSuchElementException.class);
        try {
            logger.info("INICIANDO ESPERA IMPLICITA NO OBJETO: " + element.toString());
            el = wait.until(ExpectedConditions.elementToBeClickable((WebElement) element));
        } catch (RuntimeException e) {
//            Utils.checkErrors();
            logger.error(e.getMessage());
        }
        return el;
    }

    public WebElement waitElementToBeClickableById(String type, String nameToFind, int timeoutInSeconds,
                                                   int poolingInSeconds) throws IOException {
        WebElement element = null;

        logger.info("INICIANDO ESPERA IMPLICITA NO OBJETO: " + nameToFind);
        element = fluentWaitToBeClickable("id", nameToFind, timeoutInSeconds, poolingInSeconds);

        return element;
    }

    private WebElement fluentWaitToBeClickable(String type, String fieldName, int timeoutInSeconds,
                                               int poolingInSeconds) throws IOException {

        logger.info("INICIANDO ESPERA IMPLICITA NO OBJETO: " + fieldName);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(poolingInSeconds)).ignoring(NoSuchElementException.class);
        switch (type) {
            case "id":
                return wait.until(ExpectedConditions.elementToBeClickable(By.id(fieldName)));
            case "xpath":
                return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(fieldName)));
            case "className":
                return wait.until(ExpectedConditions.elementToBeClickable(By.className(fieldName)));
        }

        return null;
    }

    public static void waitAndClickElementsUsingByLocator(By by) throws InterruptedException {
        boolean clicked = false;
        int attempts = 0;
        wait = new WebDriverWait(driver, 30);
        while (!clicked && attempts < 10) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(by)).click();
                logger.info("Successfully clicked on the element using by locator: " + "<" + by.toString() + ">");
                clicked = true;
            } catch (Exception e) {
                logger.error(
                        "Unable to wait and click on the element using the By locator, Exception: " + e.getMessage());
                Assert.fail("Unable to wait and click on the element using the By locator, element: " + "<"
                        + by.toString() + ">");
            }
            attempts++;
        }
    }

    public static void clickOnTextFromDropdownList(WebElement list, String textToSearchFor) throws Exception {
        Wait<WebDriver> tempWait = new WebDriverWait(driver, 30);
        try {
            tempWait.until(ExpectedConditions.elementToBeClickable(list)).click();
            list.sendKeys(textToSearchFor);
            list.sendKeys(Keys.ENTER);
            System.out.println("Successfully sent the following keys: " + textToSearchFor
                    + ", to the following WebElement: " + "<" + list.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to send the following keys: " + textToSearchFor
                    + ", to the following WebElement: " + "<" + list.toString() + ">");
            Assert.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
        }
    }

    public static void clickOnElementUsingCustomTimeout(WebElement locator, WebDriver driver, int timeout) {
        try {
            final WebDriverWait customWait = new WebDriverWait(driver, timeout);
            customWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
            locator.click();
            System.out.println("Successfully clicked on the WebElement, using locator: " + "<" + locator + ">"
                    + ", using a custom Timeout of: " + timeout);
        } catch (Exception e) {
            System.out.println("Unable to click on the WebElement, using locator: " + "<" + locator + ">"
                    + ", using a custom Timeout of: " + timeout);
            Assert.fail("Unable to click on the WebElement, Exception: " + e.getMessage());
        }
    }

    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** ACTION METHODS
     **********************************************************************************/

    public static void moveAndClickInPositionElement(WebElement element) {
        Actions ob = new Actions(driver);
        wait = new WebDriverWait(driver, 30);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
            Point pt = element.getLocation();
            int numberX = pt.getX();
            int numberY = pt.getY();
            ob.moveByOffset(numberX + 1, numberY).click().build().perform();
            logger.info("Successfully Action Moved and Clicked on the WebElement, using locator: X: " + numberX + "Y: "
                    + numberY + " < " + element.toString() + ">");
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement elementToClick = element;
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).isEnabled();
            if (elementPresent == true) {
                ob.moveToElement(elementToClick).click().build().perform();
                logger.info(
                        "(Stale Exception) - Successfully Action Moved and Clicked on the WebElement, using locator: "
                                + "<" + element.toString() + ">");
            }
        } catch (Exception e) {
            logger.error("Unable to Action Move and Click on the WebElement, using locator: " + "<" + element.toString()
                    + ">");
            Assert.fail("Unable to Action Move and Click on the WebElement, Exception: " + e.getMessage());
        }
    }

    public static void actionMoveAndClick(WebElement element) throws Exception {
        Actions ob = new Actions(driver);
        wait = new WebDriverWait(driver, 30);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
            ob.moveToElement(element).click().build().perform();
            logger.info("Successfully Action Moved and Clicked on the WebElement, using locator: " + "<"
                    + element.toString() + ">");
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement elementToClick = element;
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).isEnabled();
            if (elementPresent == true) {
                ob.moveToElement(elementToClick).click().build().perform();
                logger.info(
                        "(Stale Exception) - Successfully Action Moved and Clicked on the WebElement, using locator: "
                                + "<" + element.toString() + ">");
            }
        } catch (Exception e) {
            logger.error("Unable to Action Move and Click on the WebElement, using locator: " + "<" + element.toString()
                    + ">");
            Assert.fail("Unable to Action Move and Click on the WebElement, Exception: " + e.getMessage());
        }
    }

    public static void actionMoveAndClickByLocator(By element) throws Exception {
        Actions ob = new Actions(driver);
        wait = new WebDriverWait(driver, 30);
        try {
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
            if (elementPresent == true) {
                WebElement elementToClick = driver.findElement(element);
                ob.moveToElement(elementToClick).click().build().perform();
                System.out.println("Action moved and clicked on the following element, using By locator: " + "<"
                        + element.toString() + ">");
            }
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement elementToClick = driver.findElement(element);
            ob.moveToElement(elementToClick).click().build().perform();
            System.out
                    .println("(Stale Exception) - Action moved and clicked on the following element, using By locator: "
                            + "<" + element.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to Action Move and Click on the WebElement using by locator: " + "<"
                    + element.toString() + ">");
            Assert.fail(
                    "Unable to Action Move and Click on the WebElement using by locator, Exception: " + e.getMessage());
        }
    }

    public static void scrollIntoView(WebElement element) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollIntoMiddleView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        js.executeScript(scrollElementIntoMiddle, element);
    }

    public static void fecharBrowser() throws IOException, InterruptedException {
        driver.manage().deleteAllCookies();
        driver.quit();
        if (Hooks.getRunningDriver() == DriverType.CHROME)
            TaskManagerUtils.killProcess("chromedriver.exe");
    }

    // It'll be correct if element is not visible
    public static boolean elementIsNotVisible(By element) {
        try {
            driver.findElement(element);
            logger.error("Not worked! Element is visible");
            return false;
        } catch (Exception e) {
            logger.info("Worked! Element is not visible");
            return true;
        }
    }

    public static boolean hasClass(WebElement element, String classe) {
        String classes = element.getAttribute("class");
        for (String c : classes.split(" ")) {
            if (c.equals(classe)) {
                logger.info("Classe encontrada com sucesso! Classe encontrada: " + c + "Classe esperada: " + classe);
                return true;
            }
        }
        return false;
    }

    public static boolean isElementClickable(WebElement element) {
        wait = new WebDriverWait(driver, 30);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.info("WebElement is clickable using locator: " + "<" + element.toString() + ">");
            return true;
        } catch (Exception e) {
            logger.error("WebElement is NOT clickable using locator: " + "<" + element.toString() + ">");
            return false;
        }
    }

    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** SEND KEYS METHODS /
     **********************************************************************************/
    public static void sendKeysToWebElement(WebElement element, String textToSend) throws Exception {
        try {
            WaitUntilWebElementIsVisible(element);
            element.clear();
            element.sendKeys(textToSend);
            logger.info("Successfully Sent the following keys: '" + textToSend + "' to element: " + "<"
                    + element.toString() + ">");
        } catch (Exception e) {
            logger.error("Unable to locate WebElement: " + "<" + element.toString() + "> and send the following keys: "
                    + textToSend);
            Assert.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
        }
    }

    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** JS METHODS & JS SCROLL
     **********************************************************************************/
    public static void scrollToElementByWebElementLocator(WebElement element) {
        wait = new WebDriverWait(driver, 30);
        try {
            wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -400)");
            System.out.println(
                    "Succesfully scrolled to the WebElement, using locator: " + "<" + element.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to scroll to the WebElement, using locator: " + "<" + element.toString() + ">");
            Assert.fail("Unable to scroll to the WebElement, Exception: " + e.getMessage());
        }
    }

    public static void jsPageScroll(int numb1, int numb2) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("scroll(" + numb1 + "," + numb2 + ")");
            System.out.println("Succesfully scrolled to the correct position! using locators: " + numb1 + ", " + numb2);
        } catch (Exception e) {
            System.out
                    .println("Unable to scroll to element using locators: " + "<" + numb1 + "> " + " <" + numb2 + ">");
            Assert.fail("Unable to manually scroll to WebElement, Exception: " + e.getMessage());
        }
    }

    public static void waitAndclickElementUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 30);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            js.executeScript("arguments[0].click();", element);
            System.out
                    .println("Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement staleElement = element;
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(staleElement)).isEnabled();
            if (elementPresent == true) {
                js.executeScript("arguments[0].click();", elementPresent);
                System.out.println("(Stale Exception) Successfully JS clicked on the following WebElement: " + "<"
                        + element.toString() + ">");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Unable to JS click on the following WebElement: " + "<" + element.toString() + ">");
            Assert.fail("Unable to JS click on the WebElement, Exception: " + e.getMessage());
        }
    }

    public static void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** WAIT METHODS
     **********************************************************************************/
    public static boolean WaitUntilWebElementIsVisible(WebElement element) {
        wait = new WebDriverWait(driver, 40);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("WebElement is visible using locator: " + "<" + element.toString() + ">");
            return true;
        } catch (Exception e) {
            logger.error("WebElement is NOT visible, using locator: " + "<" + element.toString() + ">");
            Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
            return false;
        }
    }

    public static WebElement waitElementIsVisiblePolling(Object element, int timeoutInSeconds, int poolingInSeconds)
            throws IOException {
        WebElement el = null;
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(poolingInSeconds)).ignoring(NoSuchElementException.class);
        try {
            logger.info("INICIANDO ESPERA IMPLICITA NO OBJETO: " + element.toString());
            el = wait.until(ExpectedConditions.visibilityOf((WebElement) element));
        } catch (RuntimeException e) {
            logger.error("WebElement is NOT visible, using locator: " + "<" + element.toString() + ">");
            logger.error(e.getMessage());
        }
        return el;
    }

    public static void esperarElementoComPolling(WebElement we) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofMillis(5)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(we));
    }

    public static boolean WaitUntilWebElementIsVisibleUsingByLocator(By element) {
        wait = new WebDriverWait(driver, 30);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            System.out.println("Element is visible using By locator: " + "<" + element.toString() + ">");
            return true;
        } catch (Exception e) {
            System.out.println("WebElement is NOT visible, using By locator: " + "<" + element.toString() + ">");
            Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
            return false;
        }
    }

    public static boolean waitUntilPreLoadElementDissapears(By element) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }


    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** PAGE METHODS
     **********************************************************************************/
    public static void navegar(String url) {
        driver.get(url);
    }

    public static String getCurrentURL() {
        try {
            String url = driver.getCurrentUrl();
            logger.info("Found(Got) the following URL: " + url);
            return url;
        } catch (Exception e) {
            logger.error("Unable to locate (Get) the current URL, Exception: " + e.getMessage());
            return e.getMessage();
        }
    }

    public static String waitForSpecificPage(String urlToWaitFor) {
        try {
            String url = driver.getCurrentUrl();
            wait.until(ExpectedConditions.urlMatches(urlToWaitFor));
            logger.info("The current URL was: " + url + ", " + "navigated and waited for the following URL: "
                    + urlToWaitFor);
            return urlToWaitFor;
        } catch (Exception e) {
            logger.error("Exception! waiting for the URL: " + urlToWaitFor + ",  Exception: " + e.getMessage());
            return e.getMessage();
        }
    }

    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** ALERT & POPUPS METHODS
     **********************************************************************************/
    public static void closePopups(By locator) throws InterruptedException {
        wait = new WebDriverWait(driver, 30);
        try {
            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    element.click();
                    wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
                    System.out.println("The popup has been closed Successfully!");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception! - could not close the popup!, Exception: " + e.toString());
            throw (e);
        }
    }

    public static boolean checkPopupIsVisible() {
        try {
            @SuppressWarnings("unused")
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("A popup has been found!");
            return true;
        } catch (Exception e) {
            System.err.println("Error came while waiting for the alert popup to appear. " + e.getMessage());
        }
        return false;
    }

    public static boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void closeAlertPopupBox() throws AWTException, InterruptedException {
        wait = new WebDriverWait(driver, 30);
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (UnhandledAlertException f) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            System.out.println("Unable to close the popup");
            Assert.fail("Unable to close the popup, Exception: " + e.getMessage());
        }
    }

    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** REPORT
     **********************************************************************************/

    public static void tirarScreenShot() throws IOException {
        // Embed screenshot to the cucumber report
        try {
            final byte[] screenshot = ((TakesScreenshot) DriverUtils.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Evidencia");
        } catch (Exception e) {
            System.out.println("Error to embed screenshot");
            Assert.fail("Error to embed screenshot, Exception: " + e.getMessage());
        }
    }

    public static String removeAcentos(String string) {
        if (string != null) {
            string = Normalizer.normalize(string, Normalizer.Form.NFD);
            string = string.replaceAll("[^\\p{ASCII}]", "");
        }
        return string;
    }

}
