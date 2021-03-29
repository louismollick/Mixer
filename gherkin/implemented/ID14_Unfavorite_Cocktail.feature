# Author - Ekin
Feature: Favorite Cocktail

  As a user of Mixer Cocktail Recommender with an account
  I want to favorite a cocktail
  So I can reach my favoirte cocktails easier

  Background: The user is in the database and logged in
  
  Given I am signed up for Mixer
  And I am logged into my account
  
  Scenario: User unfavorites a cocktail in the list (Success Flow)

  When I search a coctail
  And I unfavorite the cocktail
  Then the coctail is removed from my favorites list

  Scenario: User unfavorites a cocktail not in the list (Alternate Flow)

  When I search a coctail
  And I unfavorite the cocktail
  And the cocktail is already not in my favorites list
  Then the coctail is still not in my favorites list
  
  Scenario: User unsuccessfully unfavorites a cocktail from the list (Error Flow)

  When I search a coctail
  And I unfavorite the cocktail
  Then the coctail is not removed from my favorites list