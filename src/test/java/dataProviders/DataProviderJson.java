package dataProviders;

import helpers.JsonHelper;
import org.testng.annotations.DataProvider;

public class DataProviderJson {
    JsonHelper jsonHelper = new JsonHelper();

    // ARRAY OBJECT file
    @DataProvider(name = "DataJsonArrayObjectFile", parallel = true)
    public Object[][] dataJsonArrayObject()  {
        Object[][] data = jsonHelper.getJsonArrayObjectFile("src/test/resources/jsonData/ObjectArray.json", "usersLogin");
        return data;
    }

    @DataProvider(name = "DataJsonArrayObjectFileWithSpecifiedRow", parallel = true)
    public Object[][] dataJsonArrayObjectFileWithSpecifiedRow()  {
        Object[][] data = jsonHelper.getJsonArrayObjectFileWithSpecifiedRow("src/test/resources/jsonData/ObjectArray.json", "usersLogin", 1, 2);
        return data;
    }

    // OBJECT file
    @DataProvider(name = "DataJsonObjectFile", parallel = true)
    public Object[][] dataJsonObjectFile()  {
        Object[][] data = jsonHelper.getJsonObjectFile("src/test/resources/jsonData/Object.json");
        return data;
    }

    @DataProvider(name = "DataJsonObjectFileWithSpecifiedRow", parallel = true)
    public Object[][] dataJsonObjectFileWithSpecifiedRow()  {
        Object[][] data = jsonHelper.getJsonObjectFileWithSpecifiedRow1("src/test/resources/jsonData/Object.json", 1, 3);
        return data;
    }

    // NEST OBJECT file
    @DataProvider(name = "DataJsonNestObjectFile", parallel = true)
    public Object[][] dataJsonNestObjectFile(){
        Object[][] data = jsonHelper.getJsonNestObjectFile("src/test/resources/jsonData/NestObject.json");
        return data;
    }

    @DataProvider(name = "DataJsonNestObjectFileWithSpecifiedRow", parallel = true)
    public Object[][] dataJsonNestObjectFileWithSpecifiedRow(){
        Object[][] data = jsonHelper.getJsonNestObjectFileWithSpecifiedRow("src/test/resources/jsonData/NestObject.json", 1, 2);
        return data;
    }

    @DataProvider(name = "DataJsonNestObjectFileWithSpecifiedKeys", parallel = true)
    public Object[][] dataJsonNestObjectFileWithSpecifiedKeys(){
        String[] keys = {"hieu", "nhu"};
        Object[][] data = jsonHelper.getJsonNestObjectFileWithSpecifiedKeys("src/test/resources/jsonData/NestObject.json", keys);
        return data;
    }

    @DataProvider(name = "DataJsonNestObjectFileWithSpecifiedKey", parallel = true)
    public Object[][] dataJsonNestObjectFileWithSpecifiedKey(){
        Object[][] data = jsonHelper.getJsonNestObjectFileWithSpecifiedKey("src/test/resources/jsonData/NestObject.json", "nhat");
        return data;
    }


}
