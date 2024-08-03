package Example.DataProviderCombineJsonFiles;

import com.google.gson.JsonObject;
import dataProviders.DataProviderJson;
import helpers.JsonHelper;
import org.testng.annotations.Test;

public class NestObjectFile {
    JsonHelper jsonHelper = new JsonHelper();

    @Test
    public void getNestObjectFile() {
        jsonHelper.setJsonFile("src/test/resources/jsonData/NestObject.json");
        System.out.println("Name " + jsonHelper.getJsonValueNestObject("nhu", "name"));
        System.out.println("Age " + jsonHelper.getJsonValueNestObject("nhu", "age"));
    }

    @Test(dataProvider = "DataJsonNestObjectFile", dataProviderClass = DataProviderJson.class)
    public void testJsonNestObjectFile(String name, String age, String gender) {
        System.out.println("Name " + name);
        System.out.println("Age " + age);
        System.out.println("Gender " + gender);
    }

    @Test(dataProvider = "DataJsonNestObjectFileWithSpecifiedRow", dataProviderClass = DataProviderJson.class)
    public void testJsonNestObjectFileWithSpecifiedRow(JsonObject data) {
        System.out.println("Name " + data.get("name").getAsString());
        System.out.println("Age " + data.get("age").getAsString());
        System.out.println("Gender " + data.get("gender").getAsString());
    }

    @Test(dataProvider = "DataJsonNestObjectFileWithSpecifiedKeys", dataProviderClass = DataProviderJson.class)
    public void testJsonNestObjectFileWithSpecifiedKeys(String name, String age, String gender) {
        System.out.println("Name " + name);
        System.out.println("Age " + age);
        System.out.println("Gender " + gender);
    }

    @Test(dataProvider = "DataJsonNestObjectFileWithSpecifiedKey", dataProviderClass = DataProviderJson.class)
    public void testJsonNestObjectFileWithSpecifiedKey(String name, String age, String gender) {
        System.out.println("Name " + name);
        System.out.println("Age " + age);
        System.out.println("Gender " + gender);
    }

}
