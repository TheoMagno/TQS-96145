Feature: Check a country's COVID cases

  Scenario: Brazil
    Given go to the WebApp home page
    When I search for "Bra"
    When click on "Brazil"
    Then country's name is "Brazil" and continent is "South-America"