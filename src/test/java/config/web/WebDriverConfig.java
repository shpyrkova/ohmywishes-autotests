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

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("browserVersion")
    @DefaultValue("latest")
    String getBrowserVersion();

    @DefaultValue("false")
    boolean isRemote();

    @Key("remoteUrl")
    URL getRemoteUrl();

    @Key("remoteHost")
    String getRemoteHost();
}