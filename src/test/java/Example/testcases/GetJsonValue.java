package Example.testcases;

import helpers.JsonHelper;
import org.testng.annotations.Test;

public class GetJsonValue {
    JsonHelper jsonHelper = new JsonHelper();

    @Test
    public void getObjectFile(){
        jsonHelper.setJsonFile("src/test/resources/jsonData/Object.json");
        System.out.println("URL " +jsonHelper.getJsonValue("url"));
        System.out.println("Name " +jsonHelper.getJsonValue("name"));
    }

    @Test
    public void getObjectArrayFile(){
        jsonHelper.setJsonFile("src/test/resources/jsonData/ObjectArray.json");
        System.out.println("Email " +jsonHelper.getJsonValueObjectArray(2, "email"));
        System.out.println("Password " +jsonHelper.getJsonValueObjectArray(2, "password"));
    }

    @Test
    public void getNestObjectFile(){
        jsonHelper.setJsonFile("src/test/resources/jsonData/NestObject.json");
        System.out.println("Name " +jsonHelper.getJsonValueNestObject("nhu", "name"));
        System.out.println("Age " +jsonHelper.getJsonValueNestObject("nhu", "age"));
    }

}
