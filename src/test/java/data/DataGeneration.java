package data;

import com.github.javafaker.Faker;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

class DataGeneration {
    private Faker faker = new Faker(new Locale("en"));

    public String getName() {
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String name = firstName + " " + lastName;
        return name;
    }

    public String approvedCard() {
        return "4444444444444441";
    }

    public String declinedCard() {
        return "4444444444444442";
    }

    public String wrongCard() {
        return "44444";
    }

    String getMonth() {
        int min = 1;
        int max = 12;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return String.format("%02d", i);
    }

    public String getCVC() {
        int min = 100;
        int max = 999;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return String.valueOf(i);
    }

    public String getYear() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int futureYear = year - 2000 + 3;
        return String.valueOf(futureYear);
    }
}
