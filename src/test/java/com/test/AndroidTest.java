package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

class AndroidTest {

    public AndroidDriver driver;
    public AppiumServiceBuilder service;

    @Test
    void androidLaunchTest() throws MalformedURLException, InterruptedException {

        //Run appium server automaticamente
        //service = new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
               // .withIPAddress("127.0.0.1").usingPort(4723);



        //Crear capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity("ApiDemos");
        options.setPlatformVersion("12.0");
        options.setApp(System.getProperty("user.dir")+"/apps/ApiDemos-debug.apk");

        //Crear Objeto AndroidDriver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);



        //Prueba Driver
        Thread.sleep(4000);
        driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/continue_button")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='android:id/button1']")).click();

        driver.findElement(AppiumBy.accessibilityId("App")).click();

        String text = driver.findElement(AppiumBy.accessibilityId("Fragment")).getText();
        System.out.println(text);
        driver.findElement(AppiumBy.accessibilityId("Fragment")).click();
        driver.navigate().back();
        driver.navigate().back();


    }

    @Test
    void iosLounch() throws MalformedURLException, InterruptedException {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iphone 15");

        IOSDriver driver1 = new IOSDriver(new URL("http://127.0.0.1:4273"), options);

        Thread.sleep(3000);
    }

    @Test
    void androidLaunchTestDos() throws MalformedURLException, InterruptedException {

        UiAutomator2Options options = getOptionsMyDemoApp();

        //Crear Objeto AndroidDriver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        driver.findElement(AppiumBy.accessibilityId("open menu")).click();
        Thread.sleep(500);
        driver.findElement(AppiumBy.accessibilityId("menu item log in")).click();
        Thread.sleep(500);
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("bob@example.com");
        driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("10203040");
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();
        Thread.sleep(500);
        String titulo = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Products\"]")).getText();
        driver.quit();
        Assertions.assertEquals("Products", titulo);

        Thread.sleep(4000);

    }

    public UiAutomator2Options getOptionsMyDemoApp(){
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setPlatformVersion("12.0");
        options.setApp(System.getProperty("user.dir")+"/apps/MyDemoApp.apk");
        return options;
    }
}
