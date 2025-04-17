package ru.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ListCheck {
    private WebDriver driver;
    public By cookieButton = By.className("App_CookieButton__3cvqF");

    public ListCheck (WebDriver driver) {
        this.driver = driver;
    }

    public void clickAccordion(int index) {
        WebElement accordionHeadingElement = driver.findElement(getByAccordionHeading(index));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", accordionHeadingElement);
        WebElement element = new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(accordionHeadingElement));
        element.click();
    }

    public String getPanelText(int index) {
        By text = getByAccordionPanel(index);
        WebElement element = new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(text));
        return element.getText();
    }

    private By getByAccordionPanel(int index) {
        return By.xpath("//div[@id='accordion__panel-"+index+"']/p");
    }

    private By getByAccordionHeading(int index) {
        return By.id("accordion__heading-"+index);
    }

    public void closeCookie() {
        driver.findElement(cookieButton).click();
    }

    public void openPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
}