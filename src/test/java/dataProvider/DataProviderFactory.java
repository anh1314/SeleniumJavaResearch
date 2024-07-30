package dataProvider;

import helpers.ExcelHelper;
import helpers.JsonHelper;
import helpers.PropertiesHelper;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Arrays;

public class DataProviderFactory {

      @DataProvider(name = "DataLoginFail", parallel = false)
      public Object[][] dataLogin(){
            ExcelHelper excelHelper = new ExcelHelper();
            Object[][] data = excelHelper.getExcelData("src/test/resources/testData/dataLogin.xlsx", "LoginFail");
            return data;
      }


      @DataProvider(name = "Data provider Login excel hashtable")
      public Object[][] dataLoginHRMFromExcelHashtable() {
            ExcelHelper excelHelper = new ExcelHelper();
            Object[][] data = excelHelper.getDataHashTable(  "src/test/resources/testData/dataLogin.xlsx", "LoginFail", 1, 4);
//            System.out.println("Login Data from Excel: " + data);
            return data;
      }


}