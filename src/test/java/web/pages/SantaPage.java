package web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SantaPage {

    private final SelenideElement createGameButton = $(byText("Создать игру"));
    private final SelenideElement gameTitleInput = $("#title");
    private final SelenideElement gameEndDateInput = $("#date");
    private final SelenideElement gameDescriptionInput = $("#description");
    private final SelenideElement gameLimitInput = $("#limit");
    private final SelenideElement nextButton = $(byText("Далее"));

    public SelenideElement gameLink(String title) {
        return $(byLinkText(title));
    }

    public void createGameButtonClick() {
        createGameButton.click();
    }

    public void fillAllGameFields(String title, String date, String description, Integer limit) {
        gameTitleInput.setValue(title);
        gameEndDateInput.setValue(date);
        gameDescriptionInput.setValue(description);
        gameLimitInput.setValue(String.valueOf(limit));
    }

    public void nextButtonClick() {
        nextButton.click();
    }

}
