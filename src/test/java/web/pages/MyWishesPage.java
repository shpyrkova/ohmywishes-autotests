package web.pages;

import com.codeborne.selenide.SelenideElement;
import models.lombok.WishItem;
import web.pages.components.WishItemComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MyWishesPage {

    public final SelenideElement createWishButton = $(byText("Загадать желание"));
    private final SelenideElement secretSantaLink = $("[href='/santa']");
    private final SelenideElement fulfilledListLink = $("[href='/fulfilled']");
    private final SelenideElement addWishItemToListButton = $(byText("Добавить в список"));
    public final SelenideElement wishUpdatedMessage = $(byText("Желание обновлено"));

    public SelenideElement getMyWishesLink(String username) {
        return $(String.format("[href='/users/%s']", username)).shouldHave(text("Мои желания"));
    }

    public SelenideElement getWishItemCard(String username, String id) {
        return $(String.format("[href='/users/%s/wishes/%s']", username, id));
    }

    public SelenideElement getWishItemTitle(String title) {
        return $(byText(title));
    }

    public SelenideElement getWishItemOptions(String username, String id) {
        return getWishItemCard(username, id).$(byText("Опции"));
    }

    final WishItemComponent wishItemComponent = new WishItemComponent();

    public void openPage(String username) {
        open("/users/" + username);
    }

    public void clickSecretSantaLink() {
        secretSantaLink.click();
    }

    public void createWishItem(WishItem wishItem) {
        createWishButton.shouldBe(visible).click();
        wishItemComponent.fillAllWishItemFields(wishItem);
        wishItemComponent.submitButtonClick();
    }

    public void addWishItemToList(String username, String id, String listName) {
        getWishItemOptions(username, id).click();
        addWishItemToListButton.hover();
        addWishItemToListButton.$(byText(listName)).scrollIntoView(true).click();
        addWishItemToListButton.$(byText("Сохранить")).click();
    }

    public void markWishItemAsGift(String username, String id) {
        getWishItemOptions(username, id).click();
        $(byText("Подарили")).click();
    }

    public void clickFulfilledListLink() {
        fulfilledListLink.click();
    }

}
