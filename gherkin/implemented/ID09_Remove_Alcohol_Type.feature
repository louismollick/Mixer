#Author - Ekin
Feature: Remove Alcohol Type From Inventory

    As a user of Mixer
    I would like to remove an alcohol type from my inventory
    So that I can generate accurate cocktail recommendations

    Background: User is logged in and has items in inventory

        Given I am signed up for Mixer
        And I am logged into my account

    Scenario: Remove a valid alcohol type from inventory (Success Flow)

        When I select a valid alcohol type
        And the alcohol type exists in inventory
        And I confirm removing the alcohol from inventory
        Then the system will remove the alcohol type from my inventory

    Scenario: Alcohol type does not exist in inventory (Error Case)

        When I select an invalid alcohol type
        And I confirm removing the alcohol from inventory
        Then the system will display an error about the alcohol
        And no alcohol will be removed from my inventory
