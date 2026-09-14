package org.example.pages;

import io.appium.java_client.android.AndroidDriver;

public class HomePage extends BasePage {
    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    public HomePage waitUntilLoaded() {
        accessibility("Home-screen");
        return this;
    }

    public void openLogin() {
        tap("Login");
    }

    public void openForms() {
        tap("Forms");
    }

    public void openSwipe() {
        tap("Swipe");
    }

    public void openWebView() {
        tap("Webview");
    }

    public void openDrag() {
        tap("Drag");
    }

    public boolean hasHomeTab() {
        return isDisplayed("Home");
    }
}
