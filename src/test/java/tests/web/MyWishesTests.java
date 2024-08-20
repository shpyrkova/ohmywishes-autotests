package tests.web;

import io.qameta.allure.Epic;
import models.lombok.WishItem;
import org.junit.jupiter.api.*;
import web.pages.UserCustomListPage;
import web.pages.FulfilledListPage;

import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

@Epic("Web тесты")
@Tag("web")
@DisplayName("WEB. Работа с желаниями пользователя на странице 'Мои желания'")
public class MyWishesTests extends TestBaseWeb {

    @BeforeEach
    public void authorizeAndOpenMyWishesPage() {
        steps.setAuthCookie();
    }

    final FulfilledListPage fulfilledListPage = new FulfilledListPage();
    final UserCustomListPage userCustomListPage = new UserCustomListPage();

    @Test
    @DisplayName("Создание нового желания с заполнением всех полей")
    void createWishItemTest() {
        String wishItemTitle = dataGenerator.generateWishItemTitle();
        String wishItemLink = dataGenerator.generateWishItemLink();
        String wishItemPictureLink = dataGenerator.wishPicture;
        String wishItemDescription = dataGenerator.generateWishItemDescription();
        Integer wishItemPrice = dataGenerator.generateMoneySum();
        String wishItemCurrency = dataGenerator.generateWishItemCurrency();
        WishItem wishItem = WishItem.builder()
                .title(wishItemTitle)
                .link(wishItemLink)
                .description(wishItemDescription)
                .price(wishItemPrice)
                .currency(wishItemCurrency)
                .picture(wishItemPictureLink).build();

        steps.openMyWishesPage();

        step("Создать желание с заполнением всех полей и аватаром", () -> {
            myWishesPage
                    .createWishItem(wishItem);
        });

        step("Проверить, что желание появилось в списке желаний", () -> {
            myWishesPage.getWishItemTitle(wishItemTitle).shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Отметка желания как подаренного")
    void markWishAsGiftedTest() {
        String wishItemTitle = dataGenerator.generateWishItemTitle();
        String wishItemDescription = dataGenerator.generateWishItemDescription();
        String wishItemId = steps.createWishItemWithApi(wishItemTitle, wishItemDescription);
        String username = userDataConfig.getUsername();
        steps.openMyWishesPage();

        step("Отметить желание подаренным", () -> {
            myWishesPage.getWishItemCard(username, wishItemId).hover();
            myWishesPage.markWishItemAsGift(username, wishItemId);
        });

        step("Желание должно пропасть из списка желаний и появиться в списке исполненных", () -> {
            myWishesPage.getWishItemCard(username, wishItemId).shouldNotBe(visible);
            myWishesPage.clickFulfilledListLink();
            fulfilledListPage.getFulfilledWishItemCard(wishItemId).shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Добавление желания в созданный пользователем список")
    void addWishToListTest() {
        String wishItemTitle = dataGenerator.generateWishItemTitle();
        String wishItemDescription = dataGenerator.generateWishItemDescription();
        String listTitle = dataGenerator.generateUserCustomListTitle();
        String listDescription = dataGenerator.generateUserCustomListDescription();
        String wishItemId = steps.createWishItemWithApi(wishItemTitle, wishItemDescription);
        String listId = steps.createUserCustomWishlistWithApi(listTitle, listDescription);
        String username = userDataConfig.getUsername();
        steps.openMyWishesPage();

        step("Добавить желание в список желаний", () -> {
            myWishesPage.addWishItemToList(username, wishItemId, listTitle);
            myWishesPage.wishUpdatedMessage.shouldBe(visible);
        });

        step("Проверить, что желание появилось в списке", () -> {
            userCustomListPage.openPage(userDataConfig.getUsername(), listId);
            userCustomListPage.wishItemCard(username, listId, wishItemId).shouldBe(visible);
        });
    }

}