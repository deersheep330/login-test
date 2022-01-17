package automation.page;

import deersheep.automation.pageobject.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {

        super(driver);

        url = "https://www.instagram.com/";

        addElement("Username", "//input[@name='username']");
        addElement("Password", "//input[@name='password']");
        addElement("Login", "//button[@type='submit' and not(@disabled)]");
        addElement("LoginDisabled", "//button[@type='submit' and @disabled]");
        addElement("ErrorMessage", "//*[@data-testid='login-error-message']");

    }

    @Override
    public void navigate() {
        super.navigate();
        op.waitFor(getElement("Username"));
    }

    public void inputUsername(String username) {
        op.sendText(getElement("Username"), username);
    }

    public void inputPassword(String password) {
        op.sendText(getElement("Password"), password);
    }

    public void cannotClickLogin() {
        op.waitFor(getElement("LoginDisabled"));
    }

    public void clickLogin() {
        op.click(getElement("Login"));
    }

    public void showErrorMessage() {
        op.waitFor(getElement("ErrorMessage"));
    }

    public void loginSuccess() {
        if (op.isExist(getElement("ErrorMessage"))) {
            throw new RuntimeException("Expect Login Success but Show Error Message!");
        }
    }

}
