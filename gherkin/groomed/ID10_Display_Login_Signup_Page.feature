# Author - Louis
Feature: Display Signup/Login page

  As a user of Mixer Cocktail Recommender
  I want to see the signup or login page
  So I can login or signup to Mixer

  Scenario: User not logged in (Success Flow)

    Given I am not yet logged in (my browser does not hold a token)
    When I navigate to the login/signup page of Mixer
    Then I should see the following text fields under the 'Login' section
      | Email: | Password: |
    And I should see the following text fields under the 'Signup' section
      | Enter your email: | Enter a password: | Confirm Password: |
    And I should see the following buttons:
      | Login | Signup |

  Scenario: User logged in and redirected (Alternate Flow)

    Given I am logged in (browser holds a valid token)
    When I navigate to the login/signup page of Mixer
    Then I should be redirected to the main page of Mixer
    And I should still be logged into my account