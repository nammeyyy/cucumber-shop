Feature: Buy products
    As a customer
    I want to buy products

Background:
    Given the store is ready to service customers
    And a product "Bread" with price 20.50 and stock of 3 exists
    And a product "Jam" with price 80.00 and stock of 10 exists
    And a product "Butter" with price 45.00 and stock of 10 exists

Scenario: Buy one product
    When I buy "Bread" with quantity 2
    Then total should be 41.00

Scenario: Buy multiple products
    When I buy "Bread" with quantity 2
    And I buy "Jam" with quantity 1
    Then total should be 121.00

Scenario: Buy three product
    When I buy "Bread" with quantity 2
    And I buy "Jam" with quantity 3
    And I buy "Butter" with quantity 2
    Then total should be 371.00

Scenario: Check Stock
    When I buy "Bread" with quantity 6
    Then I cannot buy the product due to insufficient stock

Scenario Outline: Buy three product
    When I buy "Bread" with quantity 3
    Then total should be 61.50
    Examples:
        | product | quantity | total |
        | "Bread" |    1     | 20.50 |
        | "Jam"   |    2     | 160.00 |
        |"Butter" |    1     | 45.00 |