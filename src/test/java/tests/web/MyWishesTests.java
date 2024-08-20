package tests.web;

import io.qameta.allure.Epic;
import models.lombok.WishItem;
import org.junit.jupiter.api.*;
import web.pages.UserCustomListPage;
import web.pages.FulfilledListPage;
import testdata.TestDataGenerator;

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
        String wishItemTitle = TestDataGenerator.generateWishItemTitle();
        String wishItemLink = TestDataGenerator.generateWishItemLink();
        String wishItemPictureLink = TestDataGenerator.wishPicture;
        String wishItemDescription = TestDataGenerator.generateWishItemDescription();
        Integer wishItemPrice = TestDataGenerator.generateMoneySum();
        String wishItemCurrency = TestDataGenerator.generateWishItemCurrency();
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
            myWishesPage.wishItemTitle(wishItemTitle).shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Отметка желания как подаренного")
    void markWishAsGiftedTest() {
        String wishItemTitle = TestDataGenerator.generateWishItemTitle();
        String wishItemDescription = TestDataGenerator.generateWishItemDescription();
        String wishItemId = steps.createWishItemWithApi(wishItemTitle, wishItemDescription);
        steps.openMyWishesPage();

        step("Отметить желание подаренным", () -> {
            myWishesPage.wishItemCard(wishItemId).hover();
            myWishesPage.markWishItemAsGifted(wishItemId);
        });

        step("Желание должно пропасть из списка желаний и появиться в списке исполненных", () -> {
            myWishesPage.wishItemCard(wishItemId).shouldNotBe(visible);
            myWishesPage.fulfilledListLinkClick();
            fulfilledListPage.fulfilledWishItemCard(wishItemId).shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Добавление желания в созданный пользователем список")
    void addWishToListTest() {
        String wishItemTitle = TestDataGenerator.generateWishItemTitle();
        String wishItemDescription = TestDataGenerator.generateWishItemDescription();
        String listTitle = TestDataGenerator.generateUserCustomListTitle();
        String listDescription = TestDataGenerator.generateUserCustomListDescription();
        String wishItemId = steps.createWishItemWithApi(wishItemTitle, wishItemDescription);
        String listId = steps.createUserCustomWishlistWithApi(listTitle, listDescription);
        steps.openMyWishesPage();

        step("Добавить желание в список желаний", () -> {
            myWishesPage.addWishItemToList(wishItemId, listTitle);
            myWishesPage.wishUpdatedMessage.shouldBe(visible);
        });

        step("Проверить, что желание появилось в списке", () -> {
            userCustomListPage.openPage(listId);
            userCustomListPage.wishItemCard(listId, wishItemId).shouldBe(visible);
        });
    }

}