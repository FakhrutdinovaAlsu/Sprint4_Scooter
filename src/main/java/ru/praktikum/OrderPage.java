package ru.praktikum;

import org.openqa.selenium.By;

public class OrderPage {
    public By orderButtonUp = By.className("Button_Button__ra12g");
    public By nameFieldLocator = By.xpath("//input[@placeholder='* Имя']");
    public By secondNameFieldLocator = By.xpath("//input[@placeholder='* Фамилия']");
    public By addressFieldLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    public By metroStationFieldLocator = By.xpath("//input[@placeholder='* Станция метро']");
    public By phoneNumberFieldLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    public By ButtonNext = By.className("Button_Middle__1CSJM");
    public By clickWhenGiveFieldLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    public By chooseWhenGiveFieldLocator = By.className("react-datepicker__day--selected");
    public By howLongGivenFieldLocator = By.className("Dropdown-placeholder");
    public By commentForСourier = By.xpath("//input[@placeholder='Комментарий для курьера']");
    public By buttonOrderFinish = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    public By buttonYesFinish = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Да']");
    public By orderButtonDownPage = By.className("Button_UltraBig__UU3Lp");
    public By orderConfirmButton = By.className("Order_ModalHeader__3FDaJ");
    public String textOrderConfirm = "Заказ оформлен";

    public By getColorScooterLocator(String color) {
        return By.xpath("//*[contains(@class, 'Checkbox_Label__3wxSf') and contains(text(), '" + color + "')]");
    }

    public By getHowDaysRentLocator(String longOrder) {
        return By.xpath("//*[contains(@class, 'Dropdown-option') and contains(text(), '" + longOrder + "')]");
    }

    public By getMetroStationNameLocator(String station) {
        return By.xpath("//*[contains(@class, 'Order_Text__2broi') and contains(text(), '" + station + "')]");
    }
}
