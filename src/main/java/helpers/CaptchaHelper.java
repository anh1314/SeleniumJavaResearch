package helpers;

import drivers.DriverManager;
import keywords.WebUI;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import utils.LogUtils;

import java.io.File;
import java.io.IOException;

public class CaptchaHelper {
      public String readCaptcha(By captchaLocator, By captchaReload) {
            String captchaText = "";
            int reset = 0;
            try {
                  while ((captchaText.isEmpty() || captchaText.isBlank()) && reset < 5) {
                        reset++;
                        WebElement captchaImage = DriverManager.getDriver().findElement(captchaLocator);
                        // Capture the CAPTCHA image
                        File srcFile = captchaImage.getScreenshotAs(OutputType.FILE);
                        File destFile = new File("captcha.png");
                        FileHandler.copy(srcFile, destFile);

                        // Initialize Tesseract
                        Tesseract tesseract = new Tesseract();
                        tesseract.setDatapath(PropertiesHelper.getValue("CAPTCHA_PATH"));
                        tesseract.setLanguage("eng");
                        WebUI.sleep(2);

                        try {
                              // Read CAPTCHA
                              captchaText = tesseract.doOCR(destFile).trim();  // Trim whitespace
                              LogUtils.info("CAPTCHA Text: " + captchaText);
                        } catch (TesseractException e) {
                              e.printStackTrace();
                        }

                        if ((captchaText.isEmpty() || captchaText.isBlank()) && reset < 5) {
                              DriverManager.getDriver().findElement(captchaReload).click();
                              LogUtils.info("CAPTCHA is empty. Reloading the captcha...");
                              WebUI.waitForPageLoaded();
                        }
                        destFile.delete(); // Delete the temporary CAPTCHA file
                  }
            } catch (IOException e) {
                  e.printStackTrace();
            }

            if (reset == 5 && (captchaText.isEmpty() || captchaText.isBlank())) {
                  LogUtils.error("Failed to read CAPTCHA after 5 attempts.");
            }
            return captchaText;
      }


}
