Feature: Retrieve data from database

  Scenario Outline: I retrieve from users entity
    Given I'm connected to a database to retrieve data
    When I fill in condition with "<condition>"
    And I want to retrieve 'users'
    Then I should see "<message>" for retrieving data
    And close the connection
  Examples:
    | condition                                          | message                                        |
    |                                                    | Retrieving users successfully                  |
    | username = 'rihamkatout'                           | Retrieving users successfully                  |
    | username = rihamkatout                             | Error while retrieving users from database     |
    | username = 'nousername'                            | Retrieving users successfully                  |
    | phone = '0599119482'                               | Retrieving users successfully                  |
    | email = 'rihamk@gm.c'                              | Retrieving users successfully                  |
    | firstName = 'Riham'                                | Retrieving users successfully                  |
    | lastName = 'Katout'                                | Retrieving users successfully                  |
    | username = 'rihamkatout' AND firstName = 'Riham'   | Retrieving users successfully                  |
    | latName = 'Katout'                                 | Error while retrieving users from database     |
    | lastName = 'Katout' ANDD firstName = 'Riham'       | Error while retrieving users from database     |


  Scenario Outline: I retrieve from addresses entity
    Given I'm connected to a database to retrieve data
    When I fill in condition with "<condition>"
    And I want to retrieve 'addresses'
    Then I should see "<message>" for retrieving data
    And close the connection
    Examples:
      | condition                                          | message                                        |
      |                                                    | Retrieving addresses successfully              |
      | city = 'Nablus'                                    | Retrieving addresses successfully              |
      | country = 'Palestine'                              | Retrieving addresses successfully              |
      | country = 'FakeCountry'                            | Retrieving addresses successfully              |
      | street = 'Sikkah street'                           | Retrieving addresses successfully              |
      | country = 'Palestine' AND city = 'Nablus'          | Retrieving addresses successfully              |
      | country = 'Palestine' ANDDDD city = 'Nablus'       | Error while retrieving addresses from database |

