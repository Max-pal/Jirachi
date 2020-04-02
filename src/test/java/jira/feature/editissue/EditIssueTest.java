package jira.feature.editissue;

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

public class EditIssueTest {

    WebDriver driver;
    String baseURL;
    String USERNAME = System.getenv("USERNAME");
    String PASSWORD = System.getenv("PASSWORD");
    WebElement usernameField;
    WebElement passwordField;
    WebElement loginButton;
    WebDriverWait wait;

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);

        baseURL = "https://jira.codecool.codecanvas.hu/";
        driver.get(baseURL);
        usernameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));
        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        loginButton.click();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    public void generalEditIssueTest() throws InterruptedException {

        baseURL = "https://jira.codecool.codecanvas.hu/browse/MTP-656";
        driver.get(baseURL);

        editSummary("Big issue");
        WebElement summaryFieldCheck = driver.findElement(By.id("summary-val"));
        Assertions.assertEquals("Big issue", summaryFieldCheck.getText());

        // clean-up:
        editSummary("Small issue");
//        WebElement summaryFieldCheck2 = driver.findElement(By.id("summary-val"));
//        Assertions.assertEquals("Small issue", summaryFieldCheck2.getText());
    }

    private void editSummary(String newSummary) throws InterruptedException {

        Thread.sleep(1000);

        WebElement editIssueButton = driver.findElement(By.id("edit-issue"));
        editIssueButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue-dialog")));

        WebElement summaryField = driver.findElement(By.id("summary"));
        summaryField.sendKeys(newSummary);

        WebElement updateIssueButton = driver.findElement(By.id("edit-issue-submit"));
//        WebElement updateIssueButton = driver.findElement(By.id("issue-edit-submit"));
        updateIssueButton.click();

        Thread.sleep(1000);
    }
}
