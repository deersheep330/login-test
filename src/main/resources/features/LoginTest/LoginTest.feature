Feature: Login Test

  Scenario: Empty Username And Password
    When navigate to ig
    Then input username ""
    Then input password ""
    Then cannot click login

  Scenario: Empty Username And Valid Password
    When navigate to ig
    Then input username ""
    Then input password "valid_password"
    Then cannot click login

  Scenario: Existing Username And Empty Password
    When navigate to ig
    Then input username "existing_username"
    Then input password ""
    Then cannot click login

  # length of password cannot < 6 words
  Scenario: Existing Username And Too Short Password
    When navigate to ig
    Then input username "existing_username"
    Then input password "sssss"
    Then cannot click login

  Scenario: Nonexistent Username And Valid Password
    When navigate to ig
    Then input username "nonexistent_username9c98c0b6-b340-46e7-9bac-458513e003b1"
    Then input password "valid_password"
    # it should show incorrect username message
    # but sometimes it just show a general "couldn't connect to Instagram" message
    Then show error message

  Scenario: Correct Username And Valid Password
    When navigate to ig
    Then input username "existing_username"
    Then input password "valid_password"
    # it should show incorrect username message
    # but sometimes it just show a general "couldn't connect to Instagram" message
    Then show error message

  # the only positive case
  Scenario: Correct Username And Password Pair
    When navigate to ig
    Then input username "correct_username"
    Then input password "correct_password"
    Then login success
