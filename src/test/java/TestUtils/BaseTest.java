package TestUtils;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pageObjects.android.FormPage;
import pageObjects.android.LoginPage;
import pageObjects.android.SignUpPage;
import utils.AppiumUtils;

import java.io.*;
import java.net.URL;
import java.util.Properties;


public class BaseTest extends AppiumUtils {


    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public AppiumServiceBuilder builder;
    public FormPage formPage;
    public LoginPage login;
    public SignUpPage signUp;


    @BeforeClass
    public void ConfigureAppium() throws IOException {


        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//data.properties");
        prop.load(fis);
        String ipAddress = System.getProperty("ipAddress")!= null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
        String port = prop.getProperty("port");



        try {
            builder = new AppiumServiceBuilder();
            builder.withIPAddress(ipAddress);
                   builder.usingPort(Integer.parseInt(port));
            service = AppiumDriverLocalService.buildService(builder);
            service.start();
        } catch (Exception ignored) {
        }

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(System.getProperty("AndroidDeviceName")!= null ? System.getProperty("AndroidDeviceName") : prop.getProperty("AndroidDeviceName"));
        options.setApp(System.getProperty("user.dir")+"//src//main//resources//General-Store.apk");
//        options.setApp("/Users/geekyants/Downloads/APKPure_v3.19.65_apkpure.com.apk");
//        options.setApp("/Users/geekyants/Downloads/build2/app-paypenny-release.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options );
         formPage = new FormPage(driver);
//        login = new LoginPage(driver);
//        signUp = new SignUpPage(driver);

    }



    @AfterClass
    public void tearDown(){
         driver.quit();
    }

}
