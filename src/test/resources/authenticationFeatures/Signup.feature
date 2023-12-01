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
    And he submits the registration form
    Then he should see "<Message>" for register

    Examples:
      | Username | FirstName | LastName | PhoneNumber | Password     | Email             | Message                                    |
      | shahd28  | Shahd     | Hamad    | 0595014020  | 1234**Aa     | shahd22@gmail.com | User was registered successfully           |
      | shahd18  | Shahd     | Hamad    | 059501402   | 1234**Aa     | shahd18@gmail.com | Invalid phone number                       |
      | shahd12  | Shahd     | Hamad    | 059501402a  | 1234**Aa     | shahd12@gmail.com | Invalid phone number                       |
      | shahd28  | Shahd     | Hamad    | 0595014020  | weakPassword | shahd28@gmail.com | Invalid password                           |
      | shahd20  | Shahd     | Hamad    | 0595014020  | 1234**Aa     | shahd22ail.com    | Invalid email address                      |
      | shahd11  | Shahd     | Hamad    | 0595014020  | 1234**Aa     | shahd11@gmail.com | Username is already taken                  |
      |          | Shahd     | Hamad    | 0595014020  | 1234**Aa     | shahd29@gmail.com | Username can't be empty                    |
      | shahd19  |           | Hamad    | 0595014020  | 1234**Aa     | shahd29@gmail.com | First name can't be empty                  |
      | shahd19  | Shahd     |          | 0595014020  | 1234**Aa     | shahd29@gmail.com | Last name can't be empty                   |
      | shahd19  | Shahd     | Hamad    |             | 1234**Aa     | shahd29@gmail.com | Phone number can't be empty                |
      | shahd19  | Shahd     | Hamad    | 0595014020  |              | shahd29@gmail.com | Password can't be empty                    |
      | shahd19  | Shahd     | Hamad    | 0595014020  | 1234**Aa     |                   | Email address can't be empty               |











