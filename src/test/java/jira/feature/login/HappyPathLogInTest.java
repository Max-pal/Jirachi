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

public class HappyPathLogInTest {
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
        usernameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));
        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        loginButton.click();
    }

    @Test
    public void LogInLoginPage() {
        baseURL = "https://jira.codecool.codecanvas.hu/login.jsp";
        driver.get(baseURL);
        usernameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        loginButton = driver.findElement(By.xpath("//*[@id=\"login-form-submit\"]"));
        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        loginButton.click();

    }

    @AfterEach
    public void tearDown() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span/img")));
        WebElement profilepic = driver.findElement(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span/img"));
        String check = profilepic.getAttribute("alt");
        assertTrue(check.toLowerCase().contains(USERNAME));
        driver.close();
    }
//    ajs-layer-placeholder
//            //h4/a[contains(text(),'SAP M')]"
//            "//*[@class,'ajs-layer-placeholder']/following-sibling::img"
//    //input[contains(@type,'radio') and contains(@name,'gender')]
//            //*[@class,'ajs-layer-placeholder']//a[@class,'icon']
//            // *[contains(@class,'ajs-layer-placeholder') and contains(@class,'aui-list-item-link')]
//    <div class="ajs-layer-placeholder"><div class="ajs-layer box-shadow" aria-hidden="true" style="width: 248px; position: fixed; left: 150px; top: 305.5px; max-height: 388.5px; display: none;"><div class="aui-list" id="issuetype-suggestions" tabindex="-1" role="listbox" style="display: block;"><div class="aui-list-scroll" tabindex="-1" role="presentation"><ul class="aui-last"><li class="aui-list-item aui-list-item-li-improvement" role="option" id="improvement-140"><a class="aui-list-item-link aui-iconised-link" role="presentation" href="#"><img class="icon" alt="" src="https://jira.codecool.codecanvas.hu/secure/viewavatar?size=xsmall&amp;avatarId=10310&amp;avatarType=issuetype">Improvement</a></li><li class="aui-list-item aui-list-item-li-new-feature" role="option" id="new-feature-141"><a class="aui-list-item-link aui-iconised-link" role="presentation" href="#"><img class="icon" alt="" src="https://jira.codecool.codecanvas.hu/secure/viewavatar?size=xsmall&amp;avatarId=10311&amp;avatarType=issuetype">New Feature</a></li><li class="aui-list-item aui-list-item-li-bug active" role="option" id="bug-142"><a class="aui-list-item-link aui-iconised-link" role="presentation" href="#"><img class="icon" alt="" src="https://jira.codecool.codecanvas.hu/secure/viewavatar?size=xsmall&amp;avatarId=10303&amp;avatarType=issuetype">Bug</a></li><li class="aui-list-item aui-list-item-li-epic" role="option" id="epic-143"><a class="aui-list-item-link aui-iconised-link" role="presentation" href="#"><img class="icon" alt="" src="https://jira.codecool.codecanvas.hu/images/icons/issuetypes/epic.svg">Epic</a></li></ul></div></div></div></div>
//    //*[contains(@class,'ajs-layer-placeholder') and contains(@class,'icon')]

}
