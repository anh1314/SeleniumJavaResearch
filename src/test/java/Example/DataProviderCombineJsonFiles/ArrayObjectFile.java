package Example.DataProviderCombineJsonFiles;

import dataProviders.DataProviderJson;
import helpers.JsonHelper;
import org.testng.annotations.Test;

public class ArrayObjectFile {
    JsonHelper jsonHelper = new JsonHelper();

    @Test
    public void getObjectArrayFile() {
        jsonHelper.setJsonFile("src/test/resources/jsonData/ObjectArray.json");
        System.out.println("Email " + jsonHelper.getJsonValueObjectArray(2, "email"));
        System.out.println("Password " + jsonHelper.getJsonValueObjectArray(2, "password"));
    }

    @Test(dataProvider = "DataJsonArrayObjectFile", dataProviderClass = DataProviderJson.class)
    public void testJsonArrayObjectFile(String ID, String Email, String Password) {
        System.out.println("Get data with id " + ID);
        System.out.println("Email " + Email);
        System.out.println("Password " + Password);
    }

    @Test(dataProvider = "DataJsonArrayObjectFileWithSpecifiedRow", dataProviderClass = DataProviderJson.class)
    public void testJsonArrayObjectFileWithSpecifiedRow(String ID, String Email, String Password) {
        System.out.println("Get data with id " + ID);
        System.out.println("Email " + Email);
        System.out.println("Password " + Password);
    }
}
