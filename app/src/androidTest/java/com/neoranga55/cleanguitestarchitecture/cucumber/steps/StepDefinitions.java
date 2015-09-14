package com.neoranga55.cleanguitestarchitecture.cucumber.steps;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import com.neoranga55.cleanguitestarchitecture.LoginActivity;
import com.neoranga55.cleanguitestarchitecture.cucumber.pages.BasePage;
import com.neoranga55.cleanguitestarchitecture.cucumber.pages.LoginPage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * This defines all the translations from Gherkin (semi-English) sentences to Java
 */
public class StepDefinitions extends ActivityInstrumentationTestCase2<LoginActivity> {

    public static final String TAG = StepDefinitions.class.getSimpleName();
    private Context mContext;
    private BasePage mCurrentPage;
    private Activity mActivity;

    public StepDefinitions() {
        super(LoginActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mContext = getInstrumentation().getContext();
        mActivity = getActivity(); // Start Activity before each test scenario
        assertNotNull(mActivity);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        // All clean up of application after each scenario must happen here
    }
    /**
     *
     * Given I see the login page
     * When I login with user name "Sebas" and password "pasión"
     * Then I see the welcome page
     * And the title is "Welcome Sebas"
     *
     */
    @Given("^I see the login page$")
    public void i_see_the_login_page() {
        mCurrentPage = new LoginPage();
    }

    @Given("^I login with user name \"(.+)\" and password \"(.+)\"$")
    public void i_login_with_username_and_password(final String userName, final String password) {
        mCurrentPage = mCurrentPage.is(LoginPage.class).doLogin(userName, password);
    }
}
