Feature: TestFeature

  Background:
    Given Google page is opened

  Scenario: Open google page
    When User enters search request 'automation'
    Then Results page is opened

  Scenario Outline: Different search requests
    When User enters search request '<request>'
    Then Results page is opened

    Examples:
      | request    |
      | combiner   |
      | automation |
