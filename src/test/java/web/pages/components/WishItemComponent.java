package web.pages.components;

import com.codeborne.selenide.SelenideElement;
import models.lombok.WishItem;

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

    public void fillAllWishItemFields(WishItem wishItem) {
        wishTitleInput.setValue(wishItem.getTitle());
        wishLinkInput.setValue(wishItem.getLink());
        wishDescriptionInput.setValue(wishItem.getDescription());
        wishPriceInput.setValue(String.valueOf(wishItem.getPrice()));
        wishCurrencyInput.click();
        wishCurrencyName(wishItem.getCurrency()).scrollIntoView(true).click();
        wishPictureInput.uploadFromClasspath(wishItem.getPicture());
    }

    public void submitButtonClick() {
        submitButton.click();
    }

}
