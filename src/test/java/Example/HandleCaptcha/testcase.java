package Example.HandleCaptcha;

import common.BaseTest;
import helpers.JsonHelper;
import helpers.PropertiesHelper;
import keywords.WebUI;
import org.testng.annotations.Test;

public class testcase extends BaseTest {
 Example.HandleCaptcha.page page = new page();
 JsonHelper jsonHelper = new JsonHelper();

 @Test
      public void testHandleCaptcha() {
       jsonHelper.setJsonFile(PropertiesHelper.getValue("JSON_PATH"));
       WebUI.openURL(jsonHelper.getJsonValue("url"));
       page.handleCaptcha();
       WebUI.sleep(5);

      }
}

