@RegressionTest @MarketDepth @SmokeTest
Feature: Market Depth

  Background: Market Depth Pre-Conditions
    Given "Client2" logged into the app "/login"
    When User clicks on the desired security

  @TEST_NEPX-1701 @TESTSET_NEPX-1667 
  Scenario: Dealer Bid data Comparison with Dashboard
    And User view numbers of "BID" in market depth page
    When User made quick search on the dashboard with "ISIN"
    Then User should get same "BID" numbers for the security

  @TEST_NEPX-1702 @TESTSET_NEPX-1667 
  Scenario: Dealer Ask data Comparison with Dashboard
    And User view numbers of "ASK" in market depth page
    When User made quick search on the dashboard with "ISIN"
    Then User should get same "ASK" numbers for the security

  @TEST_NEPX-1703 @TESTSET_NEPX-1667 
  Scenario: Instrument Detail Compariosn with Dashboard Page
    And User view information about the security
    When User made quick search on the dashboard with "ISIN"
    Then User should get same security information with Market Depth page

  @TEST_NEPX-1704 @TESTSET_NEPX-1667 
  Scenario: Exclude Cancels Toggle Off
    When User change the Exclude Cancels Toggle Off
    Then Market Depth include the cancels according to toggle

  @TEST_NEPX-1704 @TESTSET_NEPX-1667 
  Scenario: Exclude Cancels Toggle On
    When As default exclude cancels toggle on
    Then Market Depth exclude the cancels according to toggle

  @TEST_NEPX-1705 @TESTSET_NEPX-1667 
  Scenario: View Dealer Interest By Toggle: Live
    When User change the View Dealer Interest By toggle to Live
    Then Market Depth shows Live bids and sells

  @TEST_NEPX-1706 @TESTSET_NEPX-1667
  Scenario: View Dealer Interest By Toggle: 1W
    When User change the View Dealer Interest Toggle to 1W
    Then Market Depth data changes according to "1W" toggle

  @TEST_NEPX-1707 @TESTSET_NEPX-1667
  Scenario: View Dealer Interest By Toggle: 1M
    When User change the View Dealer Interest Toggle to 1M
    Then Market Depth data changes according to "1M" toggle

  Scenario: PDF Export
    When User clicks on "Export" button on Market Depth
    Then Verifies that file succesfully exported

