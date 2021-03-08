# Author - Ekin
Feature: Favorite Cocktail

  As a user of Mixer Cocktail Recommender with an account
  I want to favorite a cocktail
  So I can reach my favoirte cocktails easier

  Background: The user is in the database and logged in
  
  Given I am signed up for Mixer
  And I am logged into my account
  
  Scenario: User favorites a cocktail not in the list (Success Flow)

  When I search a coctail
  And I favorite the cocktail
  Then the coctail is added to my favorites list

  Scenario: User favorites a cocktail in the list (Alternate Flow)

  When I search a coctail
  And I favorite the cocktail
  And the cocktail is already in my favorites list
  Then the coctail is kept in my favorites list
  
  Scenario: User unsuccessfully favorites a cocktail (Error Flow)

  When I search a coctail
  And I favorite the cocktail
  Then the coctail is not added to my favorites list