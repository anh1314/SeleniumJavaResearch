package Example.DynamicXpath;

import Example.TestCMS.pages.DashboardPage;
import Example.TestCMS.pages.LoginPage;
import common.BaseTest;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class DemoDynamicXpath extends BaseTest {
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    @Test
    public void testDashboard() {
        loginPage.loginCMS();
        dashboardPage.clickMenuProducts();

//         %d được sử dụng để chèn một số nguyên (integer) vào chuỗi.
//        %s được sử dụng để chèn một chuỗi ký tự (string) vào trong chuỗi.

        //Index
        String liXpathIndex = "//li[@class='aiz-side-nav-item'][%d]";
        int menuIndex = 2;
        String liXPath = String.format(liXpathIndex, menuIndex);
        WebUI.clickElement(By.xpath(liXPath));
//        WebUI.clickElement(By.xpath(String.format(liXpathIndex, 2)));

        // Text
        String liXpathText = "//li[@class='aiz-side-nav-item']//span[text()='%s']";
//        String menuText = "Add New Product";
        String liXpath2 = String.format(liXpathText, "Add New Product");
        WebUI.clickElement(By.xpath(liXpath2));

    }


}
