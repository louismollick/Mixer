# Author - Sami
Feature: Create Account

    As somebody interested in Mixer,
    I want to create account with Mixer
    so that I can use the Mixer webapp.

    Background: Somebody has an internet connection and is on the Create Account page

        Given I am somebody with a stable internet connection
        And I am on the Create Account page of the Mixer webaapp

    Scenario: User account generated (Success Flow)

        When I enter an email and password
        And the system finds that it is a valid email
        Then the system will create my account with email and password
        And I will be redirected to the main page of Mixer

    Scenario: Email already in the database (Error Flow)

        When I enter an email and password
        And the system finds that the email is already in the database
        Then the system will not create a new account with email and password
        And the system will display to the user an error

    Scenario: Weak password (Error Flow)

        When I enter an email and password
        And the system finds that it is a valid email
        But the system finds that the password is too weak
        Then the system will not create a new account with email and password
        And the system will display to the user an error