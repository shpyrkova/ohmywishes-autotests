package config.common;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:api.properties",
        "system:properties"
})

public interface ApiConfig extends Config {
    @Key("baseURI")
    String getBaseUri();
}