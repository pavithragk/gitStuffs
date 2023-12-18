package Appium_Frame;

import TestUtils.BaseTest;
import org.testng.annotations.Test;
import pageObjects.android.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void LoginForm(){

        LoginPage login = new LoginPage(driver);
        login.skipAction();
        login.dropDownAction();
        login.selectDropdown();
        login.enterMobileNumber("8553317682");
        login.continueButtonAction();
        login.findMpinText();
//        login.tapOnProfile();
//        login.tapOnUserIcon();
//        login.clickOnLoginButton();


    }
}
