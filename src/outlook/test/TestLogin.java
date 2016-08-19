package outlook.test;

import org.junit.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.util.Map;


public class TestLogin extends LoginPage{

    @Before
    public void openBrowser() throws Exception{
        try {
        driver = new FirefoxDriver();
        wait10s = new WebDriverWait(driver, 10);
        driver.get("https://login.live.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoginWithValidEmail(){
        enterEmail(getEmail());
        enterPassword(getPassword());
        buttonSignIn().submit();
        checkAuthorization();
    }

    @Test
    public void loginWithInvalidCorrectPhone(){
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
    public void loginInvalidCorrectEmail(){
        enterEmail("some1here@host.com");
        enterPassword(getPassword());
        buttonSignIn().click();
    }

    @Test
    public void loginEmptyPassValidEmail() {
        enterEmail(getEmail());
        buttonSignIn().click();
        wait10s.until(ExpectedConditions.visibilityOf(passwordError()));
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
