Feature: Order Management

  Background:
    Given the system has orders

  Scenario: Retrieve all orders
    When the client requests all orders

  Scenario: Retrieve order by ID
    When the client requests an order by ID
    Then the system should respond with the order details

  Scenario: Create a new order
    Given the client provides order details
    When the client submits a new order
    Then the system should respond with the created order details

  Scenario: Update order status
    Given there is an existing order
    When the client updates the order status
    Then the system should respond with a success message

  Scenario: Delete an order
    Given there is an existing order
    When the client deletes the order
    Then the system should respond with a success message

  Scenario: Perform order checkout
    Given there are orders for a specific CPF
    When the client requests order checkout for that CPF
    Then the system should respond with a list of order checkout details
