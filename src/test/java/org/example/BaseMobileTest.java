package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.example.driver.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseMobileTest {
    protected AndroidDriver driver;

    @BeforeEach
    void setUp() {
        driver = DriverFactory.createDriver();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
