package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.WishItemComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MyWishesPage {

    public final String url = "/users/surkova";
    public final SelenideElement createWishButton = $(byText("Загадать желание"));
    private final SelenideElement secretSantaLink = $("[href='/santa']");
    private final SelenideElement fulfilledListLink = $("[href='/fulfilled']");
    private SelenideElement addWishItemToListButton = $(byText("Добавить в список"));
    public SelenideElement myWishesLink(String username) {
        return $(String.format("[href='/users/%s']", username)).shouldHave(text("Мои желания"));
    }
    private SelenideElement shareWishListLinkButton = $(byText("Поделиться"));
    private SelenideElement copyWishListLinkButton = $(byText("Скопировать ссылку"));

    public SelenideElement wishItemCard(String id) {
        return $(String.format("[href='/users/surkova/wishes/%s']", id));
    }

    public SelenideElement wishItemOptions(String id) {
        return wishItemCard(id).$(byText("Опции"));
    }

    WishItemComponent wishItemComponent = new WishItemComponent();

    public void openPage() {
        open(url);
    }

    public void secretSantaLinkClick() {
        secretSantaLink.click();
    }

    public void shareWishListLink() {
        shareWishListLinkButton.click();
        copyWishListLinkButton.click();
    }

    public void createWishItem(String title, String link, String description, String price, String currency, String filePath) {
        createWishButton.shouldBe(visible).click();
        wishItemComponent.fillAllWishItemFields(title, link, description, price, currency, filePath);
        wishItemComponent.submitButtonClick();
    }

    public void addWishItemToList(String id, String listName) {
        wishItemOptions(id).click();
        addWishItemToListButton.hover();
        addWishItemToListButton.$(byText(listName)).scrollIntoView(true).click();
        addWishItemToListButton.$(byText("Сохранить")).click();
    }

    public void markWishItemAsGifted(String id) {
        wishItemOptions(id).click();
        $(byText("Подарили")).click();
    }

    public void fulfilledListLinkClick() {
        fulfilledListLink.click();
    }


}
