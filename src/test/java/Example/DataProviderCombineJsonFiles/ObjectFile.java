package Example.DataProviderCombineJsonFiles;

import dataProviders.DataProviderJson;
import helpers.JsonHelper;
import org.testng.annotations.Test;

public class ObjectFile {
    JsonHelper jsonHelper = new JsonHelper();

    @Test
    public void getObjectFile() {
        jsonHelper.setJsonFile("src/test/resources/jsonData/Object.json");
        System.out.println("URL " + jsonHelper.getJsonValue("url"));
        System.out.println("Name " + jsonHelper.getJsonValue("name"));
    }

    @Test(dataProvider = "DataJsonObjectFile", dataProviderClass = DataProviderJson.class)
    public void JsonObjectFile(String url, String cccd, String name, String email) {
        System.out.println("URL " + url);
        System.out.println("CCCD " + cccd);
        System.out.println("Name " + name);
        System.out.println("Email " + email);
    }

    @Test(dataProvider = "DataJsonObjectFileWithSpecifiedRow", dataProviderClass = DataProviderJson.class)
    public void testJsonObjectFileWithSpecifiedRow(String URL, String CCCD, String Name) {
        System.out.println("URL " + URL);
        System.out.println("CCCD " + CCCD);
        System.out.println("Name " + Name);
    }
}
