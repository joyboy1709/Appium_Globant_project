package org.example.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private static final By LOGIN_CONTAINER = AppiumBy.accessibilityId("button-login-container");
    private static final By SIGNUP_CONTAINER = AppiumBy.accessibilityId("button-sign-up-container");
    private static final By EMAIL = AppiumBy.accessibilityId("input-email");
    private static final By PASSWORD = AppiumBy.accessibilityId("input-password");
    private static final By REPEAT_PASSWORD = AppiumBy.accessibilityId("input-repeat-password");
    private static final By LOGIN_BUTTON = AppiumBy.accessibilityId("button-LOGIN");
    private static final By SIGNUP_BUTTON = AppiumBy.accessibilityId("button-SIGN UP");

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public LoginPage waitUntilLoaded() {
        accessibility("Login-screen");
        return this;
    }

    public void selectLogin() {
        wait.until(d -> d.findElement(LOGIN_CONTAINER)).click();
    }

    public void selectSignUp() {
        wait.until(d -> d.findElement(SIGNUP_CONTAINER)).click();
    }

    public void signUp(String email, String password) {
        fill(EMAIL, email);
        fill(PASSWORD, password);
        fill(REPEAT_PASSWORD, password);
        wait.until(d -> d.findElement(SIGNUP_BUTTON)).click();
    }

    public void login(String email, String password) {
        fill(EMAIL, email);
        fill(PASSWORD, password);
        wait.until(d -> d.findElement(LOGIN_BUTTON)).click();
    }

    public boolean isLoginScreenVisible() {
        return isDisplayed("Login-screen");
    }

    private void fill(By locator, String value) {
        wait.until(d -> d.findElement(locator)).sendKeys(value);
    }
}
