package selenium.automated.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.awt.image.ImageWatched;
import sun.font.TrueTypeFont;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DevToTests {
    WebDriver driver; //inicjalizacja drivera - pustej przeglądarki
    String url = "https://dev.to/";  //zapisujemy w zmiennej url, wartość linku, który ma zostać otworzony w przeglądarce
    public void HighlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
    }
    @Before
    public void SetUp() { //pre-requirements - warunki początkowe
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ANIA\\Desktop\\mój kurs\\chromedriver_win32\\chromedriver.exe"); //ścieżka do chromedriver
        driver = new ChromeDriver();
        driver.get(url); //otworzenie linku w przeglądarce
        driver.manage().window().maximize();
    }
    @Test
    public void OpenDevTo() {
        //na ten moment, mamy otworzną stronę dev.to - żeby sprawdzić, czy faktycznie na niej jesteśmy,
        // chcemy porównać url ze zmiennej wcześniejszej do obecnego url z przeglądarki
        String currentUrl = driver.getCurrentUrl(); //wyciągamy obecny url z przeglądarki i przypisujemy go do zmiennej currentUrl
        //assertTrue - sprawdza poprawność warunku url.equals(currentUrl) - czy url ze zmiennej url jest taki sam jak w zmiennej currentUrl
        //jeśli nie - wypisuje message "The current url isn't dev.to", i ustawia test na fail
        assertTrue("The current url isn't dev.to", url.equals(currentUrl));
    }
    @Test
    public void GoToWeeAndOpenTheFirstPost() {
        WebElement week = driver.findElement(By.cssSelector("#on-page-nav-controls > div > nav > a:nth-child(2)")); //znalezienie elementu week na stronie
        HighlightElement(week); // podswietlenie elementu week
        week.click(); // klikniecie elementu week
        WebDriverWait wait = new WebDriverWait(driver, 5); //zainicjalizowanie Explicit Wait
        wait.until(ExpectedConditions.urlToBe("https://dev.to/top/week")); //poczekaj aż url będzie : https://dev.to/top/week
        //wait.until(ExpectedConditions.attributeContains(week,"class","item--current"));
        WebElement firstPostOnWeek = driver.findElement(By.className("crayons-story__body")); //odnalezienie pierwszego posta
        HighlightElement(firstPostOnWeek); //podswietlenie 1 postu
        WebElement firstPostTitle = driver.findElement(By.cssSelector(".crayons-story__title > a")); //znajdź element za pomocą cssSelector -  będzie to nazwa 1 posta
        HighlightElement(firstPostTitle);
        String linkToFirstPost = firstPostTitle.getAttribute("href"); //wyciagnij z nazwy pierwszego posta link do strony
        firstPostOnWeek.click(); //klikniecie 1 postu
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("crayons-article__header__meta")));
        String currentUrl = driver.getCurrentUrl(); //wyciagnij obecny link
        //sprawdz czy link do postu jest taki sam jak obecny url
        assertEquals("url isn't the same as link(href) value", linkToFirstPost, currentUrl);
    }


}