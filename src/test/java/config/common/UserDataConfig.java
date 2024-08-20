package config.common;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:userdata.properties"
})

public interface UserDataConfig extends Config {
    @Key("email")
    String getEmail();

    @Key("password")
    String getPassword();

    @Key("username")
    String getUsername();
}