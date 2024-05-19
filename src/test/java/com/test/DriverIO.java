package com.test;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import w3c.FingerGestureUtils;

import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class DriverIO {
    private final WebDriverWait wait;
    private final FingerGestureUtils fingerGestureUtils;
    private AndroidDriver driver;

    public DriverIO(final AndroidDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, ofSeconds(10));
        this.fingerGestureUtils = new FingerGestureUtils(driver);
    }

    private By dragBtn = androidUIAutomator("new UiSelector().description(\"Drag\")");

    public void dragAnDrop(){
        WebElement element = this.wait.until(elementToBeClickable(dragBtn));
        fingerGestureUtils.tap(element);
    }

}
