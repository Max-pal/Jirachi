package jira.feature;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowseIssue {

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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);

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
    public void browseIssueTest() {
        baseURL = "https://jira.codecool.codecanvas.hu/browse/MTP-156";
        String issueID = null;
        String summary = null;
        driver.get(baseURL);

        if (!driver.findElements(By.id("key-val")).isEmpty()) {
            issueID = driver.findElement(By.id("key-val")).getText();
        }
        if (!driver.findElements(By.id("summary-val")).isEmpty()) {
            summary = driver.findElement(By.id("summary-val")).getText();
        }

        String finalIssueID = issueID;
        String finalSummary = summary;

        Assertions.assertAll("issueIdentificators",
                () -> assertEquals("MTP-156", finalIssueID),
                () -> assertEquals("Can u edit???", finalSummary)
        );
    }

    @Test
    public void browseIssueTestCoala() {

        baseURL = "https://jira.codecool.codecanvas.hu/browse/COALA-1";
        String issueID1 = null;
        driver.get(baseURL);
        if (!driver.findElements(By.id("key-val")).isEmpty()) {
            issueID1 = driver.findElement(By.id("key-val")).getText();
        }

        baseURL = "https://jira.codecool.codecanvas.hu/browse/COALA-2";
        String issueID2 = null;
        driver.get(baseURL);
        if (!driver.findElements(By.id("key-val")).isEmpty()) {
            issueID2 = driver.findElement(By.id("key-val")).getText();
        }

        baseURL = "https://jira.codecool.codecanvas.hu/browse/COALA-3";
        String issueID3 = null;
        driver.get(baseURL);
        if (!driver.findElements(By.id("key-val")).isEmpty()) {
            issueID3 = driver.findElement(By.id("key-val")).getText();
        }

        String finalIssueID1 = issueID1;
        String finalIssueID2 = issueID2;
        String finalIssueID3 = issueID3;

        Assertions.assertAll("issueIDs",
                () -> assertEquals("COALA-1", finalIssueID1),
                () -> assertEquals("COALA-2", finalIssueID2),
                () -> assertEquals("COALA-3", finalIssueID3)
        );
    }

    @Test
    public void browseIssueTestJeti() {

        baseURL = "https://jira.codecool.codecanvas.hu/browse/JETI-1";
        String issueID1 = null;
        driver.get(baseURL);
        if (!driver.findElements(By.id("key-val")).isEmpty()) {
            issueID1 = driver.findElement(By.id("key-val")).getText();
        }

        baseURL = "https://jira.codecool.codecanvas.hu/browse/JETI-2";
        String issueID2 = null;
        driver.get(baseURL);
        if (!driver.findElements(By.id("key-val")).isEmpty()) {
            issueID2 = driver.findElement(By.id("key-val")).getText();
        }

        baseURL = "https://jira.codecool.codecanvas.hu/browse/JETI-3";
        String issueID3 = null;
        driver.get(baseURL);
        if (!driver.findElements(By.id("key-val")).isEmpty()) {
            issueID3 = driver.findElement(By.id("key-val")).getText();
        }

        String finalIssueID1 = issueID1;
        String finalIssueID2 = issueID2;
        String finalIssueID3 = issueID3;

        Assertions.assertAll("issueIDs",
                () -> assertEquals("JETI-1", finalIssueID1),
                () -> assertEquals("JETI-2", finalIssueID2),
                () -> assertEquals("JETI-3", finalIssueID3)
        );
    }

    @Test
    public void browseIssueTestToucan() {

        baseURL = "https://jira.codecool.codecanvas.hu/browse/TOUCAN-1";
        String issueID1 = null;
        driver.get(baseURL);
        if (!driver.findElements(By.id("key-val")).isEmpty()) {
            issueID1 = driver.findElement(By.id("key-val")).getText();
        }

        baseURL = "https://jira.codecool.codecanvas.hu/browse/TOUCAN-2";
        String issueID2 = null;
        driver.get(baseURL);
        if (!driver.findElements(By.id("key-val")).isEmpty()) {
            issueID2 = driver.findElement(By.id("key-val")).getText();
        }

        baseURL = "https://jira.codecool.codecanvas.hu/browse/TOUCAN-3";
        String issueID3 = null;
        driver.get(baseURL);
        if (!driver.findElements(By.id("key-val")).isEmpty()) {
            issueID3 = driver.findElement(By.id("key-val")).getText();
        }

        String finalIssueID1 = issueID1;
        String finalIssueID2 = issueID2;
        String finalIssueID = issueID3;

        Assertions.assertAll("issueIDs",
                () -> assertEquals("TOUCAN-1", finalIssueID1),
                () -> assertEquals("TOUCAN-2", finalIssueID2),
                () -> assertEquals("TOUCAN-3", finalIssueID)
        );
    }
}
