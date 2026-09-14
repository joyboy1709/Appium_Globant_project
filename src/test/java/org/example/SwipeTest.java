package org.example;

import org.example.pages.HomePage;
import org.example.pages.SwipePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SwipeTest extends BaseMobileTest {
    @Test
    void shouldSwipeCardsAndFindHiddenMessage() {
        new HomePage(driver).waitUntilLoaded().openSwipe();
        SwipePage swipe = new SwipePage(driver).waitUntilLoaded();
        assertTrue(swipe.isFirstCardVisible());

        swipe.swipeRight();
        assertTrue(swipe.isSecondCardVisible(), "The next card should be visible");

        for (int i = 0; i < 20 && !swipe.isLastCardVisible(); i++) {
            swipe.swipeRight();
        }
        assertTrue(swipe.isLastCardVisible(), "The last card should be visible");

        for (int i = 0; i < 8 && !swipe.isFoundMessageVisible(); i++) {
            swipe.swipeUp();
        }
        assertTrue(swipe.isFoundMessageVisible(), "The hidden message should be found");
    }
}
