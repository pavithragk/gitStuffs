package Appium_Frame;

import TestUtils.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;

public class NewUserTest extends BaseTest {

    AppiumDriver driver;

    public NewUserTest(AppiumDriver driver) {

        this.driver = driver;
    }

    @Test
    public void newUserForm(){
        signUp.skipAction();
        signUp.dropDownAction();
        signUp.selectDropdown();
        signUp.enterMobileNumber("9742303505");
        signUp.continueButtonAction();
    }
}
