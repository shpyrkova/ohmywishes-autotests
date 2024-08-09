package config.web;

import org.aeonbits.owner.Config;
import java.net.URL;

@Config.Sources({
        "classpath:web/${env}.properties"
})

public interface WebDriverConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://ohmywishes.ru")
    URL getBaseUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browserVersion")
    @DefaultValue("122.0")
    String getBrowserVersion();

    @DefaultValue("true")
    boolean isRemote();

    @Key("remoteUrl")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    URL getRemoteUrl();

    @Key("remoteHost")
    @DefaultValue("selenoid.autotests.cloud")
    String getRemoteHost();
}