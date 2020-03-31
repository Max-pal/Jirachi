package jira.feature.login;


        import org.junit.jupiter.api.*;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;

        import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UnauthorizedLoginAttempt {
    static WebDriver driver = new FirefoxDriver();
    static String baseURL = "https://jira.codecool.codecanvas.hu/";
    String USERNAME = System.getenv("USERNAME");
    String PASSWORD = System.getenv("PASSWORD");
    WebElement usernameField;
    WebElement passwordField;
    WebElement loginButton;
    WebDriverWait wait = new WebDriverWait(driver, 10);


    @BeforeAll
    static void setUp() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    @RepeatedTest(2)
    @Order(1)
    public void loginAttempt() {
        usernameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));
        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"usernameerror\"]")));
        Assertions.assertEquals(0, driver.findElements(By.xpath("//*[@id=\"captcha\"]")).size());
    }

    @Test
    @Order(2)
    public void captchaFor3rdAttempt(){
        usernameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));
        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"usernameerror\"]")));
        Assertions.assertEquals(1, driver.findElements(By.xpath("//*[@id=\"captcha\"]")).size());
    }

}