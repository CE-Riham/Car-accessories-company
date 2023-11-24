#Feature: User sign-Up
#  I want to sign up for car accessories
#
#  Scenario: Successful Sign-Up
#    When user is on the sign-up page
#    And he fills in 'PhoneNumber' with '0595014020'
#    And he fills in 'Email' with 'shahd22@gmail.com'
#    And he fills in 'Username' with 'shahd28'
#    And he fills in 'Password' with '1234**Aa'
#    And he submits the registration form
#    Then he should see "success"
#
#  Scenario Outline: User sign-up with various invalid inputs
#    Given user is on the sign-up page
#    When he clicks on sign-up and flag is 'true'
#    And he fills in 'username' with "<Username>"
#    And he fills in 'firstName' with "<FirstName>"
#    And he fills in 'lastName' with "<LastName>"
#    And he fills in 'phoneNumber' with "<PhoneNumber>"
#    And he fills in 'password' with "<Password>"
#    And he fills in 'email' with "<Email>"
#    And he submits the registration form
#    Then he should see "<Message>"
#
#    Examples:
#      | Username | FirstName | LastName | PhoneNumber | Password     | Email             | Message                                    |
#      | shahd18  | Shahd     | Hamad    | 059501402   | 1234**Aa     | shahd18@gmail.com | PhoneNumber must contain exactly 10 digits |
#      | shahd12  | Shahd     | Hamad    | 059501402a  | 1234**Aa     | shahd12@gmail.com | PhoneNumber must contain only numbers      |
#      | shahd28  | Shahd     | Hamad    | 0595014020  | weakPassword | shahd28@gmail.com | Invalid Password                           |
#      | shahd20  | Shahd     | Hamad    | 0595014020  | 1234**Aa     | shahd22ail.com    | Invalid Email                              |
#      | shahd18  | Shahd     | Hamad    | 0595014020  | 1234**Aa     | shahd11@gmail.com | Username is already taken                  |
#      | shahd11  | Shahd     | Hamad    | 0595014020  | 1234**Aa     | shahd18@gmail.com | Email is already taken                     |
#      |          | Shahd     | Hamad    | 0595014020  | 1234**Aa     | shahd29@gmail.com | Missing Information                        |
#      | shahd19  |           | Hamad    | 0595014020  | 1234**Aa     | shahd29@gmail.com | Missing Information                        |
#      | shahd19  | Shahd     |          | 0595014020  | 1234**Aa     | shahd29@gmail.com | Missing Information                        |
#      | shahd19  | Shahd     | Hamad    |             | 1234**Aa     | shahd29@gmail.com | Missing Information                        |
#      | shahd19  | Shahd     | Hamad    | 0595014020  |              | shahd29@gmail.com | Missing Information                        |
#      | shahd19  | Shahd     | Hamad    | 0595014020  | 1234**Aa     |                   | Missing Information                        |
#
#
#
#
#
#
#
#
#
#
#
