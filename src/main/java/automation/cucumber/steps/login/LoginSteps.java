package automation.cucumber.steps.login;

import automation.cucumber.steps.BaseSteps;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseSteps {

    @When("navigate to ig")
    public void navigateToIg() {
        loginPage.navigate();
    }

    @Then("input username {string}")
    public void inputUsername(String username) {
        loginPage.inputUsername(username);
    }

    @Then("input password {string}")
    public void inputPassword(String password) {
        loginPage.inputPassword(password);
    }

    @Then("cannot click login")
    public void cannotClickLogin() {
        loginPage.cannotClickLogin();
    }

    @Then("show error message")
    public void showErrorMessage() {
        loginPage.showErrorMessage();
    }

    @Then("login success")
    public void loginSuccess() {
        loginPage.loginSuccess();
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            loginPage.getOperation().screenshotAndEmbedInCucumberReport(scenario);
        }
    }

}
