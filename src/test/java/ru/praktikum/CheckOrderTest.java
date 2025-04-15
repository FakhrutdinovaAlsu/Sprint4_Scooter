package ru.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

// Класс оформления заказа
@RunWith(Parameterized.class)
public class CheckOrderTest {
    private WebDriver driver;
    private final String name;
    private final String secondName;
    private final String address;
    private final String station;
    private final String phone;
    private final String dateOrder;
    private final String longOrder;
    private final String color;
    private final String comment;

    public CheckOrderTest (String name, String secondName, String address,String station, String phone, String dateOrder,String longOrder,String color,String comment) {
        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.dateOrder = dateOrder;
        this.longOrder = longOrder;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Тестовые данные: name, secondName, address, station, phone, dateOrder, longOrder, color, comment ")
    public static Object [][] getTextData() {
        return new Object[][]{
                {"Петр","Иванов","Москва","Бульвар Рокоссовского","88005553535","16.04.2025","четверо суток","чёрный жемчуг","чистый"},
                {"Вася","Петров","Москва","Красные Ворота","88005553555","18.04.2025","двое суток","серая безысходность","новый"},
        };
    }

    @Before
    public void StartUp()  {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
    public void CheckOrder() throws InterruptedException {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        OrderPage orderPage = new OrderPage();
        driver.findElement(orderPage.orderButton).click();
        driver.findElement(orderPage.nameFieldLocator).sendKeys(name);
        driver.findElement(orderPage.secondNameFieldLocator).sendKeys(secondName);
        driver.findElement(orderPage.addressFieldLocator).sendKeys(address);
        driver.findElement(orderPage.metroStationFieldLocator).click();
        driver.findElement(orderPage.getMetroStationNameLocator(station)).click();
        driver.findElement(orderPage.phoneNumberFieldLocator).sendKeys(phone);
        driver.findElement(orderPage.ButtonNext).click();
        driver.findElement(orderPage.clickWhenGiveFieldLocator).sendKeys(dateOrder);
        driver.findElement(orderPage.chooseWhenGiveFieldLocator).click();
        driver.findElement(orderPage.howLongGivenFieldLocator).click();
        driver.findElement(orderPage.getHowDaysRentLocator(longOrder)).click();
        driver.findElement(orderPage.getColorScooterLocator(color)).click();
        driver.findElement(orderPage.commentForСourier).sendKeys(comment);
        driver.findElement(orderPage.buttonOrderFinish).click();
        driver.findElement(orderPage.buttonYesFinish).click();
    }

    @After
    public void  tearDown() {
        driver.quit();
    }
}
