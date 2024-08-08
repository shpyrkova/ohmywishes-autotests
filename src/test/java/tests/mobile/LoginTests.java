package tests.mobile;

import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Tag("mobile")
@DisplayName("Авторизация в приложении")
public class LoginTests extends TestBase {

    @Test
    @DisplayName("Авторизация по электронной почте")
    void loginWithEmailTest() {
        step("Выбрать вход по электронной почте", () -> {
            $(id("com.ohmywishes.start:id/email")).click();
        });

        step("Ввести email и пароль, нажать Далее", () -> {
            $(id("com.ohmywishes.start:id/editTextEmail")).sendKeys(credentialsConfig.getEmail());
            $(id("com.ohmywishes.start:id/editTextPassword")).sendKeys(credentialsConfig.getPassword());
            $(id("com.ohmywishes.start:id/tvNext")).click();
        });

        step("Отклонить запрос на отправку уведомлений", () -> {
            $(id("com.android.permissioncontroller:id/permission_deny_button")).click();
        });

        step("Перейти на экран 'Мои желания'", () -> {
            $(id("com.ohmywishes.start:id/icon_my")).click();
        });

        step("На экране 'Мои желания' Отображается имя пользователя", () -> {
            $(id("com.ohmywishes.start:id/textViewProfileFullName")).shouldHave(text("Gentle Monster"));
        });
    }

    @Test
    @DisplayName("Запрос на сброс пароля")
    void resetPasswordRequestTest() {
        step("Выбрать вход по электронной почте", () -> {
            $(id("com.ohmywishes.start:id/email")).click();
        });

        step("Нажать 'Забылм пароль'", () -> {
            $(id("com.ohmywishes.start:id/textViewSignInEmailForgotPassword")).click();
        });

        step("Ввести почту для сброса пароля, нажать Далее", () -> {
            $(id("com.ohmywishes.start:id/editText")).sendKeys(credentialsConfig.getEmail());
            $(id("com.ohmywishes.start:id/tvNext")).click();
        });

        step("Открыт экран 'Осталось проверить почту'", () -> {
            $(id("com.ohmywishes.start:id/textViewDoneTitleCheckEmail")).shouldBe(visible);
        });
    }

}