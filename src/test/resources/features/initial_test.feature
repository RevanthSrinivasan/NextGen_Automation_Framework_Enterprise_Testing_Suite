
Feature: Framework sanity check
  @smoke
#  Scenario: Verify cucumber setup
#    Given framework is initialized
#    Then test should pass

  Scenario: Validate home page heading
    Given I open the home page
    When I get the heading text
    Then I validate the heading text