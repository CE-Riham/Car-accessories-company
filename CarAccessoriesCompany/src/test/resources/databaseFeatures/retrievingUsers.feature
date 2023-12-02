Feature: Retrieve users from database

  Scenario Outline: I retrieve from users entity
    Given I'm connected to a database to retrieve users
    When I fill in condition with "<condition>" for users
    And I want to retrieve 'users' users
    Then I should see "<message>" for retrieving users
    And close the connection after retrieving users
  Examples:
    | condition                                              | message                                        |
    |                                                        | Retrieving users successfully                  |
    | where username = 'rihamkatout'                         | Retrieving users successfully                  |
    | where username = rihamkatout                           | Error while retrieving users from database     |
    | where username = 'nousername'                          | Retrieving users successfully                  |
    | where phone = '0599119482'                             | Retrieving users successfully                  |
    | where email = 'rihamk@gm.c'                            | Retrieving users successfully                  |
    | where firstName = 'Riham'                              | Retrieving users successfully                  |
    | where lastName = 'Katout'                              | Retrieving users successfully                  |
    | where username = 'rihamkatout' AND firstName = 'Riham' | Retrieving users successfully                  |
    | where latName = 'Katout'                               | Error while retrieving users from database     |
    | where lastName = 'Katout' ANDD firstName = 'Riham'     | Error while retrieving users from database     |
