package ru.netology.web.test.Credit;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.*;
import ru.netology.web.page.*;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.web.data.SQLHelper.cleanData;

public class CreditCardCvcTest {
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

    @DisplayName("2.5.1 Buying tour by credit card - СVV with two digits")
    @Test
    void creditBuyingTwoDigitsCVV() {
        DashboardPage dashboardPage = new DashboardPage();
        CreditPage creditPage = dashboardPage.getCreditPayment();
        creditPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "valid",
                "short")
        );
        creditPage.checkErrorMessageCardCvcField("Неверный формат");
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("2.5.2 Buying tour by credit card - СVV with triple zero")
    @Test
    void creditBuyingTripleZeroCVV() {
        DashboardPage dashboardPage = new DashboardPage();
        CreditPage creditPage = dashboardPage.getCreditPayment();
        creditPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "valid",
                "tripleZero")
        );
        creditPage.checkSuccessNotification();
        assertEquals("APPROVED", new SQLHelper().getCreditRequestStatus());
        assertNotNull(new SQLHelper().getCreditId());
    }

}
