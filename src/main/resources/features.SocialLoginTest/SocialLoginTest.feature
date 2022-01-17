Feature: Social Login Test

  Scenario: Already Login Facebook
    When navigate to fb
    Then login fb
    Then navigate to ig
    Then login with fb
    Then login success
    Then logout

  Scenario: Not Login Facebook Yet
    Then navigate to ig
    Then login with fb
    Then redirect to fb
    Then login fb
    Then redirect to ig
    Then login success
    Then logout
