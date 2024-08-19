package web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public final String url = "/authorization";

    private final SelenideElement emailInput = $("#email");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement nextButton = $(byText("Далее"));
    public final SelenideElement byEmailButton = $(byText("Электронная почта"));
    public final SelenideElement wrongEmailOrPasswordMessage = $(byText("Введен неверный email или пароль"));

    public void login(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        nextButton.click();
    }

    public void byEmail() {
        byEmailButton.click();
    }

}
