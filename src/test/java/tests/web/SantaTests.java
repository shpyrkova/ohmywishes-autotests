package tests.web;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.SantaPage;
import testdata.TestDataGenerator;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

@Tag("web")
@DisplayName("WEB. Игра 'Тайный санта'")
public class SantaTests extends TestBase {

    SantaPage santaPage = new SantaPage();

    @Test
    @DisplayName("Создание игры Тайный санта с заполнением всех полей")
    void createSantaGameTest() {
        steps.setAuthCookie();
        steps.openMyWishesPage();
        String title = TestDataGenerator.generateGameTitle();
        String description = TestDataGenerator.generateGameDescription();
        String limit = TestDataGenerator.generateMoneySum();
        String endDate = TestDataGenerator.generateGameEndDate();

        step("Нажать Тайный санта -> Создать игру", () -> {
            myWishesPage.secretSantaLinkClick();
            santaPage.createGameButtonClick();
        });

        step("Создать игру с заполнением всех полей", () -> {
            santaPage.fillAllGameFields(title, endDate, description, limit);
            santaPage.nextButtonClick();
            Selenide.refresh();
        });

        step("Проверить, что игра появилась в списке игр", () -> {
            String createdGameId = apiClient.getGameIdFromGameListByGameTitle(title);
            santaPage.gameLink(createdGameId).shouldBe(visible).shouldHave(text(title));
        });
    }
}
