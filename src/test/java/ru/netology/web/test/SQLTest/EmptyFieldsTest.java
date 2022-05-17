package ru.netology.web.test.SQLTest;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class EmptyFieldsTest {
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

    @DisplayName("1.3 Buying tour by debit card - empty fields")
    @Test
    void debitBuyingEmptyFields() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "empty",
                "empty",
                "empty",
                "empty",
                "empty",
                "empty"
        );
        //TODO
    }

    @DisplayName("1.4 Buying tour by debit card - empty card number")
    @Test
    void debitBuyingEmptyCardNumber() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "empty",
                "en",
                "future",
                "future",
                "validName",
                "random"
        );
        //TODO
    }

    @DisplayName("1.5 Buying tour by debit card - empty month field")
    @Test
    void debitBuyingEmptyMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "ACTIVE",
                "en",
                "empty",
                "future",
                "validName",
                "random"
        );
        //TODO
    }

    @DisplayName("1.6 Buying tour by debit card - empty year field")
    @Test
    void debitBuyingEmptyYear() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "ACTIVE",
                "en",
                "future",
                "empty",
                "validName",
                "random"
        );
        //TODO
    }

    @DisplayName("1.7 Buying tour by debit card - empty card holder name")
    @Test
    void debitBuyingEmptyCardHolder() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "ACTIVE",
                "en",
                "future",
                "future",
                "empty",
                "random"
        );
        //TODO
    }

    @DisplayName("1.8 Buying tour by debit card - empty CVV field")
    @Test
    void debitBuyingEmptyCVVField() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "ACTIVE",
                "en",
                "future",
                "future",
                "validName",
                "empty"
        );
        //TODO
    }
}
