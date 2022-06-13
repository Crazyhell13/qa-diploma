package ru.netology.web.test.Debit;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.*;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.web.data.SQLHelper.cleanData;
import static org.junit.jupiter.api.Assertions.*;

public class YearFieldTest {
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

    private final SelenideElement messageCardYearField = $$(".input__top").find(text("Год")).parent().$(".input__sub");

    @DisplayName("2.3.1 Buying tour by debit card - one digit in year field")
    @Test
    void debitBuyingOneDigitYear() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "digit",
                "valid",
                "random")
        );
        messageCardYearField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.3.2 Buying tour by debit card - double zero in year field")
    @Test
    void debitBuyingDoubleZeroYear() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "doubleZero",
                "valid",
                "random")
        );
        messageCardYearField.shouldHave(exactText("Истёк срок действия карты"));
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("2.3.3 Buying tour by debit card - invalid date in year field - more than 5 years")
    @Test
    void debitBuyingMoreThanFiveYear() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "moreThanFive",
                "valid",
                "random")
        );
        messageCardYearField.shouldHave(exactText("Неверно указан срок действия карты"));
        assertNull(new SQLHelper().getPaymentId());
    }
}
