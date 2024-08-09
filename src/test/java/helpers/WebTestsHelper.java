package helpers;

import com.codeborne.selenide.WebDriverRunner;

public class WebTestsHelper {

    public String getCurrentUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

}
