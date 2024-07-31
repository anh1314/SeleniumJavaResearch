package Example.testcases;

import com.google.gson.JsonObject;
import dataProvider.DataProviderFactory;
import dataProvider.DataProviderFactory_Json;
import org.testng.annotations.Test;
import utils.LogUtils;

import static org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.data;

public class DataProviderCombineJson {

    // ARRAY OBJECT file
    @Test(dataProvider = "DataJsonArrayObjectFile", dataProviderClass = DataProviderFactory_Json.class)
    public void testJsonArrayObjectFile(String ID, String Email, String Password){
        System.out.println("Get data with id " +ID);
        System.out.println("Email " +Email);
        System.out.println("Password " +Password);
    }

    @Test(dataProvider = "DataJsonArrayObjectFileWithSpecifiedRow", dataProviderClass = DataProviderFactory_Json.class)
    public void testJsonArrayObjectFileWithSpecifiedRow(String ID, String Email, String Password){
        System.out.println("Get data with id " +ID);
        System.out.println("Email " +Email);
        System.out.println("Password " +Password);
    }

    // OBJECT file
    @Test(dataProvider = "DataJsonObjectFile", dataProviderClass = DataProviderFactory_Json.class)
    public void JsonObjectFile(String url, String cccd, String name, String email){
        System.out.println("URL " +url);
        System.out.println("CCCD " +cccd);
        System.out.println("Name " +name);
        System.out.println("Email " +email);
    }

    @Test(dataProvider = "DataJsonObjectFileWithSpecifiedRow", dataProviderClass = DataProviderFactory_Json.class)
    public void testJsonObjectFileWithSpecifiedRow( String URL, String CCCD,  String Name){
        System.out.println("URL " +URL);
        System.out.println("CCCD " +CCCD);
        System.out.println("Name " +Name);
    }

    // NEST OBJECT file
    @Test(dataProvider = "DataJsonNestObjectFile", dataProviderClass = DataProviderFactory_Json.class)
    public void testJsonNestObjectFile(String  name, String age, String gender){
        System.out.println("Name " +name);
        System.out.println("Age " +age);
        System.out.println("Gender " +gender);
    }

    @Test(dataProvider = "DataJsonNestObjectFileWithSpecifiedRow", dataProviderClass = DataProviderFactory_Json.class)
    public void testJsonNestObjectFileWithSpecifiedRow(JsonObject data) {
        System.out.println("Name " + data.get("name").getAsString());
        System.out.println("Age " + data.get("age").getAsString());
        System.out.println("Gender " + data.get("gender").getAsString());
    }

    @Test(dataProvider = "DataJsonNestObjectFileWithSpecifiedKeys", dataProviderClass = DataProviderFactory_Json.class)
    public void testJsonNestObjectFileWithSpecifiedKeys(String  name, String age, String gender) {
        System.out.println("Name " + name);
        System.out.println("Age " + age);
        System.out.println("Gender " + gender);
    }

    @Test(dataProvider = "DataJsonNestObjectFileWithSpecifiedKey", dataProviderClass = DataProviderFactory_Json.class)
    public void testJsonNestObjectFileWithSpecifiedKey(String  name, String age, String gender) {
        System.out.println("Name " + name);
        System.out.println("Age " + age);
        System.out.println("Gender " + gender);
    }

}
