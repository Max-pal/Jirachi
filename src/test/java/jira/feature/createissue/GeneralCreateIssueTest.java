package jira.feature.createissue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneralCreateIssueTest {
    static WebDriver driver = new FirefoxDriver();
    static String baseURL = "https://jira.codecool.codecanvas.hu/";
    static String USERNAME = System.getenv("USERNAME");
    static String PASSWORD = System.getenv("PASSWORD");
    static WebDriverWait wait = new WebDriverWait(driver, 10);

    @BeforeAll
    public static void setUp(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseURL);
        WebElement usernameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));
        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span/img")));
        WebElement profilepic = driver.findElement(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span/img"));
        String check = profilepic.getAttribute("alt");
//        Check if correct user is logged in
        assertTrue(check.toLowerCase().contains(USERNAME));
    }

    @Test
    public void createIssueTest() {
        WebElement createIssueButton = driver.findElement(By.xpath("//*[@id=\"create_link\"]"));
        createIssueButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"create-issue-dialog\"]")));
        //        driver.findElement(By.xpath("//*[@id=\"project-field\"]")).sendKeys("Main Testing Project (MTP)");
//        Select Task
        driver.findElement(By.xpath("//*[@id=\"issuetype-field\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"issuetype-field\"]")).sendKeys("Task");
//        Enter "Test for selenium" in summary
        driver.findElement(By.id("summary")).click();
        driver.findElement(By.id("summary")).sendKeys("Test from selenium");
//        Click on create issue button
        driver.findElement(By.xpath("//*[@id=\"create-issue-submit\"]")).click();
//        Wait and click to the  issue pop-up
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'issue-created-key')]")));
        driver.findElement(By.xpath("//*[contains(@class, 'issue-created-key')]")).click();
//        Check if the created project is the one.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"summary-val\"]")));
        assertTrue(driver.findElement(By.xpath("//*[@id=\"summary-val\"]")).getText().contains("Test from selenium"));
    }
}
