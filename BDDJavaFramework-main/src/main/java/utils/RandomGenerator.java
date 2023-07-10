package utils;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class RandomGenerator {
    private static Logger log = LoggerFactory.getLogger(RandomGenerator.class);

    private static Faker faker = new Faker(new Locale("en-US"));

    private RandomGenerator () {
    }

    public static Faker getFaker () {
        return faker;
    }

    public static String numeric (int length) {
        return String.valueOf(faker.number().randomNumber(length, true));
    }

    public static String phoneNumberUnFormatted () {
        return faker.numerify("##########");
    }

    public static String phoneNumber () {
        return faker.phoneNumber().phoneNumber();
    }

    public static String cellPhone () {
        return faker.phoneNumber().cellPhone();
    }

    public static String firstName () {
        return RandomStringUtils.randomAlphanumeric(6) +faker.name().firstName();
    }

    public static String lastName () {
        return RandomStringUtils.randomAlphanumeric(6)+faker.name().lastName();
    }

    public static String fullName () {
        return faker.name().fullName();
    }

    public static String businessName () {
        return RandomStringUtils.randomAlphanumeric(4)+faker.company().name();
    }

    public static String emailAddress () {
        return faker.internet().emailAddress();
    }
}
