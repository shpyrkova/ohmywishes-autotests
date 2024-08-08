package tests.web;

import api.ApiClient;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ApiConfig;
import config.CredentialsConfig;
import config.web.WebDriverProvider;
import helpers.Attachments;
import helpers.WebTestsHelper;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import pages.LoginPage;
import pages.MainPage;
import pages.MyWishesPage;
import steps.CommonSteps;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    protected WebTestsHelper webTestsHelper = new WebTestsHelper();
    public ApiClient apiClient = new ApiClient();
    CommonSteps steps = new CommonSteps();
    protected static CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class, System.getProperties());
    protected static ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());

    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    MyWishesPage myWishesPage = new MyWishesPage();

    @BeforeEach
    public void setUp() {
        WebDriverProvider webDriverProvider = new WebDriverProvider();
        webDriverProvider.setWebConfig();
        RestAssured.baseURI = apiConfig.getBaseUri();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        Attachments.screenshotAs("Last step screenshot");
        closeWebDriver();
    }

}
