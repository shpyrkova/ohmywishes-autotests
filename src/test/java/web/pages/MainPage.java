package web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private final SelenideElement loginButton = $(byText("Войти"));
    private final SelenideElement myWishesLink = $(byText("Мои желания"));

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickMyWishesLink() {
        myWishesLink.click();
    }

}
