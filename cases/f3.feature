Feature: Information for the product
  user should be asked for the his name and description for carpets he would to clean
  user should insert picture for carpets would to clean
  Scenario: empty information
    When user click on insert order and flag is 'true'
    Then all field should be with 'error'

  Scenario: a successful Information
    When user click on insert order and flag is 'true'
    And he fill in 'Name' with 'carpet'
    And he fill in 'Quantity' with "4"
    And he fill in 'size' with '2'
    And he fill in 'color' with 'Red'
    And he fill in 'picture' with extension 'a.png'
    And he presses 'save' and flag is 'true'
    Then show massage 'information has been entered successfully'

  Scenario Outline: errors with input
    When user click on insert order and flag is 'true'
    And he fill in a 'Name' with '<Name>'
    And he fill in a 'Quantity' with '<Quantity>'
    And he fill in a 'size' with '<size>'
    And he fill in a 'color' with '<color>'
    And he fill in a 'picture' with extension '<picture>'
    And he presses 'save' and flag is 'true'
    Then the user should see '<message>'

    Examples:
      | Name   | Quantity | size    | color | picture | message                                 |
      | carpet | -2       | (2,4)   | Red   | png     | Quantity must be positive integer       |
      | ground | 4        | (2,4)   | Red   | png     | Invalid Name, please check it           |
      | carpet | 4        | (-2,4)  | Red   | png     | Size must be positive number            |
      | carpet | 4        | (2,-4)  | Red   | png     | Size must be positive number            |
      | carpet | 4        | (-2,-4) | Red   | png     | Size must be positive number            |
      | carpet | 4        | (0,2)   | Red   | png     | Size must be more than 0                |
      | carpet | 4        | (2,0)   | Red   | png     | Size must be more than 0                |
      | carpet | 0        | (2,4)   | Red   | png     | Quantity must be more than 0            |
      | carpet | 4        | (2,4)   | Red   | exe     | The extension of picture should be .png |
      | carpet | 4        | (0,0)   | Red   | png     | The Size must be more than 0            |