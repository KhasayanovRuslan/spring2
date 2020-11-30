package com.geekbrains.geekmarketwinter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class TestClassMarket {
    private WebDriver driver;

    @BeforeSuite
    public void init() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @Test
    public void test1() {
        driver.get("http://localhost:8181/wintermarket/cart");

        WebElement webElement = driver.findElement(By.cssSelector("table table-hover"));
        Assert.assertEquals(webElement.isEnabled(), true);
    }

    @AfterSuite
    public void shutdown() {
        this.driver.quit();
    }

}
