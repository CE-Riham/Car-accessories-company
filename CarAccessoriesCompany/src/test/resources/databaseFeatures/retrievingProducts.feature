Feature: Retrieve products from database

  Scenario Outline: I retrieve from products entity
    Given I'm connected to a database to retrieve products
    When I fill in condition with "<condition>" for products
    And I want to retrieve 'products' products
    Then I should see "<message>" for retrieving products
    And close the connection after retrieving products

#  image varchar(100),
#  longDescription varchar(500) not null,
#  shortDescription varchar(100) not null,
#  availability int not null,

    Examples:
      | condition                                      | message                                       |
      |                                                | Retrieving products successfully              |
      | where productID = '1'                          | Retrieving products successfully              |
      | where productID = -1                           | Retrieving products successfully              |
      | where productName = prodName                   | Error while retrieving products from database |
      | where productName = 'Car Floor Mats'           | Retrieving products successfully              |
      | where category = 'INTERIOR'                    | Retrieving products successfully              |
      | where price < 100                              | Retrieving products successfully              |
      | where numberOfOrders > 100                     | Retrieving products successfully              |
      | where LOWER(longDescription) like '%covers%'   | Retrieving products successfully              |
      | where LOWER(shortDescription) like '%filters%' | Retrieving products successfully              |
      | where availability > 0                         | Retrieving products successfully              |
