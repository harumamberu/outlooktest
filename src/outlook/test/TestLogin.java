package outlook.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.Map;


public class TestLogin extends LoginPage{
    @Before
    public void openBrowser() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities().firefox();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver.get("https://login.live.com");
    }

    @Test
    public void testLoginWithValidEmail(){
        enterEmail(getEmail());
        enterPassword(getPassword());
        buttonSignIn().click();
    }

    @Test
    public void loginWithValidPhone(){
        enterPhone(getPhone());
        enterPassword(getPassword());
        buttonSignIn().click();
    }

    @Test
    public void loginInvalidPassValidMail(){
        enterPhone(getPhone());
        enterPassword("1234qwER");
        buttonSignIn().click();
    }


    @Test
    public void loginInvalidEmail(){
        enterEmail("some1here@host.com");
        enterPassword(getPassword());
        buttonSignIn().click();
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }
}
