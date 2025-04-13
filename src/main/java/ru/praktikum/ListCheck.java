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

    public ListCheck () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public String getPanelText(int index) throws InterruptedException {
        String accordionPanelId = "//div[@id='accordion__panel-"+index+"']/p";
        By text = By.xpath(accordionPanelId);
        Thread.sleep(1500);
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

    public void  tearDown() {
        driver.quit();
    }
}


