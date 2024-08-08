package steps;

import api.ApiClient;
import io.qameta.allure.Step;
import models.lombok.UserCustomList;
import models.lombok.WishItem;
import org.openqa.selenium.Cookie;
import pages.MyWishesPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CommonSteps {

    private final ApiClient apiClient = new ApiClient();

    @Step("Установить авторизационные cookie в браузере")
    public void setAuthCookie() {
        String token = apiClient.generateToken();
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("token", token));
    }

    @Step("Открыть страницу Мои желания")
    public void openMyWishesPage() {
        MyWishesPage myWishesPage = new MyWishesPage();
        myWishesPage.openPage();
    }

    @Step("Создать желание через API и получить его id")
    public String createWishItemWithApi(String title, String description) {
        return apiClient.createWishItem(title, description).as(WishItem.class).getUnderscoreId();
    }

    @Step("Создать список желаний через API и получить его id")
    public String createUserCustomWishlistWithApi(String title, String description) {
        return apiClient.createUserCustomList(title, description).as(UserCustomList.class).getUnderscoreId();
    }

}
