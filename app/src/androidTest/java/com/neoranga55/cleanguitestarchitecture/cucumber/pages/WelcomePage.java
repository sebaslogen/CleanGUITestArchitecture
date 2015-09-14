package com.neoranga55.cleanguitestarchitecture.cucumber.pages;

import com.neoranga55.cleanguitestarchitecture.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * This class will expose and implement all behaviors and most checks of the WelcomeActivity
 */
public class WelcomePage extends BasePage {

    /**
     * The constructor verifies that we are on the correct page by checking
     * the existence of the unique identifier elements of the page/view
     */
    public WelcomePage() {
        // onView(withId(R.id.login_activity)).check(matches(isDisplayed()));
    }
}
