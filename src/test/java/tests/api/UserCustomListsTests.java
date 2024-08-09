package tests.api;

import io.qameta.allure.Epic;
import io.restassured.response.Response;
import models.lombok.UserCustomList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testdata.TestDataGenerator;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("API тесты")
@Tag("api")
@DisplayName("API. Кастомные списки желаний пользователя")
public class UserCustomListsTests extends TestBase {

    @Test
    @DisplayName("Создание списка желаний")
    void createUserCustomListTest() {
        String listTitle = TestDataGenerator.generateUserCustomListTitle();
        String listDescription = TestDataGenerator.generateUserCustomListDescription();

        Response response = step("Создать список желаний", () ->
                apiClient.createUserCustomList(listTitle, listDescription));
        UserCustomList responseUserCustomList = response.as(UserCustomList.class);

        step("Проверить, что название и описание созданного списка корректны", () -> {
            assertThat(responseUserCustomList.getTitle()).isEqualTo(listTitle);
            assertThat(responseUserCustomList.getDescription()).isEqualTo(listDescription);
        });
    }

    @Test
    @DisplayName("При удалении кастомного списка желаний желания из этого списка не удаляются из системы")
    void deleteListWithWishesTest() {
        String listTitle = TestDataGenerator.generateUserCustomListTitle();
        String listDescription = TestDataGenerator.generateUserCustomListDescription();
        String listId = steps.createUserCustomWishlistWithApi(listTitle, listDescription);
        String wishItemTitle = TestDataGenerator.generateWishItemTitle();
        String wishItemDescription = TestDataGenerator.generateWishItemDescription();
        String wishItemId = steps.createWishItemWithApi(wishItemTitle, wishItemDescription);
        step("Добавить желание в список желаний", () -> {
            apiClient.addWishItemToList(wishItemId, wishItemTitle, listId);
        });

        Response response = step("Удалить список желаний", () -> apiClient.deleteUserCustomList(listId));

        step("Проверить, что желание из списка осталось в системе", () -> {
            assertThat(response.statusCode()).isEqualTo(204);
            assertThat(apiClient.getWishItemAuthorized(wishItemId).statusCode()).isEqualTo(200);
        });
    }

}
