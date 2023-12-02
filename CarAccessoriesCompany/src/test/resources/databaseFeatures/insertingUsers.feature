Feature: Inserting user to database

  Scenario Outline: I want to insert a user to database
    Given I'm connected to a database to insert user
    And I want to insert "<field>" to "<newValue>"
    And I submit to insert
    Then I should see "<message>" for inserting data
    And close the connection and end inserting
    Examples:
      |field        |newValue         |condition                        |message                       |
      |username     |shahdhamad       | where username = 'shahd11'      |User was updated successfully |
      |lastName     |Muneer           | where username = 'rihamkatout2' |User was updated successfully |
      |firstName    |Riham            | where username = 'rihamkatout'  |User was updated successfully |
      |phone        |0594643780       | where username = 'rahafkatout'  |User was updated successfully |
      |email        |shahd@gmail.com  | where username = 'shahd11'      |User was updated successfully |
      |userPassword |asD2!d12         | where username = 'rahafkatout'  |User was updated successfully |
      |image        |rihamkatout3.jpg | where username = 'rihamkatout3' |User was updated successfully |
      |userType     |customer         | where username = 'shahd11'      |User was updated successfully |
      |userType     |installer        | where username = 'shahd11'      |User was updated successfully |
      |userType     |admin            | where username = 'shahd11'      |User was updated successfully |
      |lastName     |newLastName      | where username = 'shahd1'       |User was updated successfully |
      |firstName    |newFirstName     | where username = 'noUser'       |User was updated successfully |
      |phone        |0599             | where username = 'shahd11'      |Invalid phone number          |
      |phone        |05991194fd       | where username = 'shahd11'      |Invalid phone number          |
      |email        |invalidEmail     | where username = 'shahd11'      |Invalid email address         |
      |email        |invalidEmail@a   | where username = 'shahd11'      |Invalid email address         |
      |email        |invalidEmail@a.  | where username = 'shahd11'      |Invalid email address         |
      |userPassword |invalidPassword  | where username = 'shahd11'      |Invalid password              |
      |userPassword |invalidpassword2 | where username = 'shahd11'      |Invalid password              |
      |userType     |invalidType      | where username = 'shahd11'      |Invalid user type             |

