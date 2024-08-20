package testdata;

import com.github.javafaker.Faker;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import java.text.SimpleDateFormat;
import java.util.Date;


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
        Calendar startDate = new GregorianCalendar(2025, Calendar.NOVEMBER, 10);
        Calendar endDate = new GregorianCalendar(2025, Calendar.NOVEMBER, 12);

        Date randomDate = faker.date().between(startDate.getTime(), endDate.getTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        return dateFormat.format(randomDate);
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
