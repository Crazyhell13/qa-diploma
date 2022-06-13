package ru.netology.web.data;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ofPattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardInfo {
    private String cardNumber;
    private String cardMonth;
    private String cardYear;
    private String cardOwner;
    private String CVV;

    public static CardInfo generateCardInfo(String cardStatus, String monthStatus, String
            yearStatus, String holderStatus, String cvcStatus) {
        return new CardInfo(
                getCardNumber(cardStatus),
                getMonth(monthStatus),
                getYear(yearStatus),
                getFullName(holderStatus),
                getCVC(cvcStatus)
        );

    }

    private static String getCardNumber(String cardStatus) {
        String result = new String();
        String digit = new Faker().number().digits(1);
        String random = new Faker().finance().creditCard(CreditCardType.MASTERCARD);
        switch (cardStatus) {
            case "APPROVED":
                result = "4444 4444 4444 4441";
                break;
            case "DECLINED":
                result = "4444 4444 4444 4442";
                break;
            case "short":
                result = "4444 4444 4444 444";
                break;
            case "digit":
                result = String.valueOf(new Faker().number().digits(1));
                break;
            case "random":
                result = String.valueOf(new Faker().finance().creditCard(CreditCardType.MASTERCARD));
                break;
            case "empty":
                result = "";
                break;
            case "allZero":
                result = "0000 0000 0000 0000";
                break;
            default:
                break;
        }
        return result;
    }

    //месяц
    private static String getMonth(String monthStatus) {
        String result = new String();
        switch (monthStatus) {
            case "digit":
                result = String.valueOf(new Faker().number().digits(1));
                break;
            case "doubleZero":
                result = "00";
                break;
            case "badRandom":
                result = String.valueOf(new Faker().number().numberBetween(13, 99));
                break;
            case "current":
                result = LocalDate.now().format(ofPattern("MM"));
                break;
            case "past":
                result = LocalDate.now().minusMonths(1).format(ofPattern("MM"));
                break;
            case "future":
                result = LocalDate.now().plusMonths(1).format(ofPattern("MM"));
                break;
            case "empty":
                result = "";
                break;
            default:
                break;
        }
        return result;
    }

    //год
    private static String getYear(String yearStatus) {
        String result = new String();
        switch (yearStatus) {
            case "digit":
                result = String.valueOf(new Faker().number().digits(1));
                break;
            case "doubleZero":
                result = "00";
                break;
            case "moreThanFive":
                result = LocalDate.now().plusYears(6).format(ofPattern("yy"));
                break;
            case "current":
                result = LocalDate.now().format(ofPattern("yy"));
                break;
            case "past":
                result = LocalDate.now().minusYears(new Faker().number().numberBetween(1, 20)).format(ofPattern("yy"));
                break;
            case "future":
                result = LocalDate.now().plusYears(new Faker().number().numberBetween(1, 5)).format(ofPattern("yy"));
                break;
            case "empty":
                result = "";
                break;
            default:
                break;
        }
        return result;
    }


    //holderName
    private static String getFullName(String holderStatus) {
        String result = new String();
        switch (holderStatus) {
            case "valid":
                result = String.valueOf(new Faker().name().firstName() + " " + new Faker().name().lastName());
                break;
            case "russian":
                result = String.valueOf(new Faker(new Locale("ru")).name().fullName());
                break;
            case "digits":
                result = String.valueOf(new Faker().name().firstName() + "" + new Faker().number().numberBetween(1, 999));
                break;
            case "specialSymbols":
                result = "+_.,/~'@\"-=?&%^()$#№;*:!";
                break;
            case "asian":
                result = String.valueOf(new Faker(new Locale("ch")).name().fullName());
                break;
            case "short":
                result = "Y";
                break;
            //будем считать, что максимально 27
            case "long":
                result = "YYYYYYYYYYYYYYYYYYYY YYYYYYYY";
                break;
            case "space":
                result = "   ";
                break;
            case "empty":
                result = "";
                break;
            default:
                break;
        }
        return result;
    }

    //CVV
    private static String getCVC(String cvcStatus) {
        String result = new String();
        switch (cvcStatus) {
            case "tripleZero":
                result = "000";
                break;
            case "short":
                result = String.valueOf(new Faker().number().numberBetween(0, 99));
                break;
            case "random":
                result = String.valueOf(new Faker().number().digits(3));
                break;
            case "empty":
                result = "";
                break;
            default:
                break;
        }
        return result;
    }
}