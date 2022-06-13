package ru.netology.web.test.Debit;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.*;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.web.data.SQLHelper.cleanData;


public class CardHolderFieldTest {
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

    private final SelenideElement messageCardHolderField = $$(".input__top").find(text("Владелец")).parent().$(".input__sub");

    @DisplayName("2.4.1 Buying tour by debit card - name in russian")
    @Test
    void debitBuyingRussianName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "russian",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.4.2 Buying tour by debit card - digits in name field")
    @Test
    void debitBuyingDigitsInName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "digits",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.4.3 Buying tour by debit card - special symbols in name field")
    @Test
    void debitBuyingSpecialSymbolsInName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "specialSymbols",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.4.4 Buying tour by debit card - asian in name field")
    @Test
    void debitBuyingAsianName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "asian",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.4.5 Buying tour by debit card - short name")
    @Test
    void debitBuyingShortName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "short",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.4.6 Buying tour by debit card - long name")
    @Test
    void debitBuyingLongName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "long",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.4.7 Buying tour by debit card - name with only space")
    @Test
    void debitBuyingNameWithOnlySpace() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "space",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Поле обязательно для заполнения"));
        assertNull(new SQLHelper().getPaymentId());
    }
}
