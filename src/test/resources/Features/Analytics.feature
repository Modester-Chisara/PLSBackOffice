@RegressionTest @Analytics
Feature: Analytics Features

  Background: Analytics pre-conditions
    Given "Client2" logged into the app "/login"
    And clicks to "Analytics" module

  @TEST_NEPX-1721 @TESTSET_NEPX-1667 @SmokeTest
  Scenario: Navigate To Analytics
    Then User lands on Analytics Page

  @TEST_NEPX-1727 @TESTSET_NEPX-1667 @SmokeTest
  Scenario: Analytics - Hide Advanced
    When User clicks on the 'Hide Advanced' button
    Then User views the Search Facets container will be disappear

  @TEST_NEPX-1727
  Scenario: Analytics - Show Advanced
    When User clicks on the 'Show Advanced' button
    Then User views the Search Facets container will be enabled to create query search