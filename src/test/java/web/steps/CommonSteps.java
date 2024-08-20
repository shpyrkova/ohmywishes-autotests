package web.steps;

import api.ApiClient;
import config.common.UserDataConfig;
import io.qameta.allure.Step;
import models.lombok.UserCustomList;
import models.lombok.WishItem;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import web.pages.MyWishesPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CommonSteps {

    private final ApiClient apiClient = new ApiClient();
    protected static final UserDataConfig userDataConfig = ConfigFactory.create(UserDataConfig.class, System.getProperties());

    @Step("Установить авторизационные cookie в браузере")
    public void setAuthCookie() {
        String token = apiClient.requestToken(userDataConfig.getEmail(), userDataConfig.getPassword());
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("token", token));
    }

    @Step("Открыть страницу Мои желания")
    public void openMyWishesPage() {
        MyWishesPage myWishesPage = new MyWishesPage();
        myWishesPage.openPage(userDataConfig.getUsername());
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
