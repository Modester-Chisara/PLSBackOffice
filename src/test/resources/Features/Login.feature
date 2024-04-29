@Login @RegressionTest
Feature: Login

  Background: Navigate To Login Page
    Given User enter to "/login"

  @TEST_NEPX-1671 @Test
  Scenario: Successful Login
    And User types "HENDR-PILOT" as COMPID
    And User types "Client1" as username
    And User types "Password11*" as password
    When User clicks on "login" button
    Then User navigates to the home page with title "Neptune - Dashboard"

  @TEST_NEPX-1671-2 @SmokeTest
  Scenario Outline: Successful Login with Multiple Users
    And User types "<COMPID>" as COMPID
    And User types "<Username>" as username
    And User types "<Password>" as password
    When User clicks on "login" button
    Then User navigates to the home page with title "Neptune - Dashboard"

    Examples:
      | Username    | COMPID           | Password    |
      | Client1     | HENDR-PILOT      | Password11* |
      | Client2     | HENDR-PILOT      | Password11* |

  @TEST_NEPX-1672 @SmokeTest @Test
  Scenario: Unsuccessful Login
    And User types "HENDR" as COMPID
    And User types "Client" as username
    And User types "Password" as password
    When User clicks on "login" button
    Then User get error message saying that "Problem logging in"

  @TEST_NEPX-1743 @TESTSET_NEPX-1667 @SmokeTest @Test
  Scenario: Change the Password
    And User clicks on "change password" button
    When User navigates to "login/reset-password"
    And "Client2" fills out the required input boxes with a new password "Password11*"
    When  User enters new password on Login page
    Then User succesfully login and navigate to "https://neptx.genesislab.global/dashboard"

  @TEST_NEPX-1741 @TESTSET_NEPX-1667 @Test
  Scenario: Log Out
    And User types "HENDR-PILOT" as COMPID
    And User types "Client2" as username
    And User types "Password11*" as password
    And User clicks on "login" button
    And User clicks on 'Profile' button
    When User clicks on 'Log Out' button
    Then User successfully logged out from the app
