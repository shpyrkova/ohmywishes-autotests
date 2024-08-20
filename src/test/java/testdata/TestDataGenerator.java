package testdata;

import com.github.javafaker.Faker;

import java.util.Locale;


public class TestDataGenerator {

    static Faker faker = new Faker(new Locale("en-GB"));
    public static String wishPicture = "pictures/wish_avatar.png";

    public static String generatePassword() {
        return faker.internet().password();
    }

    public static String generateWishItemTitle() {
        return faker.food().dish() + " " + faker.food().ingredient();
    }

    public static String generateWishItemLink() {
        return String.format("https://" + faker.internet().domainName());
    }

    public static String generateWishItemDescription() {
        return faker.funnyName().name();
    }

    public static Integer generateMoneySum() {
        return faker.number().numberBetween(100, 999999);
    }

    public static String generateGameEndDate() {
        String day = String.valueOf(faker.number().numberBetween(10, 12));
        String month = String.valueOf(faker.number().numberBetween(10, 12));
        String year = String.valueOf(faker.number().numberBetween(2024, 2030));
        return day + month + year;
    }

    public static String generateWishItemCurrency() {
        String[] currencies = {"Рубль", "Доллар", "Евро", "Фунт стерлингов", "Тенге", "Лари", "Турецкая лира", "Драм",
                "Белорусский рубль", "Гривна", "Узбекский сум"};
        return faker.options().option(currencies);
    }

    public static String generateUserCustomListTitle() {
        return "list" + " " + faker.animal().name() + faker.food().sushi();
    }

    public static String generateUserCustomListDescription() {
        return faker.funnyName().name();
    }

    public static String generateGameTitle() {
        return "game" + " " + faker.food().fruit() + faker.food().spice();
    }

    public static String generateGameDescription() {
        return faker.address().city();
    }

}
