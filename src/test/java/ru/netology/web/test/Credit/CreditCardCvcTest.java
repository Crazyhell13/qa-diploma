package ru.netology.web.test.Credit;

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

    private final SelenideElement messageCvcField = $$(".input__top").find(text("CVC/CVV")).parent().$(".input__sub");

    @DisplayName("2.5.1 Buying tour by credit card - СVV with two digits")
    @Test
    void creditBuyingTwoDigitsCVV() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "valid",
                "short")
        );
        messageCvcField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("2.5.2 Buying tour by credit card - СVV with triple zero")
    @Test
    void creditBuyingTripleZeroCVV() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "valid",
                "tripleZero")
        );
        paymentPage.checkSuccessNotification();
        assertEquals("APPROVED", new SQLHelper().getCreditRequestStatus());
        assertNotNull(new SQLHelper().getCreditId());
    }

}
