package pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AndroidActions;

import java.time.Duration;

public class SignUpPage extends AndroidActions {
    AndroidDriver driver;

    public SignUpPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Skip']")
    private WebElement skipBtn;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Select Country']")
    private WebElement clickDropdown;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup")
    private WebElement selectCountry;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='sanitizedInput']")
    private WebElement mobileNumberField;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup")
    private WebElement continueButton;



    public void skipAction(){
        skipBtn.click();
    }

    public void dropDownAction(){
        clickDropdown.click();
    }

    public void selectDropdown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(selectCountry));
        selectCountry.click();
    }

    public void enterMobileNumber(String number){
        mobileNumberField.sendKeys(number);
    }

    public void continueButtonAction(){
        continueButton.click();
    }

}
