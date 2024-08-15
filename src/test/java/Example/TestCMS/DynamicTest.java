package Example.TestCMS;

import Example.TestCMS.pages.LoginPage;
import common.BaseTest;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class DynamicTest  extends BaseTest {
    LoginPage loginPage = new LoginPage();
    String dynamicMenuProduct = "//span[normalize-space()='%s']";

    private By menuProduct = By.xpath("//span[normalize-space()='Products']");
    private By selectCategory = By.xpath("//select[@id='category_id']");
    private By droplistBrand = By.xpath("//button[@title='Select Brand']");
    private By searchBrand = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");




    @Test
    public void testCMS(){
        loginPage.loginCMS();
        WebUI.clickElement(menuProduct);
        WebUI.sleep(2);
        WebUI.clickElement(By.xpath(String.format(dynamicMenuProduct, "Add New Product")));

        //select static dropdown
        WebUI.selectStaticDropdown(selectCategory, "aka");

        WebUI.sleep(6);
    }
}
