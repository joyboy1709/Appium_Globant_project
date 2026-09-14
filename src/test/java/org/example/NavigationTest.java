package org.example;

import org.example.pages.HomePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NavigationTest extends BaseMobileTest {
    @Test
    void shouldNavigateThroughBottomMenuSections() {
        HomePage home = new HomePage(driver).waitUntilLoaded();
        assertTrue(home.hasHomeTab());

        home.openLogin();
        assertTrue(new org.example.pages.LoginPage(driver).isLoginScreenVisible());

        home.openForms();
        assertTrue(new HomePage(driver).isDisplayed("Forms-screen"));

        home.openSwipe();
        assertTrue(new HomePage(driver).isDisplayed("Swipe-screen"));

        home.openWebView();
        assertTrue(new HomePage(driver).isDisplayed("Webview-screen"));

        home.openDrag();
        assertTrue(new HomePage(driver).isDisplayed("Drag-screen"));
    }
}
