package Example.HandleCapcha;

import common.BaseTest;
import helpers.JsonHelper;
import helpers.PropertiesHelper;
import keywords.WebUI;
import org.testng.annotations.Test;

public class testcase extends BaseTest {
 Example.HandleCapcha.page page = new page();
 JsonHelper jsonHelper = new JsonHelper();

 @Test
      public void testHandleCaptcha() {
       jsonHelper.setJsonFile(PropertiesHelper.getValue("JSON_PATH"));
       WebUI.openURL(jsonHelper.getJsonValue("url"));
       page.handleCaptcha();
       WebUI.sleep(5);

      }
}

