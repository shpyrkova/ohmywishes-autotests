package tests.api;

import io.qameta.allure.Epic;
import io.restassured.response.Response;
import models.lombok.*;
import models.lombok.api.GetWishItemResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.*;

@Epic("API тесты")
@Tag("api")
@DisplayName("API. Желания пользователя")
public class UserWishesTests extends TestBaseApi {

    String token;

    @BeforeEach
    public void authorizeAndOpenMyWishesPage() {
        token = apiClient.requestToken(userDataConfig.getEmail(), userDataConfig.getPassword());
    }

    @Test
    @DisplayName("Получение данных о желании неавторизованным пользователем")
    void getWishItemTest() {
        String wishItemTitle = dataGenerator.generateWishItemTitle();
        String wishItemDescription = dataGenerator.generateWishItemDescription();
        WishItem wishItem = WishItem.builder().title(wishItemTitle).description(wishItemDescription).build();
        String wishItemId = steps.createWishItemWithApi(token, wishItem);

        Response response = step("Отправить запрос на получение данных о желании без авторизации", () ->
                apiClient.getWishItemNonAuthorized(wishItemId));
        GetWishItemResponseBody responseBody = response.as(GetWishItemResponseBody.class);
        WishItem responseWishItem = responseBody.getItem();

        step("Проверить, что название и описание созданного желания корректны", () -> {
            assertThat(response.statusCode()).isEqualTo(200);
            assertThat(responseWishItem.getTitle()).isEqualTo(wishItemTitle);
            assertThat(responseWishItem.getDescription()).isEqualTo(wishItemDescription);
        });
    }

    @Test
    @DisplayName("Удаление желания")
    void deleteWishItemTest() {
        String wishItemTitle = dataGenerator.generateWishItemTitle();
        String wishItemDescription = dataGenerator.generateWishItemDescription();
        WishItem wishItem = WishItem.builder().title(wishItemTitle).description(wishItemDescription).build();
        String wishItemId = steps.createWishItemWithApi(token, wishItem);

        Response response = step("Отправить запрос на удаление желания", () ->
                apiClient.deleteWishItem(token, wishItemId));

        step("Проверить, что желание удалено", () -> {
            assertThat(response.statusCode()).isEqualTo(204);
            assertThat(apiClient.getWishItemAuthorized(token, wishItemId).statusCode()).isEqualTo(404);
        });
    }

}
