package tests.mobile;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class MobileCommonSteps {

    @Step("Закрыть экран онбординга")
    public void closeOnboarding() {
        $(androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().resourceId(\"com.ohmywishes.start:id/go\"))")).click();
    }

}
