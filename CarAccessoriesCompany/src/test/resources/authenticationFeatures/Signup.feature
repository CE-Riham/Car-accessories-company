Feature: User sign-Up
  I want to sign up for car accessories

  Scenario Outline: User sign-up with various inputs
    When user is in sign-up page
    And he fills in 'username' with "<Username>" for register
    And he fills in 'firstName' with "<FirstName>" for register
    And he fills in 'lastName' with "<LastName>" for register
    And he fills in 'phoneNumber' with "<PhoneNumber>" for register
    And he fills in 'password' with "<Password>" for register
    And he fills in 'email' with "<Email>" for register
    And he fills in 'userType' with "<userType>" for register
    And he submits the registration form
    Then he should see "<Message>" for register

    Examples:
      |Username |FirstName |LastName |PhoneNumber |Password     |Email             |userType |Message                                    |
      |shahd28  |Shahd     |Hamad    |0595014020  |1234**Aa     |shahd22@gmail.com |admin    |User was registered successfully           |
      |shahd18  |Shahd     |Hamad    |059501402   |1234**Aa     |shahd18@gmail.com |admin    |Invalid phone number                       |
      |shahd12  |Shahd     |Hamad    |059501402a  |1234**Aa     |shahd12@gmail.com |admin    |Invalid phone number                       |
      |shahd28  |Shahd     |Hamad    |0595014020  |weakPassword |shahd28@gmail.com |admin    |Invalid password                           |
      |shahd20  |Shahd     |Hamad    |0595014020  |1234**Aa     |shahd22ail.com    |admin    |Invalid email address                      |
      |shahd11  |Shahd     |Hamad    |0595014020  |1234**Aa     |shahd11@gmail.com |admin    |Username is already taken                  |
      |         |Shahd     |Hamad    |0595014020  |1234**Aa     |shahd29@gmail.com |admin    |Username can't be empty                    |
      |shahd19  |          |Hamad    |0595014020  |1234**Aa     |shahd29@gmail.com |admin    |First name can't be empty                  |
      |shahd19  |Shahd     |         |0595014020  |1234**Aa     |shahd29@gmail.com |admin    |Last name can't be empty                   |
      |shahd19  |Shahd     |Hamad    |            |1234**Aa     |shahd29@gmail.com |admin    |Phone number can't be empty                |
      |shahd19  |Shahd     |Hamad    |0595014020  |             |shahd29@gmail.com |admin    |Password can't be empty                    |
      |shahd19  |Shahd     |Hamad    |0595014020  |1234**Aa     |                  |admin    |Email address can't be empty               |
      |shahd28  |Shahd     |Hamad    |0595014020  |1234**Aa     |shahd22@gmail.com |         |User type can't be empty                   |
      |shahd28  |Shahd     |Hamad    |0595014020  |1234**Aa     |shahd22@gmail.com |customer |User was registered successfully           |
      |shahd28  |Shahd     |Hamad    |0595014020  |1234**Aa     |shahd22@gmail.com |installer|User was registered successfully           |
      |shahd28  |Shahd     |Hamad    |0595014020  |1234**Aa     |shahd22@gmail.com |none     |Invalid user type                          |









