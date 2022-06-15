package ru.netology.web.test.Credit;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.CardInfo;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.CreditPage;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.web.data.SQLHelper.cleanData;

public class CreditCardEmptyFieldsTest {
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


    @DisplayName("1.3 Buying tour by credit card - empty fields")
    @Test
    void creditBuyingEmptyFields() {
        DashboardPage dashboardPage = new DashboardPage();
        CreditPage creditPage = dashboardPage.getCreditPayment();
        creditPage.fillPaymentForm(CardInfo.generateCardInfo(
                "empty",
                "empty",
                "empty",
                "empty",
                "empty")
        );
        creditPage.checkErrorMessageCardNumberField("Неверный формат");
       creditPage.checkErrorMessageCardMonthField("Неверный формат");
        creditPage.checkErrorMessageCardYearField("Неверный формат");
        creditPage.checkErrorMessageCardHolderField("Поле обязательно для заполнения");
        creditPage.checkErrorMessageCardCvcField("Неверный формат");
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("1.4 Buying tour by credit card - empty card number")
    @Test
    void creditBuyingEmptyCardNumber() {
        DashboardPage dashboardPage = new DashboardPage();
        CreditPage creditPage = dashboardPage.getCreditPayment();
        creditPage.fillPaymentForm(CardInfo.generateCardInfo(
                "empty",
                "future",
                "future",
                "valid",
                "random")
        );
        creditPage.checkErrorMessageCardNumberField("Неверный формат");
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("1.5 Buying tour by credit card - empty month field")
    @Test
    void creditBuyingEmptyMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        CreditPage creditPage = dashboardPage.getCreditPayment();
        creditPage.fillPaymentForm(CardInfo.generateCardInfo(
                "ACTIVE",
                "empty",
                "future",
                "valid",
                "random")
        );
        creditPage.checkErrorMessageCardMonthField("Неверный формат");
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("1.6 Buying tour by credit card - empty year field")
    @Test
    void creditBuyingEmptyYear() {
        DashboardPage dashboardPage = new DashboardPage();
        CreditPage creditPage = dashboardPage.getCreditPayment();
        creditPage.fillPaymentForm(CardInfo.generateCardInfo(
                "ACTIVE",
                "future",
                "empty",
                "valid",
                "random")
        );
        creditPage.checkErrorMessageCardYearField("Неверный формат");
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("1.7 Buying tour by credit card - empty card holder name")
    @Test
    void creditBuyingEmptyCardHolder() {
        DashboardPage dashboardPage = new DashboardPage();
        CreditPage creditPage = dashboardPage.getCreditPayment();
        creditPage.fillPaymentForm(CardInfo.generateCardInfo(
                "ACTIVE",
                "future",
                "future",
                "empty",
                "random")
        );
        creditPage.checkErrorMessageCardHolderField("Поле обязательно для заполнения");
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("1.8 Buying tour by credit card - empty CVV field")
    @Test
    void creditBuyingEmptyCVVField() {
        DashboardPage dashboardPage = new DashboardPage();
        CreditPage creditPage = dashboardPage.getCreditPayment();
        creditPage.fillPaymentForm(CardInfo.generateCardInfo(
                "ACTIVE",
                "future",
                "future",
                "valid",
                "empty")
        );
        creditPage.checkErrorMessageCardCvcField("Неверный формат");
        assertNull(new SQLHelper().getCreditId());
    }
}
