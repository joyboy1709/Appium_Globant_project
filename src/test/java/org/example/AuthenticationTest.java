package org.example;

import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthenticationTest extends BaseMobileTest {
    @Test
    void shouldSignUpSuccessfully() {
        String email = uniqueEmail();
        String password = "Password123!";
        LoginPage login = openLogin();
        login.selectSignUp();
        login.signUp(email, password);
        assertTrue(login.isLoginScreenVisible(), "The login screen should remain available after sign up");
    }

    @Test
    void shouldLoginSuccessfullyWithAnIndependentUser() {
        String email = uniqueEmail();
        String password = "Password123!";
        LoginPage login = openLogin();
        login.selectSignUp();
        login.signUp(email, password);
        login.selectLogin();
        login.login(email, password);
        assertTrue(new HomePage(driver).hasHomeTab(), "A successful login should show the home tab");
    }

    private LoginPage openLogin() {
        new HomePage(driver).waitUntilLoaded().openLogin();
        return new LoginPage(driver).waitUntilLoaded();
    }

    private String uniqueEmail() {
        return "appium." + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }
}
