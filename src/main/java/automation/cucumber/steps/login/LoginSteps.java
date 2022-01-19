package automation.cucumber.steps.login;

import automation.cucumber.steps.BaseSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class LoginSteps extends BaseSteps {

    @Given("on login page")
    public void onLoginPage() {
        System.out.println("==> on login page");
    }

    

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            loginPage.getOperation().screenshotAndEmbedInCucumberReport(scenario);
        }
    }


    @Then("input username {string} and password {string}")
    public void inputUsernameAndPassword(String arg0, String arg1) {
        System.out.println("login with username: " + arg0 + ", password: " + arg1);
    }

    @Then("input username and password")
    public void inputUsernameAndPassword(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> data : list) {
            System.out.println("login with username: " + data.get("username") + ", password: " + data.get("password"));
        }

    }
}
