Feature: Login feature
  I want to login to car accessories

  Scenario Outline: login scenarios
    Given user is connected to the database
    When he fills in 'username' with '<username>' for login
    And he fills in 'password' with '<password>' for login
    And user clicks on login
    Then user should see '<message>' for login
    And close the connection

    Examples:
      | username      | password    | message                      |
      | rihamkatout   | 1234**Aa    | Valid username and password  |
      | rihamkatout2  | 1234**Aa    | Valid username and password  |
      | rihamkatout3  | 1234**Aa    | Valid username and password  |
      | rihamkatout9  | 123456      | Invalid username or password |
      | rihamkatout   | 12de456     | Invalid username or password |
      |               | 12de456     | Invalid username or password |
      | rihamkatout   |             | Invalid username or password |

