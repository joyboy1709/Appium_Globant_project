package org.example.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.example.config.MobileConfig;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public final class DriverFactory {
    private DriverFactory() {
    }

    public static AndroidDriver createDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(MobileConfig.deviceName())
                .setAutomationName("UiAutomator2")
                .setNoReset(false);
        if (!MobileConfig.appPath().isEmpty()) {
            options.setApp(MobileConfig.appPath());
        }
        if (!MobileConfig.platformVersion().isEmpty()) {
            options.setPlatformVersion(MobileConfig.platformVersion());
        }
        try {
            return new AndroidDriver(new URL(MobileConfig.appiumUrl()), options);
        } catch (MalformedURLException exception) {
            throw new IllegalArgumentException("Invalid Appium URL: " + MobileConfig.appiumUrl(), exception);
        }
    }

    public static WebDriverWait waitFor(AndroidDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(20));
    }
}
