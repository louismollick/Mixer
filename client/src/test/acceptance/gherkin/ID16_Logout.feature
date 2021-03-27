# Author - Ekin
Feature: Logout

  As a user of Mixer Cocktail Recommender with an account
  I want to logout
  So that my account secure

  Scenario: User successfully logged out of account (Success Flow)

  Given I am signed up for Mixer
  And I am logged into my account
  And I am on the Profile page
  When I press logout button
  Then I am no longer authenticated
  And I am redirected to the home page

  Scenario: User was not logged in (Alternate Flow)

  Given I am not logged in into my account
  When I navigate to the Profile page
  Then I will not see a logout button
  And I will see the message 'Please login to see your profile.'