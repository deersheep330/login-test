Feature: Login Test

#  Scenario: Normal Username And Password
#    Given on login page
#    Then input username and password

  Scenario: Parameterized Username And Password
    Given on login page
    Then input username "test@gmail.com" and password "p@ssword"

  Scenario Outline: Parameterized Username And Password With Example Keyword
    Given on login page
    Then input username "<username>" and password "<password>"

    Examples:
    | username       | password |
    | test@gmail.com | p@ssword |
    | 0912345678     | 9b5261a  |

  Scenario: Parameterized Username And Password With Data Table
    Given on login page
    Then input username and password
      | username       | password |
      | test@gmail.com | p@ssword |
      | 0912345678     | 9b5261a  |


