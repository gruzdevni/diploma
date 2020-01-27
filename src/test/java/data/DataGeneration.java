package data;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class DataGeneration {
    private static Faker faker = new Faker(new Locale("en"));

    public static String getName() {
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String name = firstName + " " + lastName;
        return name;
    }

    public static String approvedCard() {
        return "4444444444444441";
    }

    public static String declinedCard() {
        return "4444444444444442";
    }

    public static String wrongCard() {
        return "44444";
    }

    public static String getMonth() {
        int min = 1;
        int max = 12;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return String.format("%02d", i);
    }

    public static String getCVC() {
        int min = 100;
        int max = 999;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return String.valueOf(i);
    }
}
