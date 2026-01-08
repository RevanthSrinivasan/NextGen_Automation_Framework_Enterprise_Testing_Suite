Feature: User API
  @api
  Scenario: Create a new user via API
    Given I create a user with name "John" and job "QA"
    When I retrieve the created user ID
    Then The user ID should not be null