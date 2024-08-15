package Example.TestCMS.testcases;

import Example.TestCMS.pages.LoginPage;
import common.BaseTest;
import dataProviders.DataProviderCMS;
import dataProviders.DataProviderJson;
import org.testng.annotations.Test;
import utils.LogUtils;

public class LoginTest extends BaseTest {
      LoginPage loginPage = new LoginPage();

      @Test(invocationCount = 2, dataProvider = "Data Login Fail", dataProviderClass = DataProviderCMS.class)
      public void testLoginFail(String Case, String Email, String Password){
            LogUtils.info("Login with " +Case);
            loginPage.loginCMS(Email, Password);
      }

      @Test(dataProvider = "Data Login Null Email/Password", dataProviderClass = DataProviderCMS.class)
      public void testLoginFail2(String Case, String Email, String Password){
            LogUtils.info("Login with " +Case);
            loginPage.loginCMS(Email, Password);
      }

}
