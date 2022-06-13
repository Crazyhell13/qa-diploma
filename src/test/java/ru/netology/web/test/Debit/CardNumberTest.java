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

public class CardNumberTest {
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

    private final SelenideElement messageCardNumberField = $$(".input__top").find(text("Номер карты")).parent().$(".input__sub");

    @DisplayName("2.1.1 Buying tour by debit card - one digit in card number field")
    @Test
    void debitBuyingOneDigitNumber() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "digit",
                "future",
                "future",
                "valid",
                "random")
        );
        messageCardNumberField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.1.2 Buying tour by debit card - short number in card number field")
    @Test
    void debitBuyingShortNumber() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "short",
                "future",
                "future",
                "valid",
                "random")
        );
        messageCardNumberField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.1.3 Buying tour by debit card - card from another bank in card number field")
    @Test
    void debitBuyingAnotherBankCard() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "random",
                "future",
                "future",
                "valid",
                "random")
        );
        messageCardNumberField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.1.4 Buying tour by debit card - all zero's in card number field")
    @Test
    void debitBuyingZero() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "allZero",
                "future",
                "future",
                "valid",
                "random")
        );
        messageCardNumberField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }
}
