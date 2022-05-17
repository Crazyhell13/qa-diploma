package ru.netology.web.data;

import com.github.javafaker.*;
import lombok.Value;

import java.time.LocalDate;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DataHelper {
    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String cardHolder;
        String cvc;
    }

    //номер карты
    private static String getCardNumber(String status, String locale) {
        String digit = new Faker().number().digits(1);
        if (status.equals("APPROVED")) {
            return "4444 4444 4444 4441";
        }
        if (status.equals("DECLINED")) {
            return "4444 4444 4444 4442";
        }
        if (status.equals("short")) {
            return "4444 4444 4444 444";
        }
        if (status.equals("digit")) {
            return digit;
        }
        if (status.equals("random")) {
            return new Faker(new Locale(locale)).finance().creditCard(CreditCardType.MASTERCARD);
        }
        if (status.equals("empty")) {
            return "";
        }
        if (status.equals("allZero")) {
            return "0000 0000 0000 0000";
        }
        return null;
    }

    //месяц
    private static String getMonth(String status) {
        Faker faker = new Faker();
        if (status.equals("digit")) {
            return String.valueOf(faker.number().digits(1));
        }
        if (status.equals("doubleZero")) {
            return "00";
        }
        if (status.equals("badRandom")) {
            return String.valueOf(faker.number().numberBetween(13, 99));
        }
        if (status.equals("current")) {
            return LocalDate.now().format(ofPattern("MM"));
        }
        if (status.equals("past")) {
            return LocalDate.now().minusMonths(1).format(ofPattern("MM"));
        }
        if (status.equals("future")) {
            return LocalDate.now().plusMonths(1).format(ofPattern("MM"));
        }
        if (status.equals("empty")) {
            return "";
        }
        return null;
    }

    //год
    private static String getYear(String status) {
        String digit = new Faker().number().digits(1);
        if (status.equals("digit")) {
            return digit;
        }
        if (status.equals("doubleZero")) {
            return "00";
        }
        if (status.equals("moreThanFive")) {
            return LocalDate.now().plusYears(6).format(ofPattern("yy"));
        }
            if (status.equals("current")) {
                return LocalDate.now().format(ofPattern("yy"));
            }
            if (status.equals("past")) {
                return LocalDate.now().minusYears(new Faker().number().numberBetween(1, 20)).format(ofPattern("yy"));
            }
            if (status.equals("future")) {
                return LocalDate.now().plusYears(new Faker().number().numberBetween(1, 5)).format(ofPattern("yy"));
            }
            if (status.equals("empty")) {
                return "";
            }
            return null;
        }


        //holderName
        private static String getFullName (String status){
            Faker faker = new Faker();
            if (status.equals("valid")) {
                return faker.name().firstName() + " " + faker.name().lastName();
            }
            if (status.equals("russian")) {
                return new Faker(new Locale("ru")).name().fullName();
            }
            if (status.equals("digits")) {
                return faker.name().firstName() + "" + faker.number().numberBetween(1, 999);
            }
            if (status.equals("specialSymbols")) {
                return "+_.,/~'@\"-=?&%^()$#№;*:!";
            }
            if (status.equals("asian")) {
                return new Faker(new Locale("ch")).name().fullName();
            }
            if (status.equals("short")) {
                return "Y";
            }
            //будем считать, что максимально 27
            if (status.equals("long")) {
                return "YYYYYYYYYYYYYYYYYYYY YYYYYYYY";
            }
            if (status.equals("space")) {
                return "   ";
            }
            if (status.equals("empty")) {
                return "";
            }
            return null;
        }

        //CVV
        private static String getCVC (String status){
            String cvc = new Faker().number().digits(3);
            if (status.equals("tripleZero")) {
                return "000";
            }
            if (status.equals("short")) {
                return String.valueOf(new Faker().number().numberBetween(0, 99));
            }
            if (status.equals("random")) {
                return cvc;
            }
            if (status.equals("empty")) {
                return "";
            }
            return null;
        }

        public static CardInfo getCardInfo (String cardStatus, String requiredLocale, String monthStatus, String
        yearStatus, String holderStatus, String cvcStatus){
            return new CardInfo(
                    getCardNumber(cardStatus, requiredLocale),
                    getMonth(monthStatus),
                    getYear(yearStatus),
                    getFullName(holderStatus),
                    getCVC(cvcStatus)
            );
        }
    }



