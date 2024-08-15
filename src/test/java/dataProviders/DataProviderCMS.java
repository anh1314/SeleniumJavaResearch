package dataProviders;

import helpers.ExcelHelper;
import helpers.JsonHelper;
import org.testng.annotations.DataProvider;

public class DataProviderCMS {

    @DataProvider(name = "DataLoginFail", parallel = false)
    public Object[][] dataLogin() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getExcelData("src/test/resources/testData/dataLogin.xlsx", "LoginFail");
        return data;
    }

    @DataProvider(name = "Data provider Login excel hashtable")
    public Object[][] dataLoginHRMFromExcelHashtable() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable("src/test/resources/testData/dataLogin.xlsx", "LoginFail", 1, 4);
        return data;
    }

    // Test Login CRM  with file ObjectArrayLoginData.json
    @DataProvider(name = "Data Login Fail", parallel = true)
    public Object[][] dataJsonLoginFail() {
        JsonHelper jsonHelper = new JsonHelper();
        Object[][] data = jsonHelper.getJsonArrayObjectFile("src/test/resources/jsonData/ObjectArrayLoginData.json", "LoginFail");
        return data;
    }

    @DataProvider(name = "Data Login Null Email/Password", parallel = false)
    public Object[][] dataJsonLoginFail2() {
        JsonHelper jsonHelper = new JsonHelper();
        Object[][] data = jsonHelper.getJsonArrayObjectFileWithSpecifiedRow("src/test/resources/jsonData/ObjectArrayLoginData.json", "LoginFail", 3, 4);
        return data;
    }


}