package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String id, username, about, birthday, sex, email, lastname, firstname, fullname, accountType, legalInfo;
    private Object avatar, appSettings, stats;

    private UserSettings settings;

    @JsonProperty("isEmailConfirmed")
    private boolean isEmailConfirmed;

    @JsonProperty("isPro")
    private boolean isPro;

}
