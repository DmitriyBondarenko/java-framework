Feature: Verify Google search

  Background:
    #Given User is logged in via API
    Given Google page is opened

  Scenario: CheckSimpleSearch
    When User enters search request 'Automation'
    Then Results page with 'Automation' is displayed

  Scenario Outline: CheckDifferentSearchResults
    When User enters search request '<request>'
    Then Results page with '<request>' is displayed

    Examples:
      | request    |
      | Combiner   |
      | Automation |
      | Selenium   |
