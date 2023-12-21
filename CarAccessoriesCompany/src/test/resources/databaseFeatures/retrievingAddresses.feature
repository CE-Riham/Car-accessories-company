Feature: Retrieve addresses from database

  Scenario Outline: I retrieve from addresses entity
    Given I'm connected to a database to retrieve addresses
    When I fill in condition with "<condition>" for addresses
    And I want to retrieve 'addresses' addresses
    Then I should see "<message>" for retrieving addresses
    And close the connection after retrieving addresses
    Examples:
      | condition                                          | message                                        |
      |                                                    | Retrieving addresses successfully              |
      | where city = 'Nablus'                              | Retrieving addresses successfully              |
      | where country = 'Palestine'                        | Retrieving addresses successfully              |
      | where country = 'FakeCountry'                      | Retrieving addresses successfully              |
      | where street = 'Sikkah street'                     | Retrieving addresses successfully              |
      | where country = 'Palestine' AND city = 'Nablus'    | Retrieving addresses successfully              |
      | where country = 'Palestine' ANDDDD city = 'Nablus' | Error while retrieving addresses from database |

