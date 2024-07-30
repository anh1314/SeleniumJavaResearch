package Example.testcases;

import dataProvider.DataProviderFactory;
import dataProvider.DataProviderFactory_Json;
import org.testng.annotations.Test;
import utils.LogUtils;

public class DataProviderCombineJson {

    @Test(dataProvider = "DataJsonArrayObjectFile", dataProviderClass = DataProviderFactory_Json.class)
    public void testJsonArrayObjectFile(String ID, String Email, String Password){
        System.out.println("Get data with id " +ID);
        System.out.println("Email " +Email);
        System.out.println("Password " +Password);
    }

    @Test(dataProvider = "DataJsonArrayObjectFileWithSpecifiedRow", dataProviderClass = DataProviderFactory_Json.class)
    public void testJson2(String ID, String Email, String Password){
        System.out.println("Get data with id " +ID);
        System.out.println("Email " +Email);
        System.out.println("Password " +Password);
    }

    @Test(dataProvider = "DataJsonObjectFile", dataProviderClass = DataProviderFactory_Json.class)
    public void testJson3(String url, String cccd, String name, String email){
        System.out.println("URL " +url);
        System.out.println("CCCD " +cccd);
        System.out.println("Name " +name);
        System.out.println("Email " +email);
    }

    @Test(dataProvider = "DataJsonObjectFileWithSpecifiedRow", dataProviderClass = DataProviderFactory_Json.class)
    public void testJson4( String URL, String CCCD, String Name){
        System.out.println(URL);
        System.out.println(CCCD);
        System.out.println(Name);
    }

}
