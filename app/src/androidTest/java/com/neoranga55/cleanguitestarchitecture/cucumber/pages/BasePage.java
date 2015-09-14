package com.neoranga55.cleanguitestarchitecture.cucumber.pages;
import android.support.annotation.IdRes;

import com.neoranga55.cleanguitestarchitecture.cucumber.steps.HelperSteps;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Contain the basic behavior logic and checks shared across all pages/views
 */
public class BasePage {

    public static final String SCREENSHOT_TAG = "invalid-page";

    public <T extends BasePage> T is(Class<T> type) {
        if (type.isInstance(this)) {
            return type.cast(this);
        } else {
            HelperSteps.takeScreenshot(SCREENSHOT_TAG);
            throw new InvalidPageException("Invalid page type. Expected: " + type.getSimpleName() + ", but got: " + this.getClass().getSimpleName());
        }
    }

    public BasePage tapOnButtonWithText(final String button) {
        onView(withText(button)).perform(click());
        return this;
    }

    public BasePage typeTextToField(@IdRes final int fieldId, final String text) {
        onView(withId(fieldId)).perform(typeText(text));
        return this;
    }

    public static class InvalidPageException extends RuntimeException {

        public InvalidPageException(final String message) {
            super(message);
        }
    }
}
