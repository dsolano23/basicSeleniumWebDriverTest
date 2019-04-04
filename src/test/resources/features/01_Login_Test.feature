@smokeTest
@login
Feature: Login Action

  Background: Generic Steps

    Given User Navigate to Home Page
    When User Navigate to LogIn Page

  @login01
  Scenario Outline: Successful Login with invalid Credentials

    Given I insert the user <UserName>
    And I go to insert pwd
    When I insert the password <Password>
    And I do summit
    Then Message displayed Login unsuccessfully

    Examples:
      | UserName           | Password  |
      | prueba@hotmail.com | prueba123 |

