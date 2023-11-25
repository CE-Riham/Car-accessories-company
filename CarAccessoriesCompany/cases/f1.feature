Feature: Login
  I want to login to car accessories
  Background:
    Given I go to loginchoise
    And the field '' is empty

  Scenario:empty fields
    When I click on login and flag is 'true'
    Then field '' should be with error

  Scenario: Wrong password
    When he fills in 'gmail' with 'shahd@gmail.com'
    And he fills in 'password' with '123456'
    And I click on login and flag is 'true'
    Then I should see 'E-mail or password is incorrect'

  Scenario: Login successfully
    When he fills in 'gmail' with 'shahd@gmail.com'
    And he fills in 'password' with '123456'
    And I click on login and flag is 'true'
    Then I shouldnt see 'Access your account'