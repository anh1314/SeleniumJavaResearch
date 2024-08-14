package Example.TestCMS.testcases;

import Example.TestCMS.pages.LoginPage;
import common.BaseTest;
import dataProviders.DataProviderCMS;
import org.testng.annotations.Test;
import utils.LogUtils;

import java.util.Hashtable;

//Không test được case null email/password
public class TestDataProvider extends BaseTest {
    LoginPage loginPage = new LoginPage();

    @Test(dataProvider = "DataLoginFail", dataProviderClass = DataProviderCMS.class)
    public void testLoginFail(String Case, String Email, String Password){
        LogUtils.info("Login with " +Case);
        loginPage.loginCMS(Email, Password);
    }

    @Test(dataProvider = "Data provider Login excel hashtable", dataProviderClass = DataProviderCMS.class)
    public void testLoginFail(Hashtable < String, String > data){
        LogUtils.info("Login with " +data.get("Case"));
        loginPage.loginCMS(data.get("Email"), data.get("Password"));
    }



}
