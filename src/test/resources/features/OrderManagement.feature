Feature: Order Management

  Background:
    Given a new order with ID "123e4567-e89b-12d3-a456-426614174001"
    And the order has payment received as "false"
    And the order has status "RECEIVED"
    And the order has product IDs "456e89b1-23d4-567a-890b-123e45678901, 78901234-abcd-5678-ef01-234567890abc"
    And the order has price "150.0"
    And the order has a client with ID "78901234-abcd-5678-ef01-234567890abc"

  Scenario: Create a new order
    When the order is created
    Then the order information should be valid

  Scenario: Update order status
    Given the order has status "PREPARING"
    When converting the order to a response DTO
    Then the order response should match the expected values

  Scenario: Mark payment as received
    Given the order has payment received as "true"
    When converting the order to a response DTO
    Then the order response should match the expected values

  Scenario: Update order products
    Given the order has products with IDs "78901234-abcd-5678-ef01-234567890abc, 123e4567-e89b-12d3-a456-426614174001"
    When converting the order to a response DTO
    Then the order response should match the expected values

  Scenario: Update order price
    Given the order has price "200.0"
    When converting the order to a response DTO
    Then the order response should match the expected values
