package ru.netology.web.test.UITest;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class CVVTest {
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

    @DisplayName("2.5.1 Buying tour by debit card - СVV with two digits")
    @Test
    void debitBuyingTwoDigitsCVV() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "future",
                "future",
                "valid",
                "short"
        );
        //TODO
    }

    @DisplayName("2.5.2 Buying tour by debit card - СVV with triple zero")
    @Test
    void debitBuyingTripleZeroCVV() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "future",
                "future",
                "valid",
                "tripleZero"
        );
        //TODO
    }

}
