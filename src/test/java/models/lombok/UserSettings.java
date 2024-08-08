package models.lombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserSettings {

    List<String> socialProfiles;
    private String defaultCurrency, defaultLocale;

    @JsonProperty("isSystemNewsReceiveAllowed")
    private boolean isSystemNewsReceiveAllowed;

    @JsonProperty("isNewsReceiveAllowed")
    private boolean isNewsReceiveAllowed;

    @JsonProperty("isSecretSantaAllYearAvailable")
    private boolean isSecretSantaAllYearAvailable;


    @JsonProperty("isPrivateWishesBlurred")
    private boolean isPrivateWishesBlurred;

    @JsonProperty("isReservedWishesCounterEnabled")
    private boolean isReservedWishesCounterEnabled;

}
