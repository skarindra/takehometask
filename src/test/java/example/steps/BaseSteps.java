package example.steps;

import example.TestInstruments;
import io.appium.java_client.android.AndroidElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by sekarayukarindra.
 */
public class BaseSteps extends TestInstruments {

    private static Logger LOGGER = LogManager.getLogger(BaseSteps.class);

    public static boolean isElementVisibleById(String element) {
        try {
            WebDriverWait wait = new WebDriverWait(getAndroidDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    public static boolean isElementVisibleByText(String element) {
        try {
            WebDriverWait wait = new WebDriverWait(getAndroidDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element)));
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    public static void clickById(String element){
        if(isElementVisibleById(element)) {
            getAndroidDriver().findElementById(element).click();
        }
    }

    public static void clickByText(String element){
        if(isElementVisibleByText(element)) {
            getAndroidDriver().findElementByLinkText(element).click();
        }
    }

    public static void type(String element, String value){
        if(isElementVisibleById(element)){
            getAndroidDriver().findElementById(element).sendKeys(value);
        }
    }

    public static void chooseWithValue(String element, String value){
        List<AndroidElement> el = getAndroidDriver().findElementsById(element);
        for (AndroidElement elm: el) {
            if(elm.getText().equals(value)){
                elm.click();
                break;
            }
        }
    }

    public static String getText(String element){
        String returnText = "";
        if(isElementVisibleById(element)){
            returnText = getAndroidDriver().findElementById(element).getText();
        }
        return returnText;
    }
}
