package automation.cucumber.context;

import automation.page.LoginPage;
import org.openqa.selenium.WebDriver;

public class TestContext {

    private static TestContext instance = null;

    private WebDriver driver;
    private LoginPage loginPage;

    private TestContext() {

    }

    public static TestContext getInstance() {
        if (instance == null) instance = new TestContext();
        return instance;
    }

    public WebDriver getDriver() { return driver; }
    public LoginPage getLoginPage() { return loginPage; }

    public void setDriver(WebDriver driver) { this.driver = driver; }
    public void setLoginPage(LoginPage loginPage) { this.loginPage = loginPage; }

}
