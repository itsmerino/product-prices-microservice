Feature: Get product prices

  As a customer
  I want to get the prices of the products for different dates
  So that I can buy them

  Background:
    Given the following products:
    | ProductId | BrandId | Priority | Rate | StartDate           | EndDate             | Price | Currency |
    | 35455     | 1       | 0        | 1    | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 35.50 | EUR      |
    | 35455     | 1       | 1        | 2    | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 25.45 | EUR      |
    | 35455     | 1       | 1        | 3    | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 30.50 | EUR      |
    | 35455     | 1       | 1        | 4    | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 38.95 | EUR      |

  Scenario: Request at 10:00 on the 14th for product 35455 and brand 1
    When a customer requests the product 35455 of the brand 1 at date "2020-06-14-10.00.00"
    Then the product is returned with the following data:
    | Field     | Value                |
    | id        | 35455                |
    | brandId   | 1                    |
    | rate      | 1                    |
    | startDate | 2020-06-14-00.00.00  |
    | endDate   | 2020-12-31-23.59.59  |
    | price     | 35.50                |
    | currency  | EUR                  |

  Scenario: Request at 16:00 on the 14th for product 35455 and brand 1
    When a customer requests the product 35455 of the brand 1 at date "2020-06-14-16.00.00"
    Then the product is returned with the following data:
      | Field     | Value                |
      | id        | 35455                |
      | brandId   | 1                    |
      | rate      | 2                    |
      | startDate | 2020-06-14-15.00.00  |
      | endDate   | 2020-06-14-18.30.00  |
      | price     | 25.45                |
      | currency  | EUR                  |

  Scenario: Request at 21:00 on the 14th for product 35455 and brand 1
    When a customer requests the product 35455 of the brand 1 at date "2020-06-14-21.00.00"
    Then the product is returned with the following data:
      | Field     | Value                |
      | id        | 35455                |
      | brandId   | 1                    |
      | rate      | 1                    |
      | startDate | 2020-06-14-00.00.00  |
      | endDate   | 2020-12-31-23.59.59  |
      | price     | 35.50                |
      | currency  | EUR                  |

  Scenario: Request at 10:00 on the 15th for product 35455 and brand 1
    When a customer requests the product 35455 of the brand 1 at date "2020-06-15-10.00.00"
    Then the product is returned with the following data:
      | Field     | Value                |
      | id        | 35455                |
      | brandId   | 1                    |
      | rate      | 3                    |
      | startDate | 2020-06-15-00.00.00  |
      | endDate   | 2020-06-15-11.00.00  |
      | price     | 30.50                |
      | currency  | EUR                  |

  Scenario: Request at 21:00 on the 16th for product 35455 and brand 1
    When a customer requests the product 35455 of the brand 1 at date "2020-06-16-21.00.00"
    Then the product is returned with the following data:
      | Field     | Value                |
      | id        | 35455                |
      | brandId   | 1                    |
      | rate      | 4                    |
      | startDate | 2020-06-15-16.00.00  |
      | endDate   | 2020-12-31-23.59.59  |
      | price     | 38.95                |
      | currency  | EUR                  |
