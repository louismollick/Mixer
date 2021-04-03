# Author - Louis
Feature: Login with credentials

  As a user of Mixer Cocktail Recommender with an account
  I want to login using my credentials
  So that I can use the Mixer webapp until I logout.

  Background: Given I am signed up for Mixer

  Given I am signed up for Mixer

  Scenario: User logged into account (Success Flow)

    When I submit a valid email and password pair
    And I submit a login request
    Then the system will confirm the validity of the credentials
    And will return a valid login token

  Scenario: Invalid credentials (Error Flow)

    When I submit an incorrect email and password pair
    And I submit a login request
    Then the system will find that the password is incorrect
    And a new login token will not be created
    And the system will return the message 'Invalid credentials. Please try again.' with code '400'

  Scenario: Account does not exist (Error Flow)

    When I submit a non-existant email and password pair
    And I submit a login request
    Then the system will find that there is no account linked to the specified email
    And a new login token will not be created
    And the system will return the message 'Invalid credentials. Please try again.' with code '400'

  Scenario: Empty email (Error Flow)

    When I enter an empty email
    And I submit a login request
    Then the system will return the message 'Email cannot be empty.' with code '400'

  Scenario: Empty password (Error Flow)

    When I enter an empty password
    And I submit a login request
    Then the system will return the message 'Password cannot be empty.' with code '400'