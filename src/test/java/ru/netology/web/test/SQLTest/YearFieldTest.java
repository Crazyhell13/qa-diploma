package ru.netology.web.test.SQLTest;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class YearFieldTest {
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

    @DisplayName("2.3.1 Buying tour by debit card - one digit in year field")
    @Test
    void debitBuyingOneDigitYear() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "future",
                "digit",
                "validName",
                "random"
        );
        //TODO
    }

    @DisplayName("2.3.2 Buying tour by debit card - double zero in year field")
    @Test
    void debitBuyingDoubleZeroYear() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "future",
                "doubleZero",
                "validName",
                "random"
        );
        //TODO
    }

    @DisplayName("2.3.3 Buying tour by debit card - invalid date in year field - more than 5 years")
    @Test
    void debitBuyingMoreThanFiveYear() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "future",
                "moreThanFive",
                "validName",
                "random"
        );
        //TODO
    }
}
