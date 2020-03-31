package jira.feature.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmptyCredentialsLogInTest {
    WebDriver driver;
    String baseURL;
    String USERNAME = System.getenv("USERNAME");
    String PASSWORD = System.getenv("PASSWORD");
    WebElement usernameField;
    WebElement passwordField;
    WebElement loginButton;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void LogInMainPage() {
        baseURL = "https://jira.codecool.codecanvas.hu/";
        driver.get(baseURL);
        loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"usernameerror\"]")));

    }

    @Test
    public void LogInLoginPage() {
        baseURL = "https://jira.codecool.codecanvas.hu/login.jsp";
        driver.get(baseURL);
        loginButton = driver.findElement(By.xpath("//*[@id=\"login-form-submit\"]"));
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]")));
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
