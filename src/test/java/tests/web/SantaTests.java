package tests.web;

import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.pages.SantaPage;

import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

@Epic("Web тесты")
@Tag("web")
@DisplayName("WEB. Игра 'Тайный санта'")
public class SantaTests extends TestBaseWeb {

    final SantaPage santaPage = new SantaPage();

    @Test
    @DisplayName("Создание игры Тайный санта с заполнением всех полей")
    void createSantaGameTest() {
        steps.setAuthCookie();
        steps.openMyWishesPage();
        String title = dataGenerator.generateGameTitle();
        String description = dataGenerator.generateGameDescription();
        Integer limit = dataGenerator.generateMoneySum();
        String endDate = dataGenerator.generateGameEndDate();

        step("Нажать Тайный санта -> Создать игру", () -> {
            myWishesPage.clickSecretSantaLink();
            santaPage.createGameButtonClick();
        });

        step("Создать игру с заполнением всех полей", () -> {
            santaPage.fillAllGameFields(title, endDate, description, limit);
            santaPage.nextButtonClick();
        });

        step("Проверить, что игра появилась в списке игр", () -> {
            santaPage.gameLink(title).shouldBe(visible);
        });
    }
}
