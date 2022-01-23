package example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.cucumber.java8.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by sekarayukarindra.
 */
public class TestInstruments {

    private DesiredCapabilities capabilities;
    private String appDirectory = System.getProperty("user.dir")+"/app/com.monefy.app.lite_2021-12-17.apk";
    private String appiumLogFilePath = System.getProperty("user.dir") + "/appium.log";
    private URL appiumServerURL;
    private static AndroidDriver<AndroidElement> androidDriver;
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private static int DEFAULT_TIMEOUT = 60;
    private final static Logger LOGGER = LogManager.getLogger(TestInstruments.class);
    private static String scenarioName;

    private void setCapabilities(){
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "192.168.56.106:5555");
        capabilities.setCapability(MobileCapabilityType.UDID, "192.168.56.106:5555");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        capabilities.setCapability(MobileCapabilityType.APP, appDirectory);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "com.monefy.activities.onboarding.OnboardingActivity_");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
        capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);

    }

    private void loadDriver(){
        setCapabilities();
        androidDriver = new AndroidDriver<AndroidElement>(appiumServerURL, capabilities);
    }

    private void startAppium() {
        try {
            File appiumLogFile = new File(appiumLogFilePath);
            if (appiumServerURL == null) {
                builder = new AppiumServiceBuilder();
                builder.usingAnyFreePort();
                builder.withIPAddress("127.0.0.1");
                builder.withStartUpTimeOut(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
                builder.withArgument(GeneralServerFlag.LOG_LEVEL, "debug");
                builder.withLogFile(appiumLogFile);
                service = AppiumDriverLocalService.buildService(builder);
                LOGGER.info("Starting Appium Server !");
                service.start();
                appiumServerURL = service.getUrl();
                LOGGER.info("APPIUM RUN IN : "+appiumServerURL.toString());
            } else {
                LOGGER.info("Using custom appium server : " + appiumServerURL.toString());
            }
        } catch (Exception e) {
            forceStop();
        }
    }

    private void forceStop() {
        try {
            androidDriver.quit();
        } catch (Exception e) {
            System.out.println("Couldn't force quit android driver!");
        }

        try {
            service.stop();
        } catch (Exception e) {
            System.out.println("There is something wrong with appium server!");
        }
    }

    public void tearDown(){
        try {
            LOGGER.info("Stopping appium server!");
            androidDriver.quit();
            service.stop();
        } catch (Exception e) {
            LOGGER.info("Android driver closed!");
        }
    }

    public void init(){
        startAppium();
        loadDriver();
    }

    public static AndroidDriver<AndroidElement> getAndroidDriver(){
        return androidDriver;
    }

    protected void afterScenario(Scenario scenario) {
        scenarioName = scenario.getName();

        if (scenario.isFailed()) {
            String path = System.getProperty("user.dir") + "/screenshots_failed/";
            File srcFile = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
            byte[] screenshot = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.BYTES);
            File imageFile = new File(path + scenarioName + ".png");
            try {
                scenario.attach(screenshot, "image/png", "screenshot");
                FileUtils.copyFile(Objects.requireNonNull(srcFile), imageFile);
                LOGGER.info("Screenshot taken");
            } catch (Exception e) {
                LOGGER.error("Exception while taking screenshot", e);
            }
        }
    }
}
