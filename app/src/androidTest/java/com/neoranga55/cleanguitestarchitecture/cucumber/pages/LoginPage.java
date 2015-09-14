package com.neoranga55.cleanguitestarchitecture.cucumber.pages;

import com.neoranga55.cleanguitestarchitecture.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * This class will expose and implement all behaviors and most checks of the LoginActivity
 */
public class LoginPage extends BasePage {

    /**
     * The constructor verifies that we are on the correct page by checking
     * the existence of the unique identifier elements of the page/view
     */
    public LoginPage() {
        onView(withId(R.id.login_activity)).check(matches(isDisplayed()));
    }

    /**
     * Perform the login and return the next Welcome page/view
     * @param userName Name of the user to login
     * @param password Password of the user to login
     * @return Welcome page/view
     */
    public WelcomePage doLogin(String userName, String password) {
        onView(withId(R.id.username)).perform(typeText(userName));
        onView(withId(R.id.password)).perform(typeText(password));
        onView(withId(R.id.login_button)).perform(click());
        return new WelcomePage();
    }
}
