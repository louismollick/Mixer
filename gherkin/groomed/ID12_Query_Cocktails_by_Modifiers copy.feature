# Author - Louis
Feature: Query Cocktail by Modifier(s)

  As a user of Mixer
  I would like to query cocktails by the modifier(s) they contain
  So that I can know which cocktails I can make with my ingredients at home

  Background: User exists

    Given I am a signed up for Mixer
    And I am logged in to Mixer

  Scenario: Cocktails containing multiple modifiers successfully queried (Success Flow)

    Given there exist cocktails containing <modifier1> and <modifier2>
    When I try to query the cocktails that contain <modifier1> and <modifier2>
    Then the system will not show an error
    And the system will successfully return a list of cocktails that all contain <modifier1> and <modifier2>

    Examples:
      | modifier1   | modifier2    |
      | Sprite      | Orange Juice |
      | Lemon Juice | Tomato Juice |
      | Lime Juice  | Sugar Syrup  |

  Scenario: No cocktails exist with the queried modifier(s) (Alternate Flow)

    Given there exist no cocktails containing both <modifier1> and <modifier2>
    When I try to query the cocktails that contain <modifier1> and <modifier2>
    Then the system will not show an error
    And will show an empty list of cocktails

    Examples:
      | modifier1   | modifier2    |
      | Sprite      | Tomato Juice |
      | Lemon Juice | Sugar Syrup  |
      | Lime Juice  | Lemon Soda   |

  Scenario: One of the modifiers does not exist (Error Flow)

    Given there exists a modifier <modifier1>
    And there does not exist a modifier <modifier2>
    When I try to query the cocktails that contain <modifier1> and <modifier2>
    Then the system will return an error
    And no Cocktails will be returned

    Examples:
      | modifier1   | modifier2 |
      | Sprite      | Toe nails |
      | Lemon Juice | Gasoline  |
      | Lime Juice  | Ajax      |