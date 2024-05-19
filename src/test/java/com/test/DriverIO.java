package com.test;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import w3c.FingerGestureUtils;

import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

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
    private By primerElemento = androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)");
    private By primerElementoPosicion = androidUIAutomator("new UiSelector().description(\"drop-l2\")");
    private By segundoElementoPosicion = androidUIAutomator("new UiSelector().description(\"drop-r3\")");
    private By terceroElementoPosicion = androidUIAutomator("new UiSelector().description(\"drop-r1\")");
    private By cuartoElementoPosicion = androidUIAutomator("new UiSelector().description(\"drop-c1\")");
    private By quintoElementoPosicion = androidUIAutomator("new UiSelector().description(\"drop-c3\")");
    private By sextoElementoPosicion = androidUIAutomator("new UiSelector().description(\"drop-r2\")");
    private By septimoElementoPosicion = androidUIAutomator("new UiSelector().description(\"drop-c2\")");
    private By octavoElementoPosicion = androidUIAutomator("new UiSelector().description(\"drop-l1\")");
    private By novenoElementoPosicion = androidUIAutomator("new UiSelector().description(\"drop-l3\")");
    private By textElemento = androidUIAutomator("new UiSelector().text(\"You made it, click retry if you want to try it again.\")");

    public String dragAnDrop(){
        WebElement element = elementClickeable(dragBtn);
        fingerGestureUtils.tap(element);
        fingerGestureUtils.dragTo(elementClickeable(primerElemento), elementClickeable(primerElementoPosicion));
        fingerGestureUtils.dragTo(elementClickeable(primerElemento), elementClickeable(segundoElementoPosicion));
        fingerGestureUtils.dragTo(elementClickeable(primerElemento), elementClickeable(terceroElementoPosicion));
        fingerGestureUtils.dragTo(elementClickeable(primerElemento), elementClickeable(cuartoElementoPosicion));
        fingerGestureUtils.dragTo(elementClickeable(primerElemento), elementClickeable(quintoElementoPosicion));
        fingerGestureUtils.dragTo(elementClickeable(primerElemento), elementClickeable(sextoElementoPosicion));
        fingerGestureUtils.dragTo(elementClickeable(primerElemento), elementClickeable(septimoElementoPosicion));
        fingerGestureUtils.dragTo(elementClickeable(primerElemento), elementClickeable(octavoElementoPosicion));
        fingerGestureUtils.dragTo(elementClickeable(primerElemento), elementClickeable(novenoElementoPosicion));
        String texto = elementClickeable(textElemento).getText();
        return texto;

    }

    private WebElement elementClickeable(By byElement){
        return this.wait.until(elementToBeClickable(byElement));
    }

}
