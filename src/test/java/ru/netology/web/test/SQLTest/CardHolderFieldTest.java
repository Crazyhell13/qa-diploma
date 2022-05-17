package ru.netology.web.test.SQLTest;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class CardHolderFieldTest {
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

    @DisplayName("2.4.1 Buying tour by debit card - name in russian")
    @Test
    void debitBuyingRussianName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "future",
                "future",
                "russian",
                "random"
        );
        //TODO
    }

    @DisplayName("2.4.2 Buying tour by debit card - digits in name field")
    @Test
    void debitBuyingDigitsInName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "future",
                "future",
                "digits",
                "random"
        );
        //TODO
    }

    @DisplayName("2.4.3 Buying tour by debit card - special symbols in name field")
    @Test
    void debitBuyingSpecialSymbolsInName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "future",
                "future",
                "specialSymbols",
                "random"
        );
        //TODO
    }

    @DisplayName("2.4.4 Buying tour by debit card - asian in name field")
    @Test
    void debitBuyingAsianName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "future",
                "future",
                "asian",
                "random"
        );
        //TODO
    }

    @DisplayName("2.4.5 Buying tour by debit card - short name")
    @Test
    void debitBuyingShortName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "future",
                "future",
                "short",
                "random"
        );
        //TODO
    }

    @DisplayName("2.4.6 Buying tour by debit card - long name")
    @Test
    void debitBuyingLongName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "future",
                "future",
                "long",
                "random"
        );
        //TODO
    }

    @DisplayName("2.4.7 Buying tour by debit card - name with only space")
    @Test
    void debitBuyingNameWithOnlySpace() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "future",
                "future",
                "space",
                "random"
        );
        //TODO
    }
}
