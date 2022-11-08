import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MariaKuzhTest {

    //    TC_1_1  - Тест кейс:
    //    //1. Открыть страницу https://openweathermap.org/
    //    //2. Набрать в строке поиска город Paris
    //    //3. Нажать пункт меню Search
    //    //4. Из выпадающего списка выбрать Paris, FR
    //    //5. Подтвердить, что заголовок изменился на "Paris, FR"


    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        Thread.sleep(5000);

        searchCityField.sendKeys(cityName);
        Thread.sleep(5000);
        WebElement searchButton = driver.findElement(
                By.xpath("//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(5000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        Thread.sleep(5000);

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        String actualResul = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResul, expectedResult);

        driver.quit(); // закрыть сессию
        //driver.close(); // закрыть сессию
    }


//    TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу
// со ссылкой https://openweathermap.org/guide
// и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap

    @Test

    public void testTagText_WhenUseGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        driver.get(url); // открыть ссылку
        Thread.sleep(3000);

        WebElement GuideElementInMenu = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']")
        ); // найти элемент на странице
        GuideElementInMenu.click(); // нажать на элемент на странице
        Thread.sleep(5000); // подождать 5 сек
        String actualResulTitle = driver.getTitle(); // найти заголовок страницы
        String actualResulUrl = driver.getCurrentUrl(); // найти адрес страницы

        Assert.assertEquals(actualResulTitle, expectedResultTitle);
        Thread.sleep(10000);
        Assert.assertEquals(actualResulUrl, expectedResultUrl);
        Thread.sleep(5000);
        driver.quit(); // закрыть сессию
    }

    /*
    TC_11_02
1.  Открыть базовую ссылку
2.  Нажать на единицы измерения Imperial: °F, mph
3.  Подтвердить, что температура для города показана в Фарингейтах  */


    @Test
    public void testTemperatureF() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";
        String fTempSymbol = "°F";

        driver.get(url);
        Thread.sleep(3000);
        driver.manage().window().maximize(); //раскрыть полное окно
        Thread.sleep(5000);

        WebElement elementImperialF = driver.findElement(
                By.xpath("//div[text() = 'Imperial: °F, mph']")
        );

//        WebElement F2 = driver.findElement(
//                By.xpath("//div[@class = 'switch-container']/div[@class = 'option']/following-siblinh::div")
//        );
        elementImperialF.click();
        //F2.click();
        Thread.sleep(5000);
//
        WebElement temp = driver.findElement(
                By.xpath("//div[@class = 'current-temp']/span")
        );

        String tempF = temp.getText();
        String actualResul = tempF.substring((tempF.length()-2)); //метод, кт выводить часть текста 50°F

        Assert.assertEquals(actualResul, expectedResult);
        Assert.assertTrue(tempF.contains(fTempSymbol)); //содержит ли строка символ
        driver.quit(); // закрыть сессию
    }

    /*
    TC_11_03
1.  Открыть базовую ссылку
2. Подтвердить, что внизу страницы есть панель с текстом
“We use cookies which are essential for the site to work.
We also use non-essential cookies to help us improve our services.
 Any data collected is anonymised.
 You can allow all cookies or manage them individually.”
3. Подтвердить, что на панели внизу страницы есть 2 кнопки
“Allow all” и “Manage cookies”*/

    @Test
    public void messageCookies() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultFindText = "We use cookies which are essential " +
                "for the site to work. We also use non-essential cookies " +
                "to help us improve our services. Any data collected is a" +
                "nonymised. You can allow all cookies or manage them individually.";
        String expectedResultAllowAll = "Allow all";
        String expectedResultManageCookies = "Manage cookies";

        driver.get(url);
        Thread.sleep(3000);
        driver.manage().window().maximize(); //раскрыть полное окно
        Thread.sleep(5000);

        Assert.assertTrue(driver.findElement( //проверяет наличие и отображение элемента
                By.className("stick-footer-panel__container")).isDisplayed());


        WebElement longText = driver.findElement(
                By.xpath("//p[@class = 'stick-footer-panel__description']")
        );
        Thread.sleep(5000);
        String actualResultFindText = longText.getText();

        WebElement buttonAllowAll = driver.findElement(
                By.xpath("//button[@class = 'stick-footer-panel__link']")
        );
        Thread.sleep(5000);
        String actualResultAllowAll = buttonAllowAll.getText();

        WebElement buttonManageCookies = driver.findElement(
                By.xpath("//a[@href = '/cookies-settings']")
        );
        Thread.sleep(5000);
        String actualResultManageCookies = buttonManageCookies.getText();

        Assert.assertEquals(actualResultFindText, expectedResultFindText);
        Assert.assertEquals(actualResultAllowAll, expectedResultAllowAll);
        Assert.assertEquals(actualResultManageCookies, expectedResultManageCookies);
        Assert.assertTrue(buttonAllowAll.isDisplayed()); //проверяет наличие и отображение элемента
        Assert.assertTrue(buttonManageCookies.isDisplayed()); //проверяет наличие и отображение элемента

        driver.quit(); // закрыть сессию
    }

    /*
    TC_11_04
1.  Открыть базовую ссылку
2.  Подтвердить, что в меню Support есть 3 подменю
с названиями “FAQ”, “How to start” и “Ask a question” */
    @Test
    public void threeSubMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultFAQ = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskaQuestion = "Ask a question";

        driver.get(url); //открыть ссылку
        Thread.sleep(3000);
        driver.manage().window().maximize(); //раскрыть полное окно
        Thread.sleep(5000);

        WebElement buttonSupport = driver.findElement(   //найти Support
                By.xpath("//div[@id = 'support-dropdown']")
        );
        Thread.sleep(2000);
        buttonSupport.click();  //нажать Support
        Thread.sleep(2000);


        Assert.assertEquals(driver.findElements( // найти и сравнить количество элементов
                By.xpath("//ul[@id = 'support-dropdown-menu']/li")).size(),3);

        WebElement buttonFAQ = driver.findElement(   //найти FAQ
                By.xpath("//div[@id= 'desktop-menu']//a[@href='/faq']")
        );
        Thread.sleep(2000);
        String actualResultFAQ = buttonFAQ.getText();


        WebElement buttonHowToStart = driver.findElement(   //найти How To Start
                By.xpath("//div[@id= 'desktop-menu']//a[@href='/appid']")
        );
        Thread.sleep(2000);
        String actualResultHowToStart = buttonHowToStart.getText();


        WebElement buttonAskaQuestion = driver.findElement(   //найти Ask a Question
                By.xpath("//div[@id= 'desktop-menu']//a[@href = 'https://home." +
                        "openweathermap.org/questions'][@target='_blank']")
        );
        Thread.sleep(2000);
        String actualResultAskaQuestion = buttonAskaQuestion.getText();

        Assert.assertEquals(actualResultFAQ, expectedResultFAQ);
        Assert.assertEquals(actualResultHowToStart, expectedResultHowToStart);
        Assert.assertEquals(actualResultAskaQuestion, expectedResultAskaQuestion);

        driver.quit(); // закрыть сессию
    }

    /*TC_11_05
1. Открыть базовую ссылку
2. Нажать пункт меню Support → Ask a question
3. Заполнить поля Email, Subject, Message
4. Не подтвердив CAPTCHA, нажать кнопку Submit
5. Подтвердить, что пользователю будет показана ошибка
 “reCAPTCHA verification failed, please try again.”*/

    @Test
    public void reCAPTCHA() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String email = "myk@mail.com";



        driver.get(url); //открыть ссылку
        Thread.sleep(3000);
        driver.manage().window().maximize(); //раскрыть полное окно
        Thread.sleep(5000);

        WebElement buttonSupport = driver.findElement(   //найти Support
                By.xpath("//div[@id = 'support-dropdown']")
        );
        Thread.sleep(2000);
        buttonSupport.click();
        Thread.sleep(2000);

        WebElement buttonAskaQuestion = driver.findElement(   //найти Ask a Question
                By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']//a[@target= '_blank']")
        );
        Thread.sleep(2000);
        buttonAskaQuestion.click();
        Thread.sleep(2000);

//        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
//        driver.switchTo().window(tabs2.get(2)); //открыть новую вкладку
//        Thread.sleep(5000);
//
//
//        WebElement firstField = driver.findElement(   //найти первое поле для заполнения
//                By.xpath("//div[@class ='col-sm-8 hint-question-form']")
//        );
//
//        Thread.sleep(2000);
//        firstField.click(); //нажать на первое поле
//        Thread.sleep(2000);
//        firstField.sendKeys(email); //набрать email
//        Thread.sleep(2000);
//
//        WebElement secondField = driver.findElement(   //найти первое поле для заполнения
//                By.xpath("//select[@class = 'form-control select required']")
//        );
//        Thread.sleep(2000);
//        secondField.click(); //нажать на второе поле
//        Thread.sleep(2000);

        driver.quit(); // закрыть сессию
    }
}
