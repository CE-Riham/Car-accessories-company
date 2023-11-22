Feature: User Sign-Up
  I want to sign up for car accessories

  Scenario: Successful Sign-Up
    When I click on sign up and flag is 'true'
    And he fills in 'ID' with '704123456'
    And he fills in 'Phone Number' with '0595014020'
    And he fills in 'VIN' with '0599585810'
    And he fills in 'Address' with 'nablus-rafidia'
    And he fills in 'Gmail' with 'shahd22@gmail.com'
    And he fills in 'user name' with 'shahd28'
    And he fills in 'Password' with '1234**Aa'
    And he fills in 'Model' with '2011'
    And he fills in 'credit card' with '409841315'
    And he presses 'sign up' and flag is 'true'
    Then there should be a user 'shahd28'

  #noinspection GherkinBrokenTableInspection
  Scenario Outline: Errors with Input
    When I click on sign up and flag is 'true'
    And he fills in 'ID' with '<ID>'
    And he fills in 'Phone Number' with '<Phone Number>'
    And he fills in 'VIN' with '<VIN>'
    And he fills in 'credit card' with '<credit card>'
    And he fills in 'Address' with '<Address>'
    And he fills in 'Gmail' with '<Gmail>'
    And he fills in 'user name' with '<user name>'
    And he fills in 'Password' with '<Password>'
    And he fills in 'Model' with '<Model>'
    And he presses 'sign up' and flag is 'true'
    Then the user should see '<message>'

    Examples:
      | ID       | Phone Number | VIN       | credit card | Address        | Gmail           | user name | Password | Model | message                     |
      | 70412345  | 0595014020   | 0599585810 | 409841315  | nablus-rafidia | shahd22@gmail.com | shahd28   | 1234**Aa | 2011  | ID must be 9 numbers not less |
      | 7041234567 | 0595014020  | 0599585810 | 2222        | nablus-rafidia | shahd22@gmail.com | shahd28   | 1234**Aa | 2010  | ID must be 9 numbers not more |
      | 70412345a | 0595014020   | 0599585810 | 2222        | nablus-rafidia | shahd22@gmail.com | shahd28   | 1234**Aa | 2011  | ID must not contain characters |
      | 704123456 | 0595014020   | 0599585810 | 2222        | nablus-rafidia | shahd22@gmail.com | shahd28   | 1234**Aa | 2010  | ID must be 9 numbers |
      | 704123456 | 059501402    | 0599585810 | 2222        | nablus-rafidia | shahd22@gmail.com | shahd28   | 1234**Aa | 2010  | Phone Number must be 10 numbers not less |
      | 704123456 | 05950140200 | 0599585810 | 2222        | nablus-rafidia | shahd22@gmail.com | shahd28   | 1234**Aa | 2010  | Phone Number must be 10 numbers not more |
      | 704123456 | 059501402a  | 0599585810 | 2222        | nablus-rafidia | shahd22@gmail.com | shahd28   | 1234**Aa | 2010  | Phone Number must not contain characters |
      | 704123456 | 0595014020   | 059958581  | 2222        | nablus-rafidia | shahd22@gmail.com | shahd28   | 1234**Aa | 2010  | Phone Number must be 10 numbers not less |
      | 704123456 | 0595014020   | 05995858100 | 2222       | nablus-rafidia | shahd22@gmail.com | shahd28   | 1234**Aa | 2010  | Phone Number must be 10 numbers not more |
      | 704123456 | 0595014020   | 059958581a  | 2222       | nablus-rafidia | shahd22@gmail.com | shahd28   | 1234**Aa | 2010  | Phone Number must not contain characters |
#      | 704123456 | 0595014020   | 0599585810






















#Feature: a user sign up
#  I want to sign up to car accessories
#  Scenario: a successful signup
#    When I click on sign up and flag is 'true'
#    And he fills in 'ID' with '704123456'
#    And he fills in 'Phone Number' with '0595014020'
#    And he fills in 'VIN' with '0599585810'
#    And he fills in 'Adress' with 'nablus-rafidia'
#    And he fills in 'Gmail' with 'shahd22@gmail.com'
#    And he fills in 'user name' with 'shahd28'
#    And he fills in 'Password' with '1234**Aa'
#    And he fills in 'Model' with '2011'
#    And he fills in 'credit card' with '409841315'
#
#    And he presses 'sign up' and flag is 'true'
#    Then there should be a user 'shahd31'
#
#  #noinspection GherkinBrokenTableInspection
#  Scenario Outline: errors with input
#    When I click on sign up and flag is 'true'
#    And he fills in 'ID' with '<ID>'
#    And he fills in 'Phone Number' with '<Phone Number>'
#    And he fills in 'VIN' with '<VIN>'
#    And he fills in 'credit card' with '<credit card>'
#    And he fills in 'Adress' with '<Adress>'
#    And he fills in 'Gmail' with '<Gmail>'
#    And he fills in 'user name' with '<user name>'
#    And he fills in 'Password' with '<Password>'
#    And he fills in 'Model' with '<Model>'
#    And he presses 'sign up' and flag is 'true'
#    Then the user should see '<massage>'
#   Examples:
#    | ID       |Phone Number | VIN       | credit card |Adress        | Gmail           | user name | Password |Model|| massage                     |
#    |70412345  |0595014020   |0599585810 |409841315    |nablus-rafidia|shahd22@gmail.com|shahd28    |1234**Aa  |2011  |ID must be 9 number not less |
#      |7041234567|0595014020   |0599585810 |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234**Aa  |2010  |ID must be 9 number not more|
#      |70412345a |0595014020   |0599585810 |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234**Aa  |2011  |ID must not contain character |
#      |704123456 |0595014020   |0599585810 |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234**Aa  |2010  |ID must be 9 number |
#      |704123456 |059501402    |0599585810 |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234**Aa  |2010  |Phone Number must be 10 number not less |
#      |704123456 |05950140200  |0599585810 |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234**Aa  |2010  |Phone Number must be 10 number not more |
#      |704123456 |059501402a   |0599585810 |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234**Aa  |2010  |Phone Number must not contain character |
#      |704123456 |0595014020   |059958581  |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234**Aa  |2010  |Phone Number must be 10 number not less |
#      |704123456 |0595014020   |05995858100|2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234**Aa  |2010  |Phone Number must be 10 number not more |
#      |704123456 |0595014020   |059958581a |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234**Aa  |2010  |Phone Number must not contain character |
#      |704123456 |0595014020   |0599585810 |2222         |nablus-rafidia|shahd22.com      |shahd28    |1234**Aa  |2010  |email must be at least 5 characters long |
#      |704123456 |0595014020   |0599585810 |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234**Aa  |2010  |email must be at least 5 characters long |
#      |704123456 |0595014020   |0599585810 |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234      |2010  |Password must be at least 5 characters long |
#      |704123456 |0595014020   |0599585810 |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |12345678  |2010  |Password must contain character |
#      |704123456 |0595014020   |0599585810 |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234567a  |2010  |Password must contain small and capital character |
#      |704123456 |0595014020   |0599585810 |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234567A  |2010  |Password must contain small and capital character |
#      |704123456 |0595014020   |0599585810 |2222         |nablus-rafidia|shahd22@gmail.com|shahd28    |1234567*  |2010  |Password must contain small and capital character |
#      |704123456 |0595014020   |0599585810 |2222        |nablus-rafidia|shahd22@gmail.com|shahd28    |1234567aA |2010  |Password must contain spetial character |