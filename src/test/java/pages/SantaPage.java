package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SantaPage {

    private final SelenideElement createGameButton = $(byText("Создать игру"));
    private final SelenideElement gameTitleInput = $("#title");
    private final SelenideElement gameEndDateInput = $("#date");
    private final SelenideElement gameDescriptionInput = $("#description");
    private final SelenideElement gameLimitInput = $("#limit");
    private final SelenideElement nextButton = $(byText("Далее"));

    public SelenideElement gameLink(String gameId) {
        return $(String.format("[href='/game/%s']", gameId));
    }

    public void createGameButtonClick() {
        createGameButton.click();
    }

    public void fillAllGameFields(String title, String date, String description, String limit) {
        gameTitleInput.setValue(title);
        gameEndDateInput.setValue(date);
        gameDescriptionInput.setValue(description);
        gameLimitInput.setValue(limit);
    }

    public void nextButtonClick() {
        nextButton.click();
    }

}
