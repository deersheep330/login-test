package automation.cucumber.runner;

import automation.cucumber.context.TestContext;
import automation.page.LoginPage;
import automation.pagefactory.LoginPageFactory;
import automation.preprocess.Preprocess;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/resources/features/LoginTest",
        plugin={"pretty",
                "html:build/test-results/cucumber/LoginTest",
                "junit:build/test-results/junit/regression.LoginTest.xml"},
        glue={"automation.cucumber.steps.login"},
        monochrome=true,
        strict=true)
public class LoginTestRunner {

    @BeforeClass
    public static void setUp() {

        System.out.println("===> BeforeClass setUp");

        WebDriver driver = Preprocess.setupWebDriverWrapper();
        LoginPage loginPage = LoginPageFactory.getLoginPage(driver);

        TestContext context = TestContext.getInstance();

        context.setDriver(driver);
        context.setLoginPage(loginPage);
    }

    @AfterClass
    public static void tearDown() {
        if (TestContext.getInstance().getLoginPage() != null) {
            TestContext.getInstance().getDriver().quit();
        }
        System.out.println("===> AfterClass tearDown");
    }

}
