package ru.netology.web.test.Debit;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.*;
import ru.netology.web.page.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.web.data.SQLHelper.cleanData;

public class CvcTest {
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


    @DisplayName("2.5.1 Buying tour by debit card - СVV with two digits")
    @Test
    void debitBuyingTwoDigitsCVV() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "valid",
                "short")
        );
       paymentPage.checkErrorMessageCardCvcField("Неверный формат");
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.5.2 Buying tour by debit card - СVV with triple zero")
    @Test
    void debitBuyingTripleZeroCVV() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "valid",
                "tripleZero")
        );
        paymentPage.checkSuccessNotification();
        assertEquals("APPROVED", new SQLHelper().getPaymentStatus());
        assertEquals(4500000, new SQLHelper().getPaymentAmount());
        assertNotNull(new SQLHelper().getPaymentId());
    }
}
