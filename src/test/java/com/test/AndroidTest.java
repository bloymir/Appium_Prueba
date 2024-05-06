package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

class AndroidTest {


    @Test
    void androidLaunchTest() throws MalformedURLException, InterruptedException {
      /* AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        try {
            // do stuff with drivers
        } finally {
            service.stop();
        }*/
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity("ApiDemos");
        options.setPlatformVersion("12.0");
        options.setApp(System.getProperty("user.dir")+"/apps/ApiDemos-debug.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        Thread.sleep(4000);
        driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/continue_button")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();

        driver.findElement(AppiumBy.accessibilityId("App")).click();
        driver.findElement(AppiumBy.accessibilityId("Fragment")).click();


    }
}
