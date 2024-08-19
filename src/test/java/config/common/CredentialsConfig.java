package config.common;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:credentials.properties"
})

public interface CredentialsConfig extends Config {
    @Key("email")
    String getEmail();

    @Key("password")
    String getPassword();
}