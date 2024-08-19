package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class WishItemComponent {

    private final SelenideElement wishTitleInput = $("#title");
    private final SelenideElement wishLinkInput = $("#link");
    private final SelenideElement wishDescriptionInput = $("#description");
    private final SelenideElement wishPriceInput = $("#price");
    private final SelenideElement wishCurrencyInput = $(byText("Цена")).sibling(0);
    private final SelenideElement wishPictureInput = $("input[type=file]");
    private final SelenideElement submitButton = $(byText("Загадать"));

    private SelenideElement wishCurrencyName(String currency) {
        return $(byText(currency));
    }

    public void fillAllWishItemFields(String title, String link, String description, String price, String currency, String filePath) {
        wishTitleInput.setValue(title);
        wishLinkInput.setValue(link);
        wishDescriptionInput.setValue(description);
        wishPriceInput.setValue(price);
        wishCurrencyInput.click();
        wishCurrencyName(currency).scrollIntoView(true).click();
        wishPictureInput.uploadFromClasspath(filePath);
    }

    public void submitButtonClick() {
        submitButton.click();
    }


}
