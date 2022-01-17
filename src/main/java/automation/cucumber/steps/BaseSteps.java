package automation.cucumber.steps;

import automation.cucumber.context.TestContext;
import automation.page.LoginPage;
import org.openqa.selenium.WebDriver;

public abstract class BaseSteps {

    protected TestContext context;

    protected WebDriver driver;
    protected LoginPage loginPage;

    public BaseSteps() {
        context = TestContext.getInstance();
        driver = context.getDriver();
        loginPage = context.getLoginPage();
    }

}
