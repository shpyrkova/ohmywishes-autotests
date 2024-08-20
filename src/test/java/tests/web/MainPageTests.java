package tests.web;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Web тесты")
@Tag("web")
@DisplayName("WEB. Возможности главной страницы без авторизации")
public class MainPageTests extends TestBaseWeb {

    @Test
    @DisplayName("Для неавторизованного пользователя по нажатию 'Мои желания' открывается страница авторизации")
    void Test() {
        step("Открыть главную страницу", () -> {
            open("");
        });

        step("Нажать Мои желания", mainPage::clickMyWishesLink);

        step("Открыта страница авторизации", () -> {
            loginPage.byEmailButton.shouldBe(Condition.visible);
            String currentUrl = webTestsHelper.getCurrentUrl();
            assertEquals("https://ohmywishes.ru/authorization", currentUrl);
        });
    }

}
