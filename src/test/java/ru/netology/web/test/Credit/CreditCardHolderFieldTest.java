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
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.web.data.SQLHelper.cleanData;


public class CreditCardHolderFieldTest {
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

    @DisplayName("2.4.1 Buying tour by credit card - name in russian")
    @Test
    void creditBuyingRussianName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "russian",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("2.4.2 Buying tour by credit card - digits in name field")
    @Test
    void creditBuyingDigitsInName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "digits",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("2.4.3 Buying tour by credit card - special symbols in name field")
    @Test
    void creditBuyingSpecialSymbolsInName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "specialSymbols",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("2.4.4 Buying tour by credit card - asian in name field")
    @Test
    void creditBuyingAsianName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "asian",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("2.4.5 Buying tour by credit card - short name")
    @Test
    void creditBuyingShortName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "short",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("2.4.6 Buying tour by credit card - long name")
    @Test
    void creditBuyingLongName() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "long",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("2.4.7 Buying tour by credit card - name with only space")
    @Test
    void creditBuyingNameWithOnlySpace() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "space",
                "random")
        );
        messageCardHolderField.shouldHave(exactText("Поле обязательно для заполнения"));
        assertNull(new SQLHelper().getCreditId());
    }
}
