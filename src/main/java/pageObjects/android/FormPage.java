package pageObjects.android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AndroidActions;

import java.time.Duration;

public class FormPage extends AndroidActions {

    AndroidDriver driver;

    public FormPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

//    @AndroidFindBy(xpath ="//android.widget.EditText[@text='Enter name here']")
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.androidsample.generalstore:id/nameField']")
    private WebElement nameField;

    @AndroidFindBy(xpath="//android.widget.RadioButton[@resource-id='com.androidsample.generalstore:id/radioFemale']")
    private WebElement femaleOption;

    @AndroidFindBy(xpath="//android.widget.RadioButton[@resource-id='com.androidsample.generalstore:id/radioMale']")
    private WebElement maleOption;

    @AndroidFindBy(id="android:id/text1")
    private WebElement countrySelection;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;



    public void setNameField(String name) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement textField = wait.until(ExpectedConditions.elementToBeClickable(nameField));
        retryClick(textField, 2);
        textField.sendKeys(name);
//       nameField.sendKeys(name);
        driver.hideKeyboard();


    }

    public void seActivity(){


        ((JavascriptExecutor)driver).executeScript("mobile: startActivity",
                ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
    }



    public void setGender(String gender){
        if(gender.contains("Female"))
            femaleOption.click();
        else
            maleOption.click();

    }

    public void setCountrySelection(String countryName)
    {
        countrySelection.click();
        scrollTpText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();



    }

    public ProductCatalogue submitForm(){
        shopButton.click();
      return  new ProductCatalogue(driver);
    }

    public void retryClick(WebElement element, int maxAttempts) throws InterruptedException {
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
                // Handle or log the exception if needed
                attempts++;
                Thread.sleep(1000); // Adjust the delay as needed
            }
        }
    }



}
