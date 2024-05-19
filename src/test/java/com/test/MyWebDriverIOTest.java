package com.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static java.time.Duration.ofSeconds;

public class MyWebDriverIOTest {
    private AndroidDriver driver;
    private String localHost = "http://127.0.0.1:4723";
    @BeforeClass
    public void setupClass() throws MalformedURLException {
        UiAutomator2Options options = getOptionsMyDemoApp();
        this.driver = new AndroidDriver(new URL(localHost), options);
        this.driver.manage()
                .timeouts()
                .implicitlyWait(ofSeconds(5));
    }
    private UiAutomator2Options getOptionsMyDemoApp(){
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554")
                .setPlatformVersion("12.0")
                //.setApp(System.getProperty("user.dir")+"/apps/MyDemoApp.apk")
                .setApp(System.getProperty("user.dir")+"/apps/driverio.apk")
                .setCapability("appium:settings[ignoreUnimportantViews]", true);
        return options;
    }

    @Test
    public void testDragAndDrop(){
        var drioverIO = new DriverIO(driver);
        drioverIO.dragAnDrop();
    }
}
