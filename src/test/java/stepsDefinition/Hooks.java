package stepsDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import functions.CreateDriver;
//import org.apache.log4j.Logger;
//import org.apache.logging.log4j;
//import org.apache.logging.log4j.core.Logger;
//import java.util.logging.Logger;

public class Hooks {
    public static WebDriver driver;
    Logger log = LogManager.getLogger(Hooks.class);
    //Logger log = LogManager.getLogger(Hooks.class);
    //getLogger((Hooks.class));
    //Logger log = Logger.getLogger(Hooks.class);
    Scenario scenario = null;

    @Before
    public void initDriver(Scenario scenario) throws IOException {
        log.info("######");
        log.info("[ Configuration ] - Inicializando la configuración del controlador.");
        log.info("######");
        driver = CreateDriver.initConfig();
        this.scenario = scenario;
        log.info("######");
        log.info("[ Scenario ] - " + scenario.getName());
        log.info("######");
    }

    @After
    public void embedScreenshot(Scenario scenario) throws IOException {
        log.info("######");
        log.info("[ Driver status ] - Limpiar y cerrar la instancia del controlador.");
        log.info("######");
        driver.quit();
}
}