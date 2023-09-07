package functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import stepsDefinition.Hooks;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CreateDriver {
        private static String browser;
        private static String os;
        private static String logLevel;
        private static Properties pro = new Properties();
        private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");
        private static Logger log = LogManager.getLogger(CreateDriver.class);
        private static CreateDriver instance = null;
        private CreateDriver() throws IOException {
                CreateDriver.initConfig();
        }

        public static WebDriver initConfig() throws IOException {
              WebDriver driver;

              try {
                      log.info("#########");
                      log.info("[POM Configuration] Lee la configuración de propiedades básicas del: ../test.properties");
                      pro.load(in);
                      browser = pro.getProperty("browser");
                      os = pro.getProperty("os");
                      logLevel = pro.getProperty("logLevel");
                      System.setProperty("webdriver.http.factory", "jdk-http-client");
              }catch (IOException e){
                      log.error("initConfig Error", e);
              }
              log.info("[POM Configuration] - OS: "+os+" | Browser: "+browser+" |");
              log.info("[POM Configuration] - LoggerLevel: "+logLevel+" |");
              log.info("#########");

              driver = WebDriverFactory.createNewWebDriver(browser,os);

              return driver;
        }
}


