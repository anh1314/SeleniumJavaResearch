package Example.HandleCaptcha;

import helpers.CaptchaHelper;
import helpers.JsonHelper;
import helpers.PropertiesHelper;
import keywords.WebUI;
import org.openqa.selenium.By;

public class page {
    private By inputCCCD = By.xpath("//input[@id='id_cccd']");
    private By inputName = By.xpath("//input[@id='id_name']");
    private By inputEmail = By.xpath("//input[@id='id_email']");
    private By inputPhone = By.xpath("//input[@id='id_phone']");
    private By selectProvince = By.xpath("//select[@id='id_province']");
    private By selectDistrict = By.xpath("//select[@id='id_district']");
    private By selectWard = By.xpath("//select[@id='id_ward']");
    private By inputStreet = By.xpath("//input[@id='id_street']");
    private By captcha = By.xpath("//canvas[@id='captcha']");
    private By reloadCaptcha = By.xpath("//a[@onclick='createCaptcha()']/span");
    private By inputCaptcha = By.xpath("//input[@id='cpatchaTextBox']");

    private By province = By.xpath("(//span[@role='combobox'])[1]");
    private By search = By.xpath("//input[@aria-label='Search']");
    private By option = By.xpath("//option[@value='42']");

    CaptchaHelper captchaHelper = new CaptchaHelper();
    JsonHelper jsonHelper = new JsonHelper();

    public void handleCaptcha() {
        jsonHelper.setJsonFile(PropertiesHelper.getValue("JSON_PATH"));
        WebUI.sendKeys(inputCCCD, jsonHelper.getJsonValue("cccd"));
        WebUI.sendKeys(inputName, jsonHelper.getJsonValue("name"));
        WebUI.sendKeys(inputEmail, jsonHelper.getJsonValue("email"));
        WebUI.sendKeys(inputPhone, jsonHelper.getJsonValue("phone"));
        WebUI.sleep(3);

        WebUI.selectStaticDropdown(selectProvince, jsonHelper.getJsonValue("province"));
//            WebUI.clickElement(province);
//            WebUI.selectValueFromDynamicDropdown(search, option,"Hồ Chí Minh");

        WebUI.selectStaticDropdown(selectDistrict, jsonHelper.getJsonValue("district"));
        WebUI.selectStaticDropdown(selectWard, jsonHelper.getJsonValue("ward"));
        WebUI.sendKeys(inputStreet, jsonHelper.getJsonValue("street"));
        WebUI.sendKeys(inputCaptcha, captchaHelper.readCaptcha(captcha, reloadCaptcha));
    }
}




