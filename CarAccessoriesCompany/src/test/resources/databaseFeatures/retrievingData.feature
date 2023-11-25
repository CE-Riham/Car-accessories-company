Feature: Select data from database

  Scenario Outline: User retrieves various data
    Given I'm connected to a database
    When I fill in condition with "<condition>"
    And I want to retrieve '<entity>'
    Then I should see "<message>"
    And close the connection
  Examples:
  | entity           | condition                                          | message                                        |
  | users            |                                                    | Retrieving users successfully                  |
  | users            | username = 'rihamkatout'                           | Retrieving users successfully                  |
  | users            | username = rihamkatout                             | Error while retrieving users from database     |
  | users            | username = 'nousername'                            | Retrieving users successfully                  |
  | users            | phone = '0599119482'                               | Retrieving users successfully                  |
  | users            | email = 'rihamk@gm.c'                              | Retrieving users successfully                  |
  | users            | firstName = 'Riham'                                | Retrieving users successfully                  |
  | users            | lastName = 'Katout'                                | Retrieving users successfully                  |
  | users            | username = 'rihamkatout' AND firstName = 'Riham'   | Retrieving users successfully                  |
  | users            | latName = 'Katout'                                 | Error while retrieving users from database     |
  | users            | lastName = 'Katout' ANDD firstName = 'Riham'       | Error while retrieving users from database     |
  | addresses        |                                                    | Retrieving addresses successfully              |
  | addresses        | city = 'Nablus'                                    | Retrieving addresses successfully              |
  | addresses        | country = 'Palestine'                              | Retrieving addresses successfully              |
  | addresses        | country = 'FakeCountry'                            | Retrieving addresses successfully              |
  | addresses        | street = 'Sikkah street'                           | Retrieving addresses successfully              |
  | addresses        | country = 'Palestine' AND city = 'Nablus'          | Retrieving addresses successfully              |
  | addresses        | country = 'Palestine' ANDDDD city = 'Nablus'       | Error while retrieving addresses from database |
  | invalidEntity    | country = 'Palestine' ANDDDD city = 'Nablus'       | Error while retrieving from database           |

