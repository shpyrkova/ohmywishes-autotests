package tests.web;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Web тесты")
@Tag("web")
@DisplayName("WEB. Авторизация по электронной почте")
public class LoginTests extends TestBaseWeb {

    @Test
    @DisplayName("Успешная авторизация по email и паролю")
    void loginWithEmailTest() {
        String email = userDataConfig.getEmail();
        String password = userDataConfig.getPassword();

        step("Открыть главную страницу, нажать Войти", () -> {
            open("");
            mainPage.clickLoginButton();
        });

        step("Войти по электронной почте", () -> {
            loginPage.clickByEmailButton();
            loginPage.login(email, password);
        });

        step("После авторизации есть меню Мои желания со ссылкой на профиль пользователя", () -> {
            String username = userDataConfig.getUsername();
            myWishesPage.getMyWishesLink(username).shouldBe(visible);
        });

    }

    @Test
    @DisplayName("Попытка авторизации с невалидным паролем")
    void loginWithInvalidPasswordTest() {
        String email = userDataConfig.getEmail();
        String wrongPassword = dataGenerator.generatePassword();
        String expectedUrl = Configuration.baseUrl + loginPage.url;

        step("Открыть главную страницу, нажать Войти", () -> {
            open("");
            mainPage.clickLoginButton();
        });

        step("Войти по электронной почте с невалидным паролем", () -> {
            loginPage.clickByEmailButton();
            loginPage.login(email, wrongPassword);
        });

        step("Всплывает ошибка о неверном логине или пароле, юзер остается на странице авторизации", () -> {
            loginPage.wrongEmailOrPasswordMessage.shouldBe(visible);
            String currentUrl = webTestsHelper.getCurrentUrl();
            assertThat(currentUrl).isEqualTo(expectedUrl);
        });

    }

}
