package config.web;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;

public class WebDriverProvider {
    private final WebDriverConfig config;

    public WebDriverProvider() {
        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    }

    public void setWebConfig() {
        Configuration.baseUrl = config.getBaseUrl().toString();
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 20000;
        if (config.isRemote()) {
            Configuration.remote = config.getRemoteUrl().toString();
        }
    }

}