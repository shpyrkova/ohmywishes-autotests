package testdata;

import models.lombok.User;
import models.lombok.UserSettings;

import java.util.Collections;

public class ExpectedUserData {

    public static User getExpectedUser() {
        User user = new User();
        user.setId("bd669f93e2c888b951423109");
        user.setUsername("surkova");
        user.setAbout("something about me");
        user.setBirthday("2000-07-01");
        user.setSex("f");
        user.setEmail("shpyrkova@gmail.com");
        user.setLastname("Monster");
        user.setFirstname("Gentle");
        user.setFullname("Gentle Monster");
        user.setAccountType("user");
        user.setLegalInfo(null);
        user.setAvatar(null);
        user.setSettings(getExpectedSettings());
        user.setAppSettings(null);
        user.setStats(null);
        user.setEmailConfirmed(true);
        user.setPro(false);

        return user;
    }

    private static UserSettings getExpectedSettings() {
        UserSettings settings = new UserSettings();
        settings.setSocialProfiles(Collections.emptyList());
        settings.setSystemNewsReceiveAllowed(false);
        settings.setSecretSantaAllYearAvailable(true);
        settings.setPrivateWishesBlurred(true);
        settings.setReservedWishesCounterEnabled(true);
        settings.setDefaultCurrency(null);
        settings.setDefaultLocale(null);
        return settings;
    }

}
