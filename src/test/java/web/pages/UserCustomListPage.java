package web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UserCustomListPage {

    private String url(String username, String userCustomListId) {
        return String.format("/users/%s/lists/%s", username, userCustomListId);
    }

    public void openPage(String username, String userCustomListId) {
        open(url(username, userCustomListId));
    }

    public SelenideElement wishItemCard(String username, String listId, String wishId) {
        return $(String.format("[href='/users/%s/lists/%s/wishes/%s']", username, listId, wishId));
    }

}
