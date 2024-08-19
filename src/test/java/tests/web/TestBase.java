package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ApiConfig;
import config.CredentialsConfig;
import config.web.WebDriverConfig;
import config.web.WebDriverProvider;
import helpers.Attachments;
import helpers.WebTestsHelper;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.LoginPage;
import pages.MainPage;
import pages.MyWishesPage;
import steps.CommonSteps;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    protected final WebTestsHelper webTestsHelper = new WebTestsHelper();
    private final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    final CommonSteps steps = new CommonSteps();
    protected static final CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class, System.getProperties());
    protected static final ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());

    final MainPage mainPage = new MainPage();
    final LoginPage loginPage = new LoginPage();
    final MyWishesPage myWishesPage = new MyWishesPage();

    @BeforeAll
    public static void beforeAll() {
        WebDriverProvider webDriverProvider = new WebDriverProvider();
        webDriverProvider.setWebConfig();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        RestAssured.baseURI = apiConfig.getBaseUri();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        Attachments.screenshotAs("Last step screenshot");
        Attachments.browserConsoleLogs();
        if (config.isRemote()) {
            Attachments.addVideo();
        }
        closeWebDriver();
    }

}
