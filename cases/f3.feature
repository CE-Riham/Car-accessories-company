Feature: Product Catalog

  Scenario: Organize Products into Categories

    Given the user is on the homepage
    When they navigate to the product catalog
    Then they should see categories like Interior, Exterior, and Electronics

  Scenario: View Detailed Product Listings

    Given the user is in the product catalog
    When they select a category, e.g., Interior
    Then they should see a list of detailed product listings

  Scenario: Product Details Include Descriptions, Images, Prices, and Availability

    Given the user is viewing a product listing
    When they click on a specific product
    Then they should see detailed information such as descriptions, images, prices, and availability

  Scenario: Search Products

    Given the user is in the product catalog
    When they use the search bar to find a specific product
    Then they should see relevant results matching their search query

  Scenario: Filter Products

    Given the user is in the product catalog
    When they apply filters, such as price range or brand
    Then they should see a refined list of products based on the selected filters