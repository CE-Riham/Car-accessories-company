Feature: Order Product

  Scenario: User orders a product
    Given the user is on the homepage
    When they choose a product
    And click on the order button
    Then the order screen should be visible

  Scenario: User does not choose a product
    Given the user is on the homepage
    When they do not choose any product
    And click on the order button
    Then nothing should happen
