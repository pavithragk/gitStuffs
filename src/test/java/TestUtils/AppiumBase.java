package TestUtils;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import pageObjects.android.FormPage;
import utils.AppiumUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumBase extends AppiumUtils {
    public static FormPage formPage;
    public static AndroidDriver driver;
    private static AppiumDriverLocalService service;

    public static void main(String[] args) {
        appiumServer();
        initializeDriver();
        // Your test logic goes here
        // ...
        closeAppiumServer();
    }



    private static void appiumServer() {
        // Set the path to your Appium server executable
        String appiumServerPath = "/Users/geekyants/.nvm/versions/node/v20.10.0/lib/node_modules/appium/build/lib/main.js";
        String appiumNodePath = "/Users/geekyants/.nvm/versions/node/v20.10.0/bin/node";
//        String appiumServerPath = "/Users/geekyants/.nvm/versions/node/v20.10.0/lib/node_modules/appium";

        // Use the AppiumServiceBuilder to configure the Appium server
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder
                .usingDriverExecutable(new File(appiumNodePath))
                .withAppiumJS(new File(appiumServerPath))
                .usingPort(4723)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withLogFile(new File("appiumServer.log"));

        // Start the Appium server
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    private static void initializeDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set desired capabilities for your Android device
        capabilities.setCapability("deviceName", "OnePlus_Nord");
        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("appPackage", "your_app_package");
//        capabilities.setCapability("appActivity", "your_app_activity");

        capabilities.setCapability("app", "/Users/geekyants/Desktop/Appium_Framework_Project/src/main/resources/General-Store.apk");

        try {
            // Initialize the Appium driver
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
            formPage = new FormPage(driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }




    private static void closeAppiumServer() {
        driver.quit();

        // Stop the Appium server after the test
        if (service != null) {
            service.stop();
        }
    }
}

