package Example.TestCMS.testcases;

import Example.TestCMS.pages.LoginPage;
import common.BaseTest;
import dataProviders.DataProviderJson;
import org.testng.annotations.Test;
import utils.LogUtils;

public class LoginTest extends BaseTest {
      LoginPage loginPage = new LoginPage();

      @Test(dataProvider = "Data Login Fail", dataProviderClass = DataProviderJson.class)
      public void testLoginFail(String Case, String Email, String Password){
            LogUtils.info("Login with " +Case);
            loginPage.loginCMS(Email, Password);
      }

      @Test(dataProvider = "Data Login Null Email/Password", dataProviderClass = DataProviderJson.class)
      public void testLoginFail2(String Case, String Email, String Password){
            LogUtils.info("Login with " +Case);
            loginPage.loginCMS(Email, Password);
      }

}
