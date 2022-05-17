package ru.netology.web.test.SQLTest;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class MonthFieldTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @DisplayName("2.2.1 Buying tour by debit card - zero's in months field")
    @Test
    void debitBuyingZeroMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "doubleZero",
                "future",
                "validName",
                "random"
        );
        //TODO
    }

    @DisplayName("2.2.2 Buying tour by debit card - invalid month's number in months field")
    @Test
    void debitBuyingInvalidMonthNumber() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "badRandom",
                "future",
                "validName",
                "random"
        );
        //TODO
    }

    @DisplayName("2.2.3 Buying tour by debit card - one digit in months field")
    @Test
    void debitBuyingOneDigitMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "digit",
                "future",
                "validName",
                "random"
        );
        //TODO
    }

    @DisplayName("2.2.4 Buying tour by debit card - invalid card - past month")
    @Test
    void debitBuyingPastMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "past",
                "future",
                "validName",
                "random"
        );
        //TODO
    }

}
