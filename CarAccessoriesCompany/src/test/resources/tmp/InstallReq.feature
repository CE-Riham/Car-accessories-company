#Feature: Installation Requests
#
#  Scenario: Customers request installation services
#
#    Given the customer wants to request installation services
#    When they fill out the installation request form
#    And provide details such as car make/model and preferred date
#    Then the system should validate and store the installation request
#
#  Scenario: Check installer availability
#
#    Given the customer has submitted an installation request
#    When the system checks for installer availability
#    Then the system should provide available time slots
#
#  Scenario: Schedule an installation appointment
#
#    Given the customer has received available time slots
#    When they select a preferred appointment time
#    Then the system should schedule the installation appointment
#
#  Scenario: View scheduled installations
#
#    Given the customer wants to view their scheduled installations
#    When they access the scheduled installations section
#    Then the system should display a list of upcoming installations