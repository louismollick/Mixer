# Author - Ekin
Feature: Logout

  As a user of Mixer Cocktail Recommender with an account
  I want to logout
  So that my account secure

  Background: The user is in the database and logged in
  
  Given I am signed up for Mixer
  And I am logged into my account
  
  Scenario: User successfully logged out of account (Success Flow)

  When I press logout button
  Then I am no longer authenticated
  And I am redirected to the login page

  Scenario: Logout unsuccessful (Error Flow)

  When I press logout button
  Then I am still authenticated
  And I am still on the logout page