# Author - Louis
Feature: Query Cocktail by Alcohol(s)

  As a user of Mixer
  I would like to query cocktails by the alcohol(s) they contain
  So that I can know which cocktails I can make with my ingredients at home

  Background: User exists

    Given I am a signed up for Mixer
    And I am logged in to Mixer

  Scenario: Cocktails containing multiple alcohols successfully queried (Success Flow)

    Given there exist cocktails containing <alcohol1> and <alcohol2>
    When I try to query the cocktails that contain <alcohol1> and <alcohol2>
    Then the system will not show an error
    And the system will successfully return a list of cocktails that all contain <alcohol1> and <alcohol2>

    Examples:
      | alcohol1       | alcohol2       |
      | Triple Sec     | Tequila        |
      | Sparkling Wine | Peach Schnapps |
      | Tequila        | Cointreau      |

  Scenario: No cocktails exist with the queried alcohol(s) (Alternate Flow)

    Given there exist no cocktails containing both <alcohol1> and <alcohol2>
    When I try to query the cocktails that contain <alcohol1> and <alcohol2>
    Then the system will not show an error
    And will show an empty list of cocktails

    Examples:
      | alcohol1       | alcohol2       |
      | Triple Sec     | Peach Schnapps |
      | Tequila        | Sparkling Wine |
      | Peach Schnapps | Cointreau      |

  Scenario: One of the alcohols does not exist (Error Flow)

    Given there exists a alcohol <alcohol1>
    And there does not exist a alcohol <alcohol2>
    When I try to query the cocktails that contain <alcohol1> and <alcohol2>
    Then the system will return an error
    And no Cocktails will be returned

    Examples:
      | alcohol1       | alcohol2  |
      | Triple Sec     | Toe nails |
      | Tequila        | Gasoline  |
      | Peach Schnapps | Ajax      |