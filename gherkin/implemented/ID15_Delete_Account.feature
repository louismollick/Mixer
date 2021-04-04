# Author - Ekin
Feature: Delete Account

  As a user of Mixer Cocktail Recommender with an account
  I want to delete my account
  So my information is deleted

  Background: The user is in the database and logged in

    Given I am signed up for Mixer
    And I am logged into my account

  Scenario: User successfully deletes their account (Success Flow)

    When I request to delete my account
    Then I will no longer be a user
    And the system will return the message 'Succesfully deleted.' with code '200'

  Scenario: Delete account unsuccessful (Error Flow)

    When I request to delete somebody else's account
    Then that account will not be deleted
    And the system will return the message 'You cannot delete someone else\'s account.' with code '403'