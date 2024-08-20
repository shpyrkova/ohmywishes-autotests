package tests.api;

import io.qameta.allure.Epic;
import io.restassured.response.Response;
import models.lombok.UserCustomList;
import models.lombok.WishItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("API тесты")
@Tag("api")
@DisplayName("API. Кастомные списки желаний пользователя")
public class UserCustomListsTests extends TestBaseApi {

    String token;
    @BeforeEach
    public void authorizeAndOpenMyWishesPage() {
        token = apiClient.requestToken(userDataConfig.getEmail(), userDataConfig.getPassword());
    }

    @Test
    @DisplayName("Создание списка желаний")
    void createUserCustomListTest() {
        String listTitle = dataGenerator.generateUserCustomListTitle();
        String listDescription = dataGenerator.generateUserCustomListDescription();

        Response response = step("Создать список желаний", () ->
                apiClient.createUserCustomList(token, listTitle, listDescription));
        UserCustomList responseUserCustomList = response.as(UserCustomList.class);

        step("Проверить, что название и описание созданного списка корректны", () -> {
            assertThat(response.statusCode()).isEqualTo(201);
            assertThat(responseUserCustomList.getTitle()).isEqualTo(listTitle);
            assertThat(responseUserCustomList.getDescription()).isEqualTo(listDescription);
        });
    }

    @Test
    @DisplayName("При удалении кастомного списка желаний желания из этого списка не удаляются из системы")
    void deleteListWithWishesTest() {
        String listTitle = dataGenerator.generateUserCustomListTitle();
        String listDescription = dataGenerator.generateUserCustomListDescription();
        String listId = steps.createUserCustomWishlistWithApi(token, listTitle, listDescription);
        String wishItemTitle = dataGenerator.generateWishItemTitle();
        String wishItemDescription = dataGenerator.generateWishItemDescription();
        WishItem wishItem = WishItem.builder().title(wishItemTitle).description(wishItemDescription).build();
        String wishItemId = steps.createWishItemWithApi(token, wishItem);
        step("Добавить желание в список желаний", () -> {
            apiClient.addWishItemToList(token, wishItem, listId);
        });

        Response response = step("Удалить список желаний", () -> apiClient.deleteUserCustomList(token, listId));

        step("Проверить, что желание из списка осталось в системе", () -> {
            assertThat(response.statusCode()).isEqualTo(204);
            assertThat(apiClient.getWishItemAuthorized(token, wishItemId).statusCode()).isEqualTo(200);
        });
    }

}
