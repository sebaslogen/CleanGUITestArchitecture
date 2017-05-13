Feature: Login in the application

  @ScenarioId("FUNCTIONAL.AUTH.SCN.001") @UserStory("MyApp-135") @login-scenarios
  Scenario: User can login with valid user name and password
    Given I see the login page
    When I login with user name "Sebas" and password "passion"
    Then I see the welcome page
    And the title is "Welcome Sebas"

  @ScenarioId("FUNCTIONAL.AUTH.SCN.002") @UserStory("MyApp-135") @login-scenarios
  Scenario: User can login with valid second user name and password
    Given I see the login page
    When I login with user name "sebaslogen" and password "passion"
    Then I see the welcome page
    And the title is "Welcome sebaslogen"

  @ScenarioId("FUNCTIONAL.AUTH.SCN.003") @UserStory("MyApp-135") @login-scenarios
  Scenario: User can login with valid third user name and password
    Given I see the login page
    When I login with user name "ThirdUser" and password "passion"
    Then I see the welcome page
    And the title is "Welcome ThirdUser"