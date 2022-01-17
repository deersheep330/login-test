package automation.preprocess;

import deersheep.automation.webdriver.WebDriverWrapper;
import org.openqa.selenium.WebDriver;

public class Preprocess {

    public static WebDriver setupWebDriverWrapper() {
        WebDriverWrapper wrapper = WebDriverWrapper.getInstance();
        wrapper.setPageLoadTimeoutInSec(30);
        wrapper.addRemoteNode("remote", "http://localhost:4444");
        return wrapper.getInstance().getWebDriver("Chrome", "remote");
    }

}
