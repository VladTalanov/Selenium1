package practiceWithSelenium.sm2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    private static String login = "GB20230513b3712";

    public static String getLogin() {
        return login;
    }
    public static String getPassword() {
        return password;
    }
    private static String password = "b6f2300019";

    @BeforeAll
    static void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized"); // режим полного экрана
        chromeOptions.addArguments("incognito");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
