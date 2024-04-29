@RegressionTest @MainModules
Feature: Main Modules

  Background: Main modules pre-conditions
    Given "Client2" logged into the app "/login"

  @SmokeTest @Test
  Scenario Outline: Verify Main Modules
    When clicks to "<Module>" module
    Then views the url changes to "<url>"
    Examples:
      | Module     | url                                        |
      | Watchlists | https://neptx.genesislab.global/watchlists |
      | Analytics  | https://neptx.genesislab.global/analytics  |
      | Admin      | https://neptx.genesislab.global/admin     |
      | Dashboard  | https://neptx.genesislab.global/dashboard |


