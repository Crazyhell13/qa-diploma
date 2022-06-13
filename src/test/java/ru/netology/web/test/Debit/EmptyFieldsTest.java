package ru.netology.web.test.Debit;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.*;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.web.data.SQLHelper.cleanData;

public class EmptyFieldsTest {
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

    private final SelenideElement messageCardNumberField = $$(".input__top").find(text("Номер карты")).parent();
    private final SelenideElement messageCardMonthField = $$(".input__top").find(text("Месяц")).parent();
    private final SelenideElement messageCardYearField = $$(".input__top").find(text("Год")).parent();
    private final SelenideElement messageCardHolderField = $$(".input__top").find(text("Владелец")).parent();
    private final SelenideElement messageCvcField = $$(".input__top").find(text("CVC/CVV")).parent();

    @DisplayName("1.3 Buying tour by debit card - empty fields")
    @Test
    void debitBuyingEmptyFields() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "empty",
                "empty",
                "empty",
                "empty",
                "empty")
        );
        messageCardNumberField.shouldHave(Condition.exactText("Неверный формат"));
        messageCardMonthField.shouldHave(Condition.exactText("Неверный формат"));
        messageCardYearField.shouldHave(Condition.exactText("Неверный формат"));
        messageCardHolderField.shouldHave(Condition.exactText("Поле обязательно для заполнения"));
        messageCvcField.shouldHave(Condition.exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("1.4 Buying tour by debit card - empty card number")
    @Test
    void debitBuyingEmptyCardNumber() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "empty",
                "future",
                "future",
                "valid",
                "random")
        );
        messageCardNumberField.shouldHave(Condition.exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("1.5 Buying tour by debit card - empty month field")
    @Test
    void debitBuyingEmptyMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "ACTIVE",
                "empty",
                "future",
                "valid",
                "random")
        );
        messageCardMonthField.shouldHave(Condition.exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("1.6 Buying tour by debit card - empty year field")
    @Test
    void debitBuyingEmptyYear() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "ACTIVE",
                "future",
                "empty",
                "valid",
                "random")
        );
        messageCardYearField.shouldHave(Condition.exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("1.7 Buying tour by debit card - empty card holder name")
    @Test
    void debitBuyingEmptyCardHolder() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "ACTIVE",
                "future",
                "future",
                "empty",
                "random")
        );
        messageCardHolderField.shouldHave(Condition.exactText("Поле обязательно для заполнения"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("1.8 Buying tour by debit card - empty CVV field")
    @Test
    void debitBuyingEmptyCVVField() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "ACTIVE",
                "future",
                "future",
                "valid",
                "empty")
        );
        messageCvcField.shouldHave(Condition.exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }
}
