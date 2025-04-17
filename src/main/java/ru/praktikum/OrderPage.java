package ru.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private final WebDriver driver;

    public By orderButtonUpLocator = By.className("Button_Button__ra12g");
    public By nameFieldLocator = By.xpath("//input[@placeholder='* Имя']");
    public By secondNameFieldLocator = By.xpath("//input[@placeholder='* Фамилия']");
    public By addressFieldLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    public By metroStationFieldLocator = By.xpath("//input[@placeholder='* Станция метро']");
    public By phoneNumberFieldLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    public By ButtonNextLocator = By.className("Button_Middle__1CSJM");
    public By clickWhenGiveFieldLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    public By chooseWhenGiveFieldLocator = By.className("react-datepicker__day--selected");
    public By howLongGivenFieldLocator = By.className("Dropdown-placeholder");
    public By commentForСourierLocator = By.xpath("//input[@placeholder='Комментарий для курьера']");
    public By buttonOrderFinishLocator = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    public By buttonYesFinishLocator = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Да']");
    public By orderButtonDownPage = By.className("Button_Middle__1CSJM");
    public By orderConfirmButtonLocator = By.className("Order_ModalHeader__3FDaJ");
    public String textOrderConfirm = "Заказ оформлен";

    public OrderPage (WebDriver driver) {
        this.driver = driver;
    }

    private By getColorScooterLocator(String color) {
        return By.xpath("//*[contains(@class, 'Checkbox_Label__3wxSf') and contains(text(), '" + color + "')]");
    }

    private By getHowDaysRentLocator(String longOrder) {
        return By.xpath("//*[contains(@class, 'Dropdown-option') and contains(text(), '" + longOrder + "')]");
    }

    private By getMetroStationNameLocator(String station) {
        return By.xpath("//*[contains(@class, 'Order_Text__2broi') and contains(text(), '" + station + "')]");
    }

    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void orderButtonUp() {
        driver.findElement(orderButtonUpLocator).click();
    }

    public void orderButtonDown() {
        WebElement buttonElement = driver.findElement(orderButtonDownPage);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", buttonElement);
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(orderButtonDownPage));
        element.click();
    }

    public void processFirstOrderPage(String name, String secondName, String address, String station, String phone) {
        driver.findElement(nameFieldLocator).sendKeys(name);
        driver.findElement(secondNameFieldLocator).sendKeys(secondName);
        driver.findElement(addressFieldLocator).sendKeys(address);
        driver.findElement(metroStationFieldLocator).click();
        driver.findElement(getMetroStationNameLocator(station)).click();
        driver.findElement(phoneNumberFieldLocator).sendKeys(phone);
        driver.findElement(ButtonNextLocator).click();
    }

    public void processSecondOrderPage(String dateOrder, String longOrder, String color, String comment) {
        driver.findElement(clickWhenGiveFieldLocator).sendKeys(dateOrder);
        driver.findElement(chooseWhenGiveFieldLocator).click();
        driver.findElement(howLongGivenFieldLocator).click();
        driver.findElement(getHowDaysRentLocator(longOrder)).click();
        driver.findElement(getColorScooterLocator(color)).click();
        driver.findElement(commentForСourierLocator).sendKeys(comment);
        driver.findElement(buttonOrderFinishLocator).click();
    }

    public void confirmOrderPage() {
        driver.findElement(buttonYesFinishLocator).click();
    }

    public String getPanelTextConfirmOrder() {
        String innerTextOrderConfirm = driver.findElement(orderConfirmButtonLocator).getText();
        return innerTextOrderConfirm.substring(0,14);
    }
}
