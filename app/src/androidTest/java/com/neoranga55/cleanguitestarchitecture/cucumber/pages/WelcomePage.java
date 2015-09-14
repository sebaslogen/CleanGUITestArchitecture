package com.neoranga55.cleanguitestarchitecture.cucumber.pages;

import com.neoranga55.cleanguitestarchitecture.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * This class will expose and implement all behaviors and most checks of the WelcomeActivity
 */
public class WelcomePage extends BasePage {

    protected static final String SCREENSHOT_TAG = "WelcomePage";

    /**
     * The constructor verifies that we are on the correct page by checking
     * the existence of the unique identifier elements of the page/view
     */
    public WelcomePage() {
        onView(withId(R.id.welcome_activity)).check(matches(isDisplayed()));
    }

    /**
     * Check the title
     * All check methods start with 'check' and should not return
     * any Page Object because checks can't perform any action
     * @param title The string title to search in the page/view
     */
    public void checkTitle(String title) {
        onView(withText(title)).check(matches(isDisplayed()));
    }
}
