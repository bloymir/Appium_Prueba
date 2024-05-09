package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.appium.java_client.AppiumBy.*;
import static java.time.Duration.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class MyDemoSignUp {

    private AndroidDriver driver;
    private WebDriverWait wait;
    private By menuBtn = accessibilityId("open menu");
    private By loginMenu = accessibilityId("menu item log in");
    private By userInput = accessibilityId("Username input field");
    private By passInput = accessibilityId("Password input field");
    private By loginBtn = accessibilityId("Login button");
    private By titleText = AppiumBy.xpath("//android.widget.TextView[@text=\"Products\"]");
    private By msjErrorOutput = AppiumBy.xpath("//android.widget.TextView[@text=\"Provided credentials do not match any user in this service.\"]");

    public MyDemoSignUp(final AndroidDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, ofSeconds(10));
    }

    private void pasosLogin(String userName, String password){
        this.wait.until(elementToBeClickable(menuBtn)).click();
        this.wait.until(elementToBeClickable(loginMenu)).click();
        this.wait.until(elementToBeClickable(userInput)).sendKeys(userName);
        this.wait.until(elementToBeClickable(passInput)).sendKeys(password);
        this.wait.until(elementToBeClickable(loginBtn)).click();
    }
    public String login(String userName, String password){
        pasosLogin(userName, password);
        String prueba = this.wait.until(visibilityOfElementLocated(titleText)).getText();
        return prueba;
    }
    public String loginError(String usernmame, String password){
        pasosLogin(usernmame, password);
        String mensaje = this.wait.until(visibilityOfElementLocated(msjErrorOutput)).getText();
        return mensaje;
    }
}
