package ru.netology.web.test.Debit;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.CardInfo;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.web.data.SQLHelper.cleanData;

public class MonthFieldTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @AfterEach
    public void cleanTables() {
        cleanData();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    private final SelenideElement messageCardMonthField = $$(".input__top").find(text("Месяц")).parent().$(".input__sub");

    @DisplayName("2.2.1 Buying tour by debit card - zero's in months field")
    @Test
    void debitBuyingZeroMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "doubleZero",
                "future",
                "validName",
                "random")
        );
        messageCardMonthField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.2.2 Buying tour by debit card - invalid month's number in months field")
    @Test
    void debitBuyingInvalidMonthNumber() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "badRandom",
                "future",
                "valid",
                "random")
        );
        messageCardMonthField.shouldHave(exactText("Неверно указан срок действия карты"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.2.3 Buying tour by debit card - one digit in months field")
    @Test
    void debitBuyingOneDigitMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "digit",
                "future",
                "valid",
                "random")
        );
        messageCardMonthField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.2.4 Buying tour by debit card - invalid card - past month")
    @Test
    void debitBuyingPastMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "past",
                "current",
                "valid",
                "random")
        );
        messageCardMonthField.shouldHave(exactText("Истёк срок действия карты"));
        assertNull(new SQLHelper().getPaymentId());
    }

}
