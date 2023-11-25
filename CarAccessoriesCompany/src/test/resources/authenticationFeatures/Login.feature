Feature: Login feature
  I want to login to car accessories

  Scenario Outline: login scenarios
    Given user is connected to the database
    When he fills in 'username' with '<username>'
    And he fills in 'password' with '<password>'
    And user clicks on login
    Then user should see '<message>'
    And close the connection

    Examples:
      | username      | password    | message                      |
      | rihamkatout   | 123456      | Valid username and password  |
      | rihamkatout2  | 123456      | Valid username and password  |
      | rihamkatout3  | 123456      | Valid username and password  |
      | rihamkatout9  | 123456      | Invalid username or password |
      | rihamkatout   | 12de456     | Invalid username or password |
      |               | 12de456     | Invalid username or password |
      | rihamkatout   |             | Invalid username or password |

