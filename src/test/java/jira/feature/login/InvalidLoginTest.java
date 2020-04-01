package jira.feature.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvalidLoginTest {

    WebDriver driver;
    String baseURL;
    String USERNAME;
    String PASSWORD;
    WebElement usernameField;
    WebElement passwordField;
    WebElement loginButton;
    WebElement loginLink;


    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        baseURL = "https://jira.codecool.codecanvas.hu/";
        USERNAME = System.getenv("USERNAME");
        driver.get(baseURL);
        usernameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    public void loginAttemptWithEmptyCredentials() {
        loginButton.click();
        // check the existence of the error message?
//        "#usernameerror > p:nth-child(2)"
        loginLink = driver.findElement(By.cssSelector(".login-link"));
        assertNotNull(loginLink);
    }

    @Test
    public void loginAttemptWithWrongPassword() {
        PASSWORD = "wrongPass";

        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        loginButton.click();
        loginLink = driver.findElement(By.cssSelector(".login-link"));
        assertNotNull(loginLink);
        resetEnvironment();
    }

    public void resetEnvironment() {
        PASSWORD = System.getenv("PASSWORD");
        usernameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));

        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span/img")));
        WebElement profilepic = driver.findElement(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span/img"));

        profilepic.click();
        WebElement logoutButton = driver.findElement(By.id("log_out"));
        logoutButton.click();
    }
}