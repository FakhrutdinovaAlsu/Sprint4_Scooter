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

public ListCheck (WebDriver driver) {
        this.driver = driver;
    }

    public void clickAccordion(int index) {
        String accordionHeadingId = "accordion__heading-"+index;
        WebElement accordionHeadingElement = driver.findElement(By.id(accordionHeadingId));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", accordionHeadingElement);
        WebElement element = new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.id(accordionHeadingId)));
        element.click();
    }

    public String getPanelText(int index) {
        String accordionPanelId = "//div[@id='accordion__panel-"+index+"']/p";
        By text = By.xpath(accordionPanelId);
        WebElement element = new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(text));
        return element.getText();
    }

    public void closeCookie() {
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();
    }

    public void openPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
}