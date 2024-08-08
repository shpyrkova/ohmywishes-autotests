package tests.api;

import io.restassured.response.Response;
import models.lombok.User;
import models.lombok.UserSettings;
import models.lombok.api.GetOwnUserInfoResponseBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testdata.ExpectedUserData;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("api")
@DisplayName("API. Профиль пользователя")
public class UserProfileTests extends TestBase {

    @Test
    @DisplayName("Получение данных о пользователе (о себе)")
    void successfulDeleteWishItemTest() {

        Response response = step("Запросить данные о пользователе", () ->
                apiClient.getOwnUserInfo());
        User userResponseData = response.as(GetOwnUserInfoResponseBody.class).getItem();
        UserSettings userResponseDataSettings = userResponseData.getSettings();

        User expectedUserData = ExpectedUserData.getExpectedUser();
        UserSettings expectedUserSettings = expectedUserData.getSettings();

        step("Проверить, что основные поля в ответе корректны", ()-> {
            assertThat(userResponseData.getId()).isEqualTo(expectedUserData.getId());
            assertThat(userResponseData.getBirthday()).isEqualTo(expectedUserData.getBirthday());
            assertThat(userResponseData.getFirstname()).isEqualTo(expectedUserData.getFirstname());
            assertThat(userResponseData.getLastname()).isEqualTo(expectedUserData.getLastname());
            assertThat(userResponseData.getFullname()).isEqualTo(expectedUserData.getFullname());
            assertThat(userResponseData.getAbout()).isEqualTo(expectedUserData.getAbout());
            assertThat(userResponseData.getEmail()).isEqualTo(expectedUserData.getEmail());
            assertThat(userResponseData.getSex()).isEqualTo(expectedUserData.getSex());
            assertThat(userResponseData.getUsername()).isEqualTo(expectedUserData.getUsername());
            assertThat(userResponseData.getAccountType()).isEqualTo(expectedUserData.getAccountType());
            assertThat(userResponseData.getLegalInfo()).isEqualTo(expectedUserData.getLegalInfo());
            assertThat(userResponseData.isEmailConfirmed()).isEqualTo(expectedUserData.isEmailConfirmed());
            assertThat(userResponseData.isPro()).isEqualTo(expectedUserData.isPro());
            assertThat(userResponseData.getAvatar()).isNotNull();
        });

        step("Проверить, что настройки пользователя в ответе корректны", ()-> {
            assertThat(userResponseDataSettings.isPrivateWishesBlurred()).isEqualTo(expectedUserSettings.isPrivateWishesBlurred());
            assertThat(userResponseDataSettings.isReservedWishesCounterEnabled()).isEqualTo(expectedUserSettings.isReservedWishesCounterEnabled());
            assertThat(userResponseDataSettings.isSecretSantaAllYearAvailable()).isEqualTo(expectedUserSettings.isSecretSantaAllYearAvailable());
            assertThat(userResponseDataSettings.getDefaultCurrency()).isEqualTo(expectedUserSettings.getDefaultCurrency());
            assertThat(userResponseDataSettings.getDefaultLocale()).isEqualTo(expectedUserSettings.getDefaultLocale());
            assertThat(userResponseDataSettings.getSocialProfiles()).isEqualTo(expectedUserSettings.getSocialProfiles());
        });
    }

}
