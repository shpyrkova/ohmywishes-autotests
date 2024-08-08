package tests.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import pages.UserCustomListPage;
import pages.FulfilledListPage;
import testdata.TestDataGenerator;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("web")
@DisplayName("WEB. Работа с желаниями пользователя на странице 'Мои желания'")
public class MyWishesTests extends TestBase {

    @BeforeEach
    public void authorizeAndOpenMyWishesPage() {
        steps.setAuthCookie();
    }

    FulfilledListPage fulfilledListPage = new FulfilledListPage();
    UserCustomListPage userCustomListPage = new UserCustomListPage();

    @Test
    @Tags({@Tag("web"), @Tag("major")})
    @DisplayName("Создание нового желания с заполнением всех полей")
    void createWishItemTest() {
        String wishItemTitle = TestDataGenerator.generateWishItemTitle();
        String wishItemLink = TestDataGenerator.generateWishItemLink();
        String wishItemAvatar = TestDataGenerator.wishPicture;
        String wishItemDescription = TestDataGenerator.generateWishItemDescription();
        String wishItemPrice = TestDataGenerator.generateMoneySum();
        String wishItemCurrency = TestDataGenerator.generateWishItemCurrency();
        steps.openMyWishesPage();

        step("Создать желание с заполнением всех полей и аватаром", () -> {
            myWishesPage
                    .createWishItem(wishItemTitle, wishItemLink, wishItemDescription, wishItemPrice, wishItemCurrency, wishItemAvatar);
        });

        step("Проверить, что желание появилось в списке желаний", () -> {
            String createdWishItemId = apiClient.getWishItemIdFromWishlistByWishTitle(wishItemTitle);
            myWishesPage.wishItemCard(createdWishItemId).shouldBe(visible).shouldHave(text(wishItemTitle));
        });
    }

    @Test
    @Tags({@Tag("web"), @Tag("major")})
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
    @Tags({@Tag("web"), @Tag("major")})
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
        });

        step("Проверить, что желание появилось в списке", () -> {
            userCustomListPage.openPage(listId);
            userCustomListPage.wishItemCard(listId, wishItemId).shouldBe(visible);
        });
    }

    @Test
    @Tags({@Tag("web"), @Tag("major")})
    @DisplayName("Успешное копирование ссылки на вишлист по кнопке Поделиться")
    void shareWishlistTest() {
        String expectedLink = Configuration.baseUrl + myWishesPage.url;
        steps.openMyWishesPage();

        step("Скопировать ссылку на вишлист в буфер обмена", () -> {
            myWishesPage.shareWishListLink();
        });

        step("Проверить, что в буфере обмена сохранена ссылка на вишлист", () -> {
            String copiedWishlistLink = webTestsHelper.getClipboardContent();
            assertThat(copiedWishlistLink).isEqualTo(expectedLink);
        });

    }


}