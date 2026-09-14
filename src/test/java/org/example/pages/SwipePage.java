package org.example.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class SwipePage extends BasePage {
    public SwipePage(AndroidDriver driver) {
        super(driver);
    }

    public SwipePage waitUntilLoaded() {
        accessibility("Swipe-screen");
        return this;
    }

    public boolean isFirstCardVisible() {
        return isDisplayed("card-1");
    }

    public boolean isLastCardVisible() {
        return isDisplayed("card-4");
    }

    public boolean isFoundMessageVisible() {
        return isDisplayed("You found me!!!");
    }

    public void swipeRight() {
        swipe(0.80, 0.20, 0.50, 0.35);
    }

    public void swipeUp() {
        swipe(0.50, 0.80, 0.50, 0.20);
    }

    private void swipe(double startX, double startY, double endX, double endY) {
        Dimension size = driver.manage().window().getSize();
        Point start = new Point((int) (size.width * startX), (int) (size.height * startY));
        Point end = new Point((int) (size.width * endX), (int) (size.height * endY));
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence gesture = new Sequence(finger, 0);
        gesture.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), start.x, start.y));
        gesture.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        gesture.addAction(finger.createPointerMove(Duration.ofMillis(600),
                PointerInput.Origin.viewport(), end.x, end.y));
        gesture.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(gesture));
    }
}
