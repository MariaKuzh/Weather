import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class BottlesTest {
    @Test
    public void howWorkWithAlerts() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://www.99-bottles-of-beer.net/";

        driver.get(url);

        WebElement searchButtonGuestbook = driver.findElement(
                By.xpath("//div[@id = 'navigation']//a [@href = '/guestbookv2.html']")
        );

        searchButtonGuestbook.click();

        WebElement searchButtonSingGuestbook = driver.findElement(
                By.xpath("//ul[@id = 'submenu']//a[@href = './signv2.html']")
        );

        searchButtonSingGuestbook.click();

        //Создать экземпляр класса WebDriverWait - явное ожидание
        WebDriverWait wait = new WebDriverWait(driver, 3);

        WebElement searchButtonUrl = driver.findElement(
                By.xpath("//a[@style= 'border-bottom: 0'] " +
                        "[@href ='javascript: x()'][@onclick = 'DoPrompt(\"url\");']")
        );
        searchButtonUrl.click();

        //Искать по локатору, где вызывается alert и кликнуть
//        driver.findElement(By.cssSelector("//a[@onclick ='DoPrompt(\"url\");']")).click();

        //Создать переменную класса Alert, переключить активность драйвера на alert
        //Это классический способпереключения, но нельзя понять, появился alert или нет

//        Alert alertUrl = driver.switchTo().alert();

        //Кдасс ExpectedConditions - предоставляет подтверждение появления окна, связан с явным ожиданием
        //Данную конструкцию переключения на alert используют чаще
        //Переменная класса Alert присваивается окну только после подтверждения появления alert

        Alert alertUrl = wait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(3000);

        //Инициализировать переменную String, значение: текст в alert

        String actualTextFromPromptURL = alertUrl.getText();

        //Напечать в alert field текст
        alertUrl.sendKeys("mySite.my");

        //Нажать на кнопку OK
        alertUrl.accept();
        alertUrl.accept();
        Thread.sleep(3000);
    }

    @Test
    public void testTextFromUrlAndEmailPrompts() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://www.99-bottles-of-beer.net/";
        String expectedResult =
                "Enter the URL for the link you want to add.";

        driver.get(url);

        WebElement searchButtonGuestbook = driver.findElement(
                By.xpath("//div[@id = 'navigation']//a [@href = '/guestbookv2.html']")
        );
        searchButtonGuestbook.click();

        WebElement searchButtonSingGuestbook = driver.findElement(
                By.xpath("//ul[@id = 'submenu']//a[@href = './signv2.html']")
        );
        searchButtonSingGuestbook.click();

        WebDriverWait wait = new WebDriverWait(driver, 3);

        WebElement searchButtonUrl = driver.findElement(
                By.xpath("//a[@style= 'border-bottom: 0'] " +
                        "[@href ='javascript: x()'][@onclick = 'DoPrompt(\"url\");']")
        );
        searchButtonUrl.click();

        Alert alertUrl = wait.until(ExpectedConditions.alertIsPresent());

        String actualResult = alertUrl.getText();

        alertUrl.accept();
        alertUrl.accept();

        Assert.assertTrue(actualResult.length() != 0);
        Assert.assertEquals(actualResult, expectedResult);
    }

}
