package com.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import static java.time.Duration.*;

public class MyDemoTest {
    private AndroidDriver driver;
    private String localHost = "http://127.0.0.1:4723";
    private String mensajeError = "Provided credentials do not match any user in this service.";
    private String titleExpected = "Products";
    private String userName = "bob@example.com";
    private String passGood = "10203040";
    private String passFail = "1234567";

    @BeforeClass
    public void setupClass() throws MalformedURLException {
        UiAutomator2Options options = getOptionsMyDemoApp();
        this.driver = new AndroidDriver(new URL(localHost), options);
        this.driver.manage()
                .timeouts()
                .implicitlyWait(ofSeconds(5));
    }

    @AfterClass
    public void tearDown(){
        this.driver.quit();
    }

    @Test
    public void testLogin(){
        var myDemo = new MyDemoSignUp(driver);
        Assert.assertEquals(myDemo.login(userName, passGood), titleExpected);
    }

    @Test
    public void testInvalidLogin(){
        var myDemo = new MyDemoSignUp(driver);
        Assert.assertEquals(myDemo.loginError(userName, passFail), mensajeError);

    }



    private UiAutomator2Options getOptionsMyDemoApp(){
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554")
                .setPlatformVersion("12.0")
                .setApp(System.getProperty("user.dir")+"/apps/MyDemoApp.apk")
                .setCapability("appium:settings[ignoreUnimportantViews]", true);
        return options;
    }
}
