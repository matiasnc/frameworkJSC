package stepsDefinition;

import functions.CreateDriver;
import functions.SeleniumFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.openqa.selenium.By;

public class Steps {

    private static Properties pro = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");
    WebDriver driver;
    SeleniumFunctions functions = new SeleniumFunctions();
    public Steps() {
        driver = Hooks.driver;
    }
    private static Logger log = LogManager.getLogger(Steps.class);

    /**** Ingreso a la pagina de logueo desde test.properties ****/
    @Given("^El cliente se encuentra en la pantalla de logeo$")
    public void ingresarEnPantallaDeLogueo() throws IOException {
        pro.load(in);
        String url = pro.getProperty("MainUrl");
        log.info(("Navegar a: "+ url));
        driver.get(url);
    }

    /**** Ingreso a la pagina de logueo que se indica en el feature ****/
    @Given("^Se ingresa al sitio (.*)$")
    public void ingresoAlSitioDelFeature(String url) throws IOException {
        log.info(("Navegar a: "+ url));
        driver.get(url);
    }

    /**** Se cierran todos los navegadores ****/
    @Then("^Se cierran los navegadores$")
    public void seCierranLosNavegadores() throws IOException {
        driver.quit();
    }

    /****  ****/
    @Then("^Cargo la informacion del DOM (.*)$")
    public void cargaDeDatosDesdeJson(String file) throws Exception {
        SeleniumFunctions.FileName = file;
        SeleniumFunctions.readJson();
        log.info("Se abre el archivo json: "+file);
    }

    @And("^Hago un clic en el elemento (.*)$")
    public void hagoUnClicEnElElemento(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        functions.waitForElementPresent(element);
        driver.findElement(SeleniumElement).click();
        log.info("Clic en el elemento: "+element);
    }

    @And("^Configuro el elemento (.*) con el texto (.*)$")
    public void configuroElElementoConElTexto(String element, String text) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).sendKeys(text);
    }

    @Then("^Confirmo si el elemento (.*) contiene (.*)$")
    public void confirmoSiElElementoContiene(String element, String text) throws Exception {
        functions.checkTextElementEqualTo(element, text);
    }
}
