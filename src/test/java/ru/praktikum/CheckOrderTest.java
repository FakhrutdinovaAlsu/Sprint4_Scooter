package ru.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

        By orderButton = By.className("Button_Button__ra12g");
        driver.findElement(orderButton).click();
        //Thread.sleep(100);

        By nameFieldLocator = By.xpath("//input[@placeholder='* Имя']");
        driver.findElement(nameFieldLocator).sendKeys(name);

        By secondNameFieldLocator = By.xpath("//input[@placeholder='* Фамилия']");
        driver.findElement(secondNameFieldLocator).sendKeys(secondName);
        //Thread.sleep(100);

        By addressFieldLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
        driver.findElement(addressFieldLocator).sendKeys(address);
        //Thread.sleep(100);

        By metroStationFieldLocator = By.xpath("//input[@placeholder='* Станция метро']");
        driver.findElement(metroStationFieldLocator).click();
        //Thread.sleep(100);

        By metroStationName = By.xpath("//*[contains(@class, 'Order_Text__2broi') and contains(text(), '"+ station +"')]");
        driver.findElement(metroStationName).click();
        //Thread.sleep(100);

        By phoneNumberFieldLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
        driver.findElement(phoneNumberFieldLocator).sendKeys(phone);
        //Thread.sleep(100);

        By ButtonNext = By.className("Button_Middle__1CSJM");
        driver.findElement(ButtonNext).click();

        By clickWhenGiveFieldLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");
        driver.findElement(clickWhenGiveFieldLocator).sendKeys(dateOrder);
        //Thread.sleep(100);

        By chooseWhenGiveFieldLocator = By.className("react-datepicker__day--selected");
        driver.findElement(chooseWhenGiveFieldLocator).click();
        //Thread.sleep(100);

        By howLongGivenFieldLocator = By.className("Dropdown-placeholder");
        driver.findElement(howLongGivenFieldLocator).click();
        //Thread.sleep(100);

        By howDaysRent = By.xpath("//*[contains(@class, 'Dropdown-option') and contains(text(), '"+ longOrder  +"')]");
        driver.findElement(howDaysRent).click();
        //Thread.sleep(100);

        By colorScooter = By.xpath("//*[contains(@class, 'Checkbox_Label__3wxSf') and contains(text(), '" + color +"')]");
        driver.findElement(colorScooter).click();
        //Thread.sleep(100);

        By commentForСourier = By.xpath("//input[@placeholder='Комментарий для курьера']");
        driver.findElement(commentForСourier).sendKeys(comment);
        //Thread.sleep(100);

        By buttonOrderFinish = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
        driver.findElement(buttonOrderFinish).click();
        //Thread.sleep(100);

       By buttonYesFinish = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Да']");
        driver.findElement(buttonYesFinish).click();
    }


    @After
    public void  tearDown() {
        driver.quit();
    }
}
