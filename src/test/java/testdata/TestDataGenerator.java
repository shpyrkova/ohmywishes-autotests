package testdata;

import com.github.javafaker.Faker;

import java.util.Locale;


public class TestDataGenerator {

    static Faker faker = new Faker(new Locale("en-GB"));
    public String wishPicture = "pictures/wish_avatar.png";

    public String generatePassword() {
        return faker.internet().password();
    }

    public String generateWishItemTitle() {
        return faker.food().dish() + " " + faker.food().ingredient();
    }

    public String generateWishItemLink() {
        return String.format("https://" + faker.internet().domainName());
    }

    public String generateWishItemDescription() {
        return faker.funnyName().name();
    }

    public Integer generateMoneySum() {
        return faker.number().numberBetween(100, 999999);
    }

    public String generateGameEndDate() {
        String day = String.valueOf(faker.number().numberBetween(10, 12));
        String month = String.valueOf(faker.number().numberBetween(10, 12));
        String year = String.valueOf(faker.number().numberBetween(2024, 2030));
        return day + month + year;
    }

    public String generateWishItemCurrency() {
        String[] currencies = {"Рубль", "Доллар", "Евро", "Фунт стерлингов", "Тенге", "Лари", "Турецкая лира", "Драм",
                "Белорусский рубль", "Гривна", "Узбекский сум"};
        return faker.options().option(currencies);
    }

    public String generateUserCustomListTitle() {
        return "list" + " " + faker.animal().name() + faker.food().sushi();
    }

    public String generateUserCustomListDescription() {
        return faker.funnyName().name();
    }

    public String generateGameTitle() {
        return "game" + " " + faker.food().fruit() + faker.food().spice();
    }

    public String generateGameDescription() {
        return faker.address().city();
    }

}
