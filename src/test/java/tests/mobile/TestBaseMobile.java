package tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.common.CredentialsConfig;
import config.mobile.MobileDriverProvider;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBaseMobile {

    final MobileCommonSteps steps = new MobileCommonSteps();
    protected static final CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class, System.getProperties());

    @BeforeAll
    static void beforeAll() {
        MobileDriverProvider mobileDriverProvider = new MobileDriverProvider();
        mobileDriverProvider.setMobileConfig();
        Configuration.timeout = 4000;
        Configuration.pollingInterval = 200;
        Configuration.browserSize = null;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
        steps.closeOnboarding();
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }
}
