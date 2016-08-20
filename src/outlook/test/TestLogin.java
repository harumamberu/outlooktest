package outlook.test;

import org.junit.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class TestLogin extends LoginPage{
    protected WebDriverWait wait10s;

    @Before
    public void openBrowser() throws Exception{
        try {
        driver = new FirefoxDriver();
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
    public void testLoginWithEmailAfterWrongPhoneEnter(){
        testLoginWithInvalidPhone();
        testLoginWithValidEmail();
    }

    @Test
    public void testLoginWithInvalidPhone(){
        enterPhone(getPhone());
        enterPassword(getPassword());
        buttonSignIn().click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ExpectedConditions.visibilityOf(phoneCountryList());
    }

    @Test
    public void testLoginInvalidPassValidMail(){
        enterPhone(getPhone());
        enterPassword("1234qwER");
        buttonSignIn().click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ExpectedConditions.visibilityOf(usernameError());
    }


    @Test
    public void testLoginUnregisteredEmail(){
        enterEmail("some1here@host.com");
        enterPassword(getPassword());
        buttonSignIn().click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ExpectedConditions.visibilityOf(usernameError());
    }

    @Test
    public void testLoginEmptyEmailEptyPass() {
        buttonSignIn().click();
        wait10s = new WebDriverWait(driver, 5);
        wait10s.until(ExpectedConditions.visibilityOf(passwordError()));
        wait10s = new WebDriverWait(driver, 5);
        wait10s.until(ExpectedConditions.visibilityOf(usernameError()));
    }

    @Test
    public void testLoginEmailWithoutAt(){
        enterEmail("some1herehost.com");
        enterPassword(getPassword());
        buttonSignIn().click();
        wait10s = new WebDriverWait(driver, 5);
        wait10s.until(ExpectedConditions.visibilityOf(usernameError()));
    }

    @Test
    public void testLoginEmailWithTwoAt(){
        enterEmail("some1here@@host.com");
        enterPassword(getPassword());
        buttonSignIn().click();
        wait10s = new WebDriverWait(driver, 5);
        wait10s.until(ExpectedConditions.visibilityOf(usernameError()));
    }

    @Test
    public void testLoginEmailWithoutHost(){
        enterEmail("some1here@.com");
        enterPassword(getPassword());
        buttonSignIn().click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ExpectedConditions.visibilityOf(usernameError());
    }

    @Test
    public void testLoginEmailWithoutDomain(){
        enterEmail("some1here@host");
        enterPassword(getPassword());
        buttonSignIn().click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ExpectedConditions.visibilityOf(usernameError());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
