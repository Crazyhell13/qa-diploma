package ru.netology.web.test.SQLTest;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class CardNumberTest {
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

    @DisplayName("2.1.1 Buying tour by debit card - one digit in card number field")
    @Test
    void debitBuyingOneDigitNumber() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "digit",
                "en",
                "future",
                "future",
                "validName",
                "random"
        );
        //TODO
    }

    @DisplayName("2.1.2 Buying tour by debit card - short number in card number field")
    @Test
    void debitBuyingShortNumber() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "short",
                "en",
                "future",
                "future",
                "validName",
                "random"
        );
        //TODO
    }

    @DisplayName("2.1.3 Buying tour by debit card - card from another bank in card number field")
    @Test
    void debitBuyingAnotherBankCard() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "random",
                "en",
                "future",
                "future",
                "validName",
                "random"
        );
        //TODO
    }

    @DisplayName("2.1.4 Buying tour by debit card - all zero's in card number field")
    @Test
    void debitBuyingZero() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "allZero",
                "en",
                "future",
                "future",
                "validName",
                "random"
        );
        //TODO
    }
}
