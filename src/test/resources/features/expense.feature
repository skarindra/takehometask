Feature: Expense

  Background:
    Given user click income menu
    When user input new income
    Then user verify new income

  Scenario: Input expense
    Given user click expense menu
    When user input new expense
    Then user verify new expense
    And user verify updated balance
