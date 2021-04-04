# Author - Ekin
Feature: Favorite Cocktail

  As a user of Mixer Cocktail Recommender with an account
  I want to favorite a cocktail
  So I can reach my favoirte cocktails easier

  Background: The user is in the database and logged in

    Given I am signed up for Mixer
    And I am logged into my account

  Scenario: User favorites a cocktail not in the list (Success Flow)

    When I select the cocktail 'Mimosa'
    And I request to favorite the cocktail
    Then the cocktail is added to my favorites list
    And the system will return the message 'Successfully added Mimosa.' with code '200'

  Scenario: User favorites a cocktail in the list (Alternate Flow)

    Given the cocktail 'Gin and Tonic' is already in my favorites list
    When I select the cocktail 'Gin and Tonic'
    And I request to favorite the cocktail
    Then the cocktail is kept in my favorites list
    And the system will return the message 'Cocktail already in favourites.' with code '200'

  Scenario: Cocktail does not exist (Error Flow)

    When I select the cocktail 'Fish & Chips'
    And I request to favorite the cocktail
    Then the cocktail is not added to my favorites list
    And the system will return the message 'Cocktail not found with name Fish & Chips.' with code '404'