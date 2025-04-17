package ru.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class ListCheck {
    private WebDriver driver;

public ListCheck (WebDriver driver) {
        this.driver = driver;
    }
    public String getPanelText(int index) {
        String accordionPanelId = "//div[@id='accordion__panel-"+index+"']/p";
        By text = By.xpath(accordionPanelId);
        return driver.findElement(text).getText();
    }

    public void clickAccordion(int index) {
        String accordionHeadingId = "accordion__heading-"+index;
        WebElement accordionHeadingElement = driver.findElement(By.id(accordionHeadingId));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", accordionHeadingElement);
        accordionHeadingElement.click();
    }

    public void closeCookie() {
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();
    }

    public void openPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

}


