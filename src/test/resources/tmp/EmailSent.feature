Feature: Send Email

  Scenario: Sending an email
    Given the user is ready to send an email
    When they send an email with subject "Test Subject" and body "This is a test email"
    Then the email should be sent successfully
