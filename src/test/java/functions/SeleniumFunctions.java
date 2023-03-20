package functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import stepsDefinition.Hooks;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SeleniumFunctions {
    private static Logger log = LogManager.getLogger(SeleniumFunctions.class);
    public static String FileName = "";
    public static String PagesFilePath = "src/test/resources/Pages";
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
}

