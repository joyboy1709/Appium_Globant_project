package org.example.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final AndroidDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    protected WebElement accessibility(String id) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(id)));
    }

    public boolean isDisplayed(String id) {
        try {
            return accessibility(id).isDisplayed();
        } catch (RuntimeException ignored) {
            return false;
        }
    }

    public void tap(String id) {
        accessibility(id).click();
    }

    protected String acceptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    protected boolean isTextDisplayed(String text) {
        return !driver.findElements(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + text + "\")")).isEmpty();
    }

    protected void tapText(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + text + "\")"))).click();
    }

    protected String acceptNativeAlert(String message) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + message + "\")")));
        tapText("OK");
        return message;
    }
}
