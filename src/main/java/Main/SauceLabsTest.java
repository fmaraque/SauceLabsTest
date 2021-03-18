package Main;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceLabsTest {

    private static RemoteWebDriver driver;

    public static void main(String[] args) {

        String SAUCE_USERNAME = args[0];
        String SAUCE_USER_KEY = args[1];

        // SETUP
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("appiumVersion", "1.17.1");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "12.2");
        capabilities.setCapability("deviceName", "iPhone 8");
        capabilities.setCapability("browserName", "safari");
        capabilities.setCapability("name", "SauceLabs Tunnel Test");
        capabilities.setCapability("idleTimeout", "90");
        capabilities.setCapability("newCommandTimeout", "90");
        capabilities.setCapability("tunnelIdentifier", "92192893f23e440087e2f9e474b1e4c4");
        String url = "https://" + SAUCE_USERNAME + ":" + SAUCE_USER_KEY + "@ondemand.eu-central-1.saucelabs.com:443" + "/wd/hub";
        System.out.println("[DEBUG][URL]: " + url);

        try {
            driver = new RemoteWebDriver(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // TEST
        driver.navigate().to("https://axn-testrail01-p.axadmin.net/index.php?/auth/login/");
        assertTrue(driver.findElement(By.xpath("//h1[.='TestRail QA']")).isDisplayed());

        // END
        if (driver != null) {
            driver.quit();
        }

    }
}
