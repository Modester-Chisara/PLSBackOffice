@RegressionTest @SmokeTest @Dashboard
Feature: Dashboard Features

  Background: Dashboard Features Pre-Conditions
    Given "Client2" logged into the app "/login"
    And User navigates to "Dashboard"

  @TEST_NEPX-1685
  Scenario: Dashboard - Hide Advanced
    When User clicks on the 'Hide Advanced' button
    Then User views the Search Facets container will be disappear

  @TEST_NEPX-1685
  Scenario: Dashboard - Show Advanced
    When User clicks on the 'Show Advanced' button
    Then User views the Search Facets container will be enabled to create query search

  @TEST_NEPX-1673
  Scenario: Dashboard - Quick Search Using 3 Num of Security ISIN
    When User enters "US1" as search value on quick search input box
    And User selects from dropdown as "ISIN"
    Then User views searched "US1" of "ISIN" with matching results

  @TEST_NEPX-1675
  Scenario: Dashboard - Quick Search Using 3 Char of Security Name
    When User enters "BMW" as search value on quick search input box
    And User selects from dropdown as "Security_Name"
    Then User views searched "BMW" of "Security_Name" with matching results

  @TEST_NEPX-1677
  Scenario Outline: Dashboard - Quick Search By Security ISIN
    When User enters "<ISIN>" as search value on quick search input box
    And User selects from dropdown as "ISIN"
    Then User views searched "<ISIN>" of "ISIN" with matching results
    Examples:
      | ISIN         |
      | XS2446386356 |
      | US404280BB43 |
      | FR0014008MT2 |

  @TEST_NEPX-1677 @NewScenario
  Scenario Outline: Dashboard - No Rows To Show display when unknown ISIN searched
    When User enters "<ISIN>" as search value on quick search input box
    And User selects from dropdown as "ISIN"
    Then The system display No Rows To Show modal
    Examples:
      | ISIN          |
      | US0351111BB42 |
      | DE000DXA0FB4  |
      | US40428AAG43  |

  @TEST_NEPX-1677
  Scenario Outline: Dashboard - Quick Search By Security CUSIP
    When User enters "<CUSIP>" as search value on quick search input box
    And User selects from dropdown as "CUSIP"
    Then User views searched "<CUSIP>" of "CUSIP" with matching results
    Examples:
      | CUSIP     |
      | 713448EU8 |
      | 80414L2H7 |

  @TEST_NEPX-1677
  Scenario Outline: Dashboard - No Rows To Show display when unknown CUSIP searched
    When User enters "<CUSIP>" as search value on quick search input box
    And User selects from dropdown as "CUSIP"
    Then The system display No Rows To Show modal
    Examples:
      | CUSIP     |
      | 713448AAG |
      | 61747AAG6 |
      | BV4153391 |

  @TEST_NEPX-1678
  Scenario Outline: Dashboard - Quick Search By Security Name
    When User enters "<Security Name>" as search value on quick search input box
    And User selects from dropdown as "Security_Name"
    Then User views searched "<Security Name>" of "Security_Name" with matching results
    Examples:
      | Security Name |
      | ABIBB         |
      | AABOND        |
      | NAB           |

  @TEST_NEPX-1678 @NewScenario
  Scenario Outline: Dashboard - No Rows To Show display when unknown Security Name searched
    When User enters "<Security Name>" as search value on quick search input box
    And User selects from dropdown as "Security_Name"
    Then The system display No Rows To Show modal
    Examples:
      | Security Name |
      | AAGS          |
      | AABJK         |
      | GFB           |

  @TEST_NEPX-1679
  Scenario: Dashboard - Quick Search Clear Button
    And User enters "BMW" as search value on quick search input box
    And User selects from dropdown as "Security_Name"
    When User clicks on the Clear button on Axe Grid
    Then Quick search input box cleared from Search filter

  @TEST_NEPX-1681
  Scenario Outline: Dashboard - Creating Query Search Using (ISIN, Currency)
    And User enters ISIN on the "<ISIN>" input box on the Search Facet container
    And User selects Currency "<Currency>" from the dropdown
    When User clicks on the 'Search Query' button
    Then User views responded data to Search Query on Axe Grid expected as ISIN "<ISIN>" and Currency "<Currency>"
    Examples:
      | ISIN         | Currency |
      | US89114TZA32 | USD      |
      | DE000NWB0AS6 | GBP      |
      | XS2384473992 | EUR      |

  @TEST_NEPX-1682 @dashTest
  Scenario Outline: Dashboard - Creating Query Search Using (Sector, Currency)
    And User selects Sector "<Sector>" from the dropdown box
    And User selects Currency "<Currency>" from the dropdown
    When User clicks on the 'Search Query' button
    Then User views responded data to Search Query on Axe Grid expected as Sector "<Sector>" and Currency "<Currency>"
    Examples:
      | Sector    | Currency |
      | ABS       | USD      |
      | Banks     | EUR      |
      | Chemicals | GBP      |

  @TEST_NEPX-1683 @dashTest
  Scenario Outline: Dashboard - Creating Query Search Using (Side, Price)
    And User selects Side "<Side>" from the dropdown
    And User enters min Price "<Min>" and max Price "<Max>" on to 'Price' input box
    When User clicks on the 'Search Query' button
    Then User views responded data to Search Query on Axe Grid expected as Min Price "<Min>", Max Price "<Max>" and Side "<Side>"
    Examples:
      | Min | Max | Side |
      | 50  | 100 | BID  |
      | 60  | 80  | ASK  |

  @TEST_NEPX-1684 @dashTest
  Scenario Outline: Dashboard - Creating Query Search Using (Side, Currency, Sector) and Save Query
    And User selects Side "<Side>" from the dropdown
    And User selects Currency "<Currency>" from the dropdown
    And User selects Sector "<Sector>" from the dropdown box
    And User clicks on the 'Search Query' button
    And User saves the query
    When User filters out with saved query from my queries list
    Then User views responded data to saved query on Axe Grid expected as Currency "<Currency>" and Sector "<Sector>"

    Examples:
      | Side     | Currency | Sector    |
      | BID      | USD      | Sovereign |
      | ASK      | GBP      | Retail    |
      | Any Side | EUR      | Insurance |

  @TEST_NEPX-1686 @dashTest
  Scenario Outline: Dashboard - Creating Query Search Using (Security Name, Sector) and Save Query
    And User enters Security Name on the "<Security>" input box on the Search Facet container
    And User selects Sector "<Sector>" from the dropdown box
    And User clicks on the 'Search Query' button
    And User saves the query
    When User filters out with saved query from my queries list
    Then User views responded data to saved query on Axe Grid expected as Security "<Security>" and Sector "<Sector>"
    Examples:
      | Security        | Sector              |
      | BNP PARIBAS S.A | Banks               |
      | Apple           | Technology          |
      | BMW             | Automobiles & Parts |

  @TEST_NEPX-1687
  Scenario: Dashboard - Shared Queries
    And User navigates to Saved Queries on Search Facets container
    And User clicks on the Shared button on the dedicated Query
    And User selects users from the list
    And User clicks on the 'Share Query' button
    Then User receives a pop-up message saying Saved Query Shared with query information

  @TEST_NEPX-1688 @Export
  Scenario: Dashboard - Exporting Raw Data
    And User clicks on the 'Export' button
    When User clicks on 'Download Raw Data' from the dropdown
    Then User gets a confirmation message and "Raw Data" downloaded with matching data

  @TEST_NEPX-1689 @Export
  Scenario: Dashboard - Exporting Axe Report
    And User clicks on the 'Export' button
    When User clicks on 'Download Axe Report' from the dropdown
    Then User gets a confirmation message and "Axe Report" downloaded with matching data

  @TEST_NEPX-1690 @Export
  Scenario: Dashboard - Exporting Cell Data
    And User right-clicks anywhere inside of the Axe Grid
    When User clicks on 'Export Cell Data' from the dropdown
    Then User gets a confirmation message and "CSV Export" downloaded with matching data

#  @TEST_NEPX-1692
#  Scenario: Dashboard - Adding Multiple Securities to WatchList
#    And User selects a multiple security checkbox
#    And User clicks on the 'Add to Watchlist' button
#    And clicks to "Watchlists" module
#    When User navigates to 'Watchlists'
#    Then User views the security in the Watchlists

  @wip
  Scenario Outline: Dashboard - No Axes Available Modal display
    And User enters "<security>" reference to security input box
    And User hit enter
    Then The system display No Axes Available Modal
    Examples:
      | security  |
      | 61747YDU6 |
      | 742718FD6 |


  Scenario: Dashboard - Close the No Axes Available Modala

  Scenario: Dashbaord - No Axes Available Modal navigate to Market Depth

  Scenario: Dashbaord - Market Depth of No Axes Available Modal display 1M data as defualt
