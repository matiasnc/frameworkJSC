package functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepsDefinition.Hooks;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.time.Duration;

import org.openqa.selenium.support.ui.Select;

public class SeleniumFunctions {
    public static final int EXPLICIT_TIMEOUT = 5;
    static WebDriver driver;
    public SeleniumFunctions() {driver = Hooks.driver;}
    private static Logger log = LogManager.getLogger(SeleniumFunctions.class);
    public static String FileName = "";
    public static String PagesFilePath = "src/test/resources/Pages/";
    public static String GetFieldBy = "";
    public static String ValueToFind = "";
    public String ElementText = "";
    public static boolean isDisplayed = false;

    public static Object readJson() throws Exception {
        FileReader reader = new FileReader(PagesFilePath + FileName);
            try {
                if (reader != null) {
                    JSONParser jsonParser = new JSONParser();
                    return jsonParser.parse(reader);
                } else {
                    return null;
                }
            } catch (FileNotFoundException | NullPointerException e) {
                log.error("No existe el archivo " + FileName);
                throw new IllegalStateException("No existe el archivo " + FileName, e);
            }
    }

    public static JSONObject ReadEntity(String element) throws Exception{
        JSONObject Entity = null;
        JSONObject jsonObject = (JSONObject) readJson();
        Entity = (JSONObject) jsonObject.get(element);
        log.info(Entity.toJSONString());
        return Entity;
    }

    public static By getCompleteElement(String element) throws Exception{
        By result = null;
        JSONObject Entity = ReadEntity(element);

        GetFieldBy = (String) Entity.get("GetFieldBy");
        ValueToFind = (String) Entity.get("ValueToFind");

        if("className".equalsIgnoreCase(GetFieldBy)){
            result = By.className(ValueToFind);
        } else if ("cssSelector".equalsIgnoreCase(GetFieldBy)){
            result = By.cssSelector(ValueToFind);
        } else if ("id".equalsIgnoreCase(GetFieldBy)){
            result = By.id(ValueToFind);
        } else if ("linkText".equalsIgnoreCase(GetFieldBy)){
            result = By.linkText(ValueToFind);
        } else if ("name".equalsIgnoreCase(GetFieldBy)){
            result = By.name(ValueToFind);
        } else if ("tagName".equalsIgnoreCase(GetFieldBy)){
            result = By.tagName(ValueToFind);
        } else if ("xpath".equalsIgnoreCase(GetFieldBy)){
            result = By.xpath(ValueToFind);
        }
        return result;
    }

    public void waitForElementPresent(String element) throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT));
        log.info("Esperando el elemento: "+element);
        w.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement));
    }

    public void waitForElementVisible(String element) throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT));
        log.info("Esperando el elemento: "+element);
        w.until(ExpectedConditions.visibilityOfElementLocated(SeleniumElement));
    }

    public String GetTextElement(String element) throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT));
        w.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement));
        log.info(String.format("Esperando el elemento: %s", element));

        ElementText = driver.findElement(SeleniumElement).getText();
        return ElementText;
    }

    public void checkPartialTextElementNotPresent(String element, String text) throws Exception{
        ElementText = GetTextElement(element);
        boolean isFound = ElementText.indexOf(text) !=-1? true: false;
        Assert.assertFalse("El texto no esta presente en el elemento: " + element + ", el texto actual es: " +ElementText, isFound);
    }
    public void checkPartialTextElementPresent(String element, String text) throws Exception{
        ElementText = GetTextElement(element);
        boolean isFound = ElementText.contains(text);
        Assert.assertTrue("El texto no esta presente en el elemento: " + element + ", el texto actual es: " +ElementText, isFound);
    }

    public void checkTextElementEqualTo(String element, String text) throws Exception{
        ElementText = GetTextElement(element);
        Assert.assertEquals("El texto no esta presente en el elemento: " + element + ", el texto actual es: " +ElementText, text, ElementText);
    }

    public boolean isElementDisplayed(String element) throws Exception{
        try{
            By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
            log.info(String.format("Esperando el elemento: %s", element));
            WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT));
            isDisplayed = w.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement)).isDisplayed();
        }catch (NoSuchElementException | TimeoutException e){
            isDisplayed = false;
            log.info(e);
        }
        log.info(String.format("%s es visible: %s", element, isDisplayed));
        return isDisplayed;
    }
}
