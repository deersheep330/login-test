package automation.preprocess;

import deersheep.automation.webdriver.WebDriverWrapper;
import org.openqa.selenium.WebDriver;

public class Preprocess {

    public static WebDriver setupWebDriverWrapper() {
        WebDriverWrapper wrapper = WebDriverWrapper.getInstance();
        wrapper.setPageLoadTimeoutInSec(30);
        wrapper.addRemoteNode("remote", "http://10.60.92.158:4444");
        return wrapper.getInstance().getWebDriver("Chrome", "remote");
    }

}
