#Author - Ekin
Feature: Remove Alcohol Type From Inventory

As a user of Mixer
I would like to remove an alcohol type from my inventory
So that I can generate accurate cocktail recommendations

Background: User is logged in and has items in inventory

    Given I am a user 
    And I am logged in to Mixer
    And I have items in my inventory

Scenario: Remove a valid alcohol type from inventory (Success Flow)

    When I select an alcohol type
    And the alcohol type is in my inventory
    And I confirm removing it from my inventory
    Then the system will remove the alcohol type from my inventory

Scenario: Alcohol type does not exist in inventory (Error Case)

    When I select an alcohol type
    And the alcohol type is not in my inventory
    Then the system will display an error
    And no item will be removed
