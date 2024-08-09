package tests.api;

import io.restassured.response.Response;
import models.lombok.*;
import models.lombok.api.GetWishItemResponseBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testdata.TestDataGenerator;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.*;

@Tag("api")
@DisplayName("API. Желания пользователя")
public class UserWishesTests extends TestBase {

    @Test
    @DisplayName("Получение данных о желании неавторизованным пользователем")
    void getWishItemTest() {
        String wishItemTitle = TestDataGenerator.generateWishItemTitle();
        String wishItemDescription = TestDataGenerator.generateWishItemDescription();
        String wishItemId = steps.createWishItemWithApi(wishItemTitle, wishItemDescription);

        Response response = step("Отправить запрос на получение данных о желании без авторизации", () ->
                apiClient.getWishItemNonAuthorized(wishItemId));
        GetWishItemResponseBody responseBody = response.as(GetWishItemResponseBody.class);
        WishItem responseWishItem = responseBody.getItem();

        step("Проверить, что название и описание созданного желания корректны", () -> {
            assertThat(responseWishItem.getTitle()).isEqualTo(wishItemTitle);
            assertThat(responseWishItem.getDescription()).isEqualTo(wishItemDescription);
        });
    }

    @Test
    @DisplayName("Удаление желания")
    void deleteWishItemTest() {
        String wishItemTitle = TestDataGenerator.generateWishItemTitle();
        String wishItemDescription = TestDataGenerator.generateWishItemDescription();
        String wishItemId = steps.createWishItemWithApi(wishItemTitle, wishItemDescription);

        Response response = step("Отправить запрос на удаление желания", () ->
                apiClient.deleteWishItem(wishItemId));

        step("Проверить, что желание удалено", () -> {
            assertThat(response.statusCode()).isEqualTo(204);
            assertThat(apiClient.getWishItemAuthorized(wishItemId).statusCode()).isEqualTo(404);
        });
    }

}
