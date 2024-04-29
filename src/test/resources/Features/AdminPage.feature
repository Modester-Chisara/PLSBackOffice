@RegressionTest
Feature: Admin Features

  Background: Admin features pre-conditions
    Given "Client1" logged into the app "/login"
    And clicks to "Admin" module
    And User navigates to "Admin"

#  @TEST_NEPX-1739 @TESTSET_NEPX-1667
#  Scenario: Admin Page - Add New User
#    And User clicks on "Add User" button on Admin page
#    And User fills out the required input boxes
#    When User clicks on "Submit" button on Admin page
#    Then User views the verification pop-up with the corresponding email
#
#  @TEST_NEPX-1740 @TESTSET_NEPX-1667
#  Scenario: Admin Page - Remove User
#    And User selects desired accounts checkbox "ChrisBancroft"
#    And User clicks on "Remove Users" button on Admin page
#    When User clicks on "Confirm Removal" button on Admin page
#    Then User views the user on User Management Table as Status Disabled