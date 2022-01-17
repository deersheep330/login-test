# Login Test

Simple Instagram login test using Selenium + Cucumber.

## Getting Started

#### Step 1: Make sure you have Java installed on your machine.

#### Step 2: Modify the Selenium server address.

In src/main/java/preprocess/Preprocess.java, modify the remote address to the address of your selenium server. e.g.:

```
wrapper.addRemoteNode("remote", "http://localhost:4444");
```

#### Step 3: Provide a correct username and password pair for the positive testcase.

In src/main/resources/features/LoginTest/LoginTest.feature, modify the "correct_username" and "correct_password" of Scenario: Correct Username And Password Pair to a username/password pair that can really login Instagram. e.g.: 

```
  Scenario: Correct Username And Password Pair
    When navigate to ig
    Then input username "realuser@gmail.com"
    Then input password "re@lp@ssw0rd"
    Then click login
    Then login success
```

#### Step 4: Execute the test with Gradle.

```
gradlew clean build testLogin
```

#### Step 5: View the test report.

After the test executed, a Cucumber test report would be generated in build/test-results/cucumber/LoginTest/index.html.

## TODO

1. The same variable in Cucumber feature files would be needed by different scenarios. For example, "valid_password", "existing_username", e.t.c, but currently there's no way to share variables between scenarios or feature files in Cucumber framework.

2. When a nonexistent username provided, The wrong username error message should be shown. When the username is existing, but the password is wrong, the wrong password error message should be shown. But sometimes no matter the username or the password is wrong, it just shows a "couldn't connect to Instagram" error message. The behavior hasn't been clarified yet.