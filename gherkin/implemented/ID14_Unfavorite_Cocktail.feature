# Author - Ekin
Feature: Unfavorite Cocktail

  As a user of Mixer Cocktail Recommender with an account
  I want to unfavorite a cocktail
  So this cocktail isn't marked in priority in my profile

  Background: The user is in the database and logged in

    Given I am signed up for Mixer
    And I am logged into my account

  Scenario: User unfavorites a cocktail in their favorite list (Success Flow)

    Given the cocktail 'Mimosa' is already in my favorites list
    When I select the cocktail 'Mimosa'
    And I request to unfavorite the cocktail
    Then the cocktail is removed from my favorites list
    And the system will return the message 'Successfully removed Mimosa.' with code '200'

  Scenario: User tries to unfavorite a cocktail not in their favorite list (Alternate Flow)

    When I select the cocktail 'Gin and Tonic'
    And I request to unfavorite the cocktail
    Then my cocktail favorites list has not changed
    And the system will return the message 'Cocktail is not in favourites.' with code '200'

  Scenario: Cocktail does not exist (Error Flow)

    When I select the cocktail 'Fish & Chips'
    And I request to unfavorite the cocktail
    Then my cocktail favorites list has not changed
    And the system will return the message 'Cocktail not found with name Fish & Chips.' with code '404'