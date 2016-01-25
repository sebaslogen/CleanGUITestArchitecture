[![Build Status](https://travis-ci.org/neoranga55/CleanGUITestArchitecture.svg?branch=master)](https://travis-ci.org/neoranga55/CleanGUITestArchitecture)

# Clean GUI Test Architecture
Sample project of Android GUI test automation using Espresso, Cucumber and the Page Object Pattern

The evolution journey of Android GUI testing
============
The code in this repository serves as a support example of all the test solutions discussed in this published article:

https://medium.com/@neoranga55/the-evolution-journey-of-android-gui-testing-f65005f7ced8

Execute the project
-------
Open the project in Android Studio and select the gradle task '**connectedCheck**'

Alternative, from the command line run ```gradlew connectedCheck```

Cucumber supports **filtering execution of test scenarios with tags** (i.e. @login-scenarios). To filter by tags you have two options (both options can't be combined):
- Hard coded tags in annotation ```@CucumberOptions``` inside ```CucumberTestCase.java```
- Use parameters in command line like ```./gradlew connectedAndroidTest -Ptags="@login-scenarios,@kitkat"```

More information about how to use and combine [Cucumber tags here](https://github.com/cucumber/cucumber/wiki/Tags).

_Note: Make sure to connect a phone to the computer or start an emulator before running the tests._

In a nutshell
-------
The sample test code can be summarized in these three elements:

1- Feature file describing the test scenario in English:
```gherkin
@ScenarioId("MyApp-135") @login-scenarios
Scenario: User can login with valid user name and password
    Given I see the login page
    When I login with user name "Sebas" and password "passion"
    Then I see the welcome page
    And the title is "Welcome Sebas"
```

2- Java glue code to translate English to Java (this is a step definition):
```java
@Given("^I see the login page$")
public void i_see_the_login_page() {
    mCurrentPage = new LoginPage();
}

@When("^I login with user name \"(.+)\" and password \"(.+)\"$")
public void i_login_with_username_and_password(final String userName, final String password) {
    mCurrentPage = mCurrentPage.is(LoginPage.class).doLogin(userName, password);
}

@Then("^I see the welcome page$")
public void i_see_the_welcome_page() {
    mCurrentPage.is(WelcomePage.class);
}

@And("^the title is \"(.+)\"$")
public void the_title_is(final String title) {
    mCurrentPage.is(WelcomePage.class).checkTitle(title);
}
```

3- Page Object class implementing the interactions between tests and tested application:
```java
/**
 * Perform the login and return the next Welcome page/view
 * @param userName Name of the user to login
 * @param password Password of the user to login
 * @return Welcome page/view
 */
public WelcomePage doLogin(String userName, String password) {
    onView(withId(R.id.username)).perform(typeText(userName));
    onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
    onView(withId(R.id.login_button)).perform(click());
    return new WelcomePage();
}
```

Advanced scenarios
-------
When the code of your application and tests mature enough you will be doing things like this:
- A test step from a test scenario that can be reused across multiple tests:
```gherkin
    Given I login into premium account
```
- The step definition describes a lot of steps that need to happen to perform the requested action:
```java
@Given("^I login into premium account$")
public void i_log_in_to_premium() {
    mCurrentPage = mCurrentPage
      .is(MainPage.class).openMenu()
      .is(MenuPage.class).selectMenuItem("Accounts")
      .is(AccountsPage.class).selectNewAccountLogin()
      .is(LoginPage.class).doLogin()
      .is(AgreementPage.class).agreeToPrivacyInformation();
}
```
This step definition still hides most of the implementation details inside the Page Objects that contain the actual how-to communicate with the tested application.

License
-------
This content is released under the MIT License: http://opensource.org/licenses/MIT
