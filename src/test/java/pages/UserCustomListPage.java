package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UserCustomListPage {

    private String url(String userCustomListId) {
        return String.format("/users/surkova/lists/%s", userCustomListId);
    }

    public void openPage(String userCustomListId) {
        open(url(userCustomListId));
    }
    public SelenideElement wishItemCard(String listId, String wishId) {
        return $(String.format("[href='/users/surkova/lists/%s/wishes/%s']", listId, wishId));
    }

}
