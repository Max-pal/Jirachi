package jira.feature.login;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidLogInTest {
    WebDriver driver;
    String baseURL;
    String USERNAME = System.getenv("USERNAME");
    String PASSWORD = System.getenv("PASSWORD");
    WebElement usernameField;
    WebElement passwordField;
    WebElement loginButton;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        baseURL = "https://jira.codecool.codecanvas.hu/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseURL);
        usernameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));
    }

    @Test
    public void happyPath() {
        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        loginButton.click();
    }


    @AfterEach
    public void tearDown() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span/img")));
        WebElement profilepic = driver.findElement(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span/img"));
        String check = profilepic.getAttribute("alt");
        assertTrue(check.toLowerCase().contains(USERNAME));
        driver.close();
    }
}
