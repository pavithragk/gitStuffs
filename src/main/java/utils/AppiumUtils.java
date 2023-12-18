package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils {
    public AppiumDriverLocalService service;
    AppiumServiceBuilder builder;



    public List<HashMap<String, String>> getJsonData(String jsonFilePath)throws IOException {
//        System.getProperty("user.dir")+"//src//test//java//TestData//eCommerce.json"
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
      List<HashMap<String, String>> data;
        data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {

          });
        return data;
    }


//    public void waitForElementToAppear(WebElement ele, AppiumDriver driver)
//    {
//        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.attributeContains((ele),"text" , "Cart"));
//    }


    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {


            builder = new AppiumServiceBuilder();
//            builder.withAppiumJS(new File("//Users//geekyants//.nvm//versions//node//v18.8.0//lib//node_modules//appium//build//lib//main.js"));
            builder.withIPAddress(ipAddress);
            builder.usingPort(port);
//            builder.withArgument(() -> "--relaxed-security"); // Add this line to enable relaxed security
            service = AppiumDriverLocalService.buildService(builder);
            service.start();
            return service;

//        service = new AppiumServiceBuilder()
//                .withAppiumJS(new File("//Users//geekyants//.nvm//versions//node//v18.8.0//lib//node_modules//appium//build//lib//main.js"))
////                .usingDriverExecutable(new File("//Users//geekyants//.nvm//versions//node//v18.8.0//bin//node"))
////                .usingDriverExecutable(new File("/Users/geekyants/.nvm/versions/node/v18.8.0/bin/node")) // not working
//                .withIPAddress(ipAddress)
//                .usingPort(port).build();
//        service.start();
//        return service;

    }

    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;


    }
}
