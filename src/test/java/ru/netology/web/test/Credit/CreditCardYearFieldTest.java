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

public class CreditCardYearFieldTest {
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

    @DisplayName("2.3.1 Buying tour by credit card - one digit in year field")
    @Test
    void creditBuyingOneDigitYear() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "digit",
                "valid",
                "random")
        );
        messageCardYearField.shouldHave(exactText("Неверный формат"));
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("2.3.2 Buying tour by credit card - double zero in year field")
    @Test
    void creditBuyingDoubleZeroYear() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "doubleZero",
                "valid",
                "random")
        );
        messageCardYearField.shouldHave(exactText("Истёк срок действия карты"));
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("2.3.3 Buying tour by credit card - invalid date in year field - more than 5 years")
    @Test
    void creditBuyingMoreThanFiveYear() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "moreThanFive",
                "valid",
                "random")
        );
        messageCardYearField.shouldHave(exactText("Неверно указан срок действия карты"));
        assertNull(new SQLHelper().getCreditId());
    }
}
