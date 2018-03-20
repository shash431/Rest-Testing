@run
Feature: Answer Digital Who Are We Page

  Scenario: The Who Are We page contains the values of the company
    When I navigate to the Answer Digital Who Are We page
    Then the following company values are displayed:
      | A CATALYST FOR CHANGE               |
      | NURTURATION                         |
      | PUT LOVE IN THE ROOM                |
      | DO THE RIGHT THING IN THE RIGHT WAY |
      | NEVER STAND STILL                   |

  Scenario: Play the Answer Atmosphere video
    Given I navigate to the Answer Digital Who Are We page
    When I play the Answer Atmosphere video
    Then the Answer Atmosphere video plays