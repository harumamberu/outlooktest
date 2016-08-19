package outlook.test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    protected WebDriver driver;
    private String email;
    private String password;
    private int phone;

    public LoginPage(){
        this.email = "test.two.dots@outlook.com";
        this.password = "?><zaqXSW12";
        this.phone = 1111112;
    }

    public void enterEmail(String email){
        fieldEmailOrPhone().sendKeys(email);
    }

    public void enterPassword(String password){
        fieldPassword().sendKeys(password);
    }

    public void enterPhone(int phone){
        fieldEmailOrPhone().sendKeys("" + phone);
    }
//-----page-objects----------
    public WebElement fieldEmailOrPhone(){
        WebElement element = driver.findElement(By.name("loginfmt"));
        return element;
    }

    public WebElement fieldPassword(){
        WebElement element = driver.findElement(By.name("passwd"));
        return element;
    }

    public WebElement buttonSignIn(){
        WebElement element = driver.findElement(By.id("idSIButton9"));
        return element;
    }



//----------------------------------------getters-setters-------------------------------------
    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
