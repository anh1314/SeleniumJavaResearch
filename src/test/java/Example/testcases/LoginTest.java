package Example.testcases;

import Example.pages.LoginPage;
import common.BaseTest;
import dataProvider.DataProviderFactory_Json;
import keywords.WebUI;
import org.testng.annotations.Test;
import utils.LogUtils;

public class LoginTest extends BaseTest {
      LoginPage loginPage = new LoginPage();

      @Test
      public void testLoginCMS(){
            loginPage.loginCMS();
            WebUI.sleep(4);
      }

      @Test(dataProvider = "Data Login Fail", dataProviderClass = DataProviderFactory_Json.class)
      public void testLoginFail(String Case, String Email, String Password){
            LogUtils.info("Login with " +Case);
            loginPage.loginCMS(Email, Password);
      }

      @Test(dataProvider = "Data Login Null Email/Password", dataProviderClass = DataProviderFactory_Json.class)
      public void testLoginFail2(String Case, String Email, String Password){
            LogUtils.info("Login with " +Case);
            loginPage.loginCMS(Email, Password);
      }

}
