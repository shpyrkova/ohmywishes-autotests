package web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FulfilledListPage {

    public SelenideElement getFulfilledWishItemCard(String id) {
        return $(String.format("[href='/fulfilled/wishes/%s']", id));
    }

}
