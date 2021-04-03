#Author - Misha
Feature: Remove Modifier From Inventory

   As a user of Mixer
   I would like to remove a modifier from my inventory
   So that I can generate cocktail recipe recommendations based only on ingredients I possess

   Background: User is logged in and has modifier item(s) in inventory

      Given I am signed up for Mixer
      And I am logged in to Mixer

   Scenario: Remove a valid modifier from inventory (Success Flow)

      When I select a valid modifier
      And the modifier exists in my inventory
      And I confirm removing the modifier from inventory
      Then the system will remove the modifier from my inventory

   Scenario: Modifier does not exist (Error Case)

      When I select an invalid modifier
      And I confirm removing the modifier from inventory
      Then the system will display an error about the modifier
      And no modifier will be removed from my inventory
