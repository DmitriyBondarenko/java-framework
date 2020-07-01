Feature: TestFeature

  Scenario: Open google page
    Given Google page is opened
    When User enters search request 'automation'
    Then Results page is opened