Feature: Login in the application

  @ScenarioId("MyApp-135") @login-scenarios
  Scenario: User can login with valid user name and password
    Given I see the login page
    When I login with user name "Sebas" and password "passion"
    Then I see the welcome page
    And the title is "Welcome Sebas"