# Author - Ekin
Feature: Delete Account

  As a user of Mixer Cocktail Recommender with an account
  I want to delete my account
  So my information is deleted

  Background: The user is in the database and logged in
  
  Given I am signed up for Mixer
  And I am logged into my account
  
  Scenario: User successfully deletes their account (Success Flow)

  When I press the delete button
  Then my information is deleted
  And I am no longer a user
  And I am redirected to the login page

  Scenario: Delete account unsuccessfull (Error Flow)

  When I press the delete button
  Then my information is not deleted
  And I am still a user 
  And I am not redirected to the login page