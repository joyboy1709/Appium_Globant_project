package org.example.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SwipePage extends BasePage {
    public SwipePage(AndroidDriver driver) {
        super(driver);
    }

    public SwipePage waitUntilLoaded() {
        accessibility("Swipe-screen");
        return this;
    }

    public boolean isFirstCardVisible() {
        return isActiveCard("__CAROUSEL_ITEM_0__");
    }

    public boolean isLastCardVisible() {
        return isActiveCard("__CAROUSEL_ITEM_5__");
    }

    public boolean isSecondCardVisible() {
        return isActiveCard("__CAROUSEL_ITEM_1__");
    }

    public boolean isFoundMessageVisible() {
        return isTextDisplayed("You found me!!!");
    }

    public void swipeRight() {
        WebElement carousel = wait.until(d -> d.findElement(
                AppiumBy.xpath("//*[@resource-id='Carousel']")));
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("elementId", ((RemoteWebElement) carousel).getId());
        arguments.put("direction", "left");
        arguments.put("percent", 0.8);
        driver.executeScript("mobile: swipeGesture", arguments);
        pauseAfterSwipe();
    }

    public void swipeUp() {
        swipe(0.50, 0.80, 0.50, 0.20);
    }

    private boolean isActiveCard(String resourceId) {
        try {
            return driver.findElement(AppiumBy.xpath(
                    "//*[@resource-id='" + resourceId + "']")).getRect().width > 800;
        } catch (RuntimeException ignored) {
            return false;
        }
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
        pauseAfterSwipe();
    }

    private void pauseAfterSwipe() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Swipe animation was interrupted", exception);
        }
    }
}
