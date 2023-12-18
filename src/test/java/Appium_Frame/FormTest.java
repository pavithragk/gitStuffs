package Appium_Frame;

import TestUtils.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.android.CartPage;
import pageObjects.android.ProductCatalogue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;



public class FormTest extends BaseTest {


    @Test(dataProvider = "getData")
    public void FillForm(HashMap<String, String> input) throws InterruptedException {

        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setCountrySelection(input.get("country"));
       ProductCatalogue productCatalogue =  formPage.submitForm();
        productCatalogue.addItemToCartByIndex(0);
        productCatalogue.addItemToCartByIndex(0);
       CartPage cartPage = productCatalogue.goToCartPage();


    }

    @BeforeMethod
    public void preSetup(){
        formPage.seActivity();

////        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
////        driver.startActivity(activity);
////        driver.executeScript("mobile: startActivity", ImmutableMap.of("intent","io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));
//
////        driver.executeScript("mobile: startActivity", ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
    }

    @DataProvider
    public Object[][] getData() throws IOException {


       List<HashMap<String, String>> data =  getJsonData(System.getProperty("user.dir")+"//src//test//java//TestData//eCommerce.json");
//       System.out.println( "here"+Objects.checkIndex(data.get(0).size(), data.size()));
        return new Object[][]{{data.get(0)}, {data.get(1)}};

    }


}
