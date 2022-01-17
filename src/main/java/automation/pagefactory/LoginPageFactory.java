package automation.pagefactory;

import automation.page.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginPageFactory {

    public static LoginPage getLoginPage(WebDriver driver) {
        return new LoginPage(driver);

        // different kinds of LoginPage can be generated here
        // e.g. MobileLoginPage
    }

}
