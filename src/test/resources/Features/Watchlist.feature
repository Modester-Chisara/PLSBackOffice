@RegressionTest @Watchlist @SmokeTest
Feature: Watchlists Feature

  Background: Watchlist Features Pre-conditions
    Given "Client2" logged into the app "/login"
    And clicks to "Watchlists" module
    And User navigates to "Watchlists"

  @TEST_NEPX-1694 @TESTSET_NEPX-1667
  Scenario Outline: WL - Add Securities
    And User clicks on the 'Add Securities' button on Watchlists
    And User enters Security "<ISIN>" to the Add securities pop-up
    When User clicks on the 'Add All' button on Watchlists
    Then User views the single security in the Watchlists "<ISIN>"
    Examples:
      | ISIN         |
      | XS2167007249 |
      | USG6382GWT59 |

  @TEST_NEPX-1695 @TESTSET_NEPX-1667
  Scenario Outline: WL - Remove Security
    And User selects securities to remove from the Watchlist "<ISIN>"
    When User clicks on the 'Remove Security' button on Watchlists
    And User clicks on the 'Ok' button on Watchlists
    Then User will no longer see the security on the WatchList
    Examples:
      | ISIN         |
      | XS2167007249 |
      | USG6382GWT59 |

  @TEST_NEPX-1696 @TESTSET_NEPX-1667
  Scenario: WL - Upload
    And User clicks on the 'Upload' button on Watchlists
    When User clicks on the override button from the opening File Upload pop-up
    Then User views the uploaded securities in the Watchlists


  @TEST_NEPX-1698 @TESTSET_NEPX-1667
  Scenario: Edit WatchList - Saving the Changes on WatchList Page
    And User clicks on the 'Watchlists Settings' button on Watchlists
    And User navigates to "Neptune - Watchlist Settings" Page
    And User toggle on "Alert" for all securities
    When User clicks on the 'Save' button on Watchlists
    Then User navigates back to Watchlist with saving changes