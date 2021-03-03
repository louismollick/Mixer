# Author - Hong
Feature: Generate cocktail choice based on inventory and settings

    As a user of Mixer
    I would like to get cocktail recommendation from given inventory and settings
    So that I can start making a cocktail

    Background: User is logged in with defined inventory and setting

        Given I am a signed up for Mixer
        And I am logged in to Mixer
        And I have items in my inventory
        And I have items in setting list

    Scenario: Valid cocktail type generated (Success Flow)

        When I request the cocktai recommendation
        And the system find the appopriate cocktail choice based on inventory and setting
        Then the system will output cocktail type

    Scenario: Fail to generate cocktail reccommendation (Error Flow)

        When I request the cocktai recommendation
        And the system fail to find appopriate cocktail choice based on inventory and setting
        Then the system will output recommendation failure