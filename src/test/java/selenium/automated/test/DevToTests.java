package selenium.automated.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.font.TrueTypeFont;

import static org.junit.Assert.assertTrue;


public class DevToTests {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ANIA\\Desktop\\mój kurs\\chromedriver_win32.exe");
        driver = new ChromeDriver();

    }
    @Test
    public void OpenDevTo() {
        String url = "https://dev.to/";
        driver.get(url);
        String currentUrl = driver.getCurrentUrl();
        assertTrue("The current url isn;t to", url.equals(currentUrl));
    }

}
