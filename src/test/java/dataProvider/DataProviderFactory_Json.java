package dataProvider;

import helpers.JsonHelper;
import org.testng.annotations.DataProvider;

public class DataProviderFactory_Json {
    JsonHelper jsonHelper = new JsonHelper();

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

    @DataProvider(name = "DataJsonObjectFile", parallel = true)
    public Object[][] dataJsonObjectFile()  {
        Object[][] data = jsonHelper.getJsonObjectFile("src/test/resources/jsonData/Object.json");
        return data;
    }

    @DataProvider(name = "DataJsonObjectFileWithSpecifiedRow", parallel = true)
    public Object[][] dataJsonObjectFileWithSpecifiedRow()  {
        JsonHelper jsonHelper = new JsonHelper();
        Object[][] data = jsonHelper.getJsonObjectFileWithSpecifiedRow("src/test/resources/jsonData/Object.json", 1, 3);
        return data;
    }

    // Test Login CRM
    @DataProvider(name = "Data Login Fail", parallel = true)
    public Object[][] dataJsonLoginFail()  {
        JsonHelper jsonHelper = new JsonHelper();
        Object[][] data = jsonHelper.getJsonArrayObjectFile("src/test/resources/jsonData/ObjectArrayLoginData.json", "LoginFail");
        return data;
    }
    @DataProvider(name = "Data Login Null Email/Password", parallel = false)
    public Object[][] dataJsonLoginFail2()  {
        JsonHelper jsonHelper = new JsonHelper();
        Object[][] data = jsonHelper.getJsonArrayObjectFileWithSpecifiedRow("src/test/resources/jsonData/ObjectArrayLoginData.json", "LoginFail",3, 4);
        return data;
    }
}
