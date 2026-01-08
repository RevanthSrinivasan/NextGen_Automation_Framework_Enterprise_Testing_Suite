
Feature: Framework sanity check
  @api
  Scenario: Validate home page heading
    Given I open the home page
    When I get the heading text
    Then I validate the heading text