package ru.netology.web.test.Credit;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.*;
import ru.netology.web.page.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.web.data.SQLHelper.cleanData;

public class CreditCardMonthFieldTest {

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

    @DisplayName("2.2.1 Buying tour by credit card - zero's in months field")
    @Test
    void creditBuyingZeroMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        CreditPage creditPage = dashboardPage.getCreditPayment();
        creditPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "doubleZero",
                "future",
                "valid",
                "random")
        );
        creditPage.checkErrorMessageCardMonthField("Неверный формат");
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("2.2.2 Buying tour by credit card - invalid month's number in months field")
    @Test
    void creditBuyingInvalidMonthNumber() {
        DashboardPage dashboardPage = new DashboardPage();
        CreditPage creditPage = dashboardPage.getCreditPayment();
        creditPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "badRandom",
                "future",
                "valid",
                "random")
        );
        creditPage.checkErrorMessageCardMonthField("Неверно указан срок действия карты");
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("2.2.3 Buying tour by credit card - one digit in months field")
    @Test
    void creditBuyingOneDigitMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        CreditPage creditPage = dashboardPage.getCreditPayment();
        creditPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "digit",
                "future",
                "valid",
                "random")
        );
        creditPage.checkErrorMessageCardMonthField("Неверный формат");
        assertNull(new SQLHelper().getCreditId());
    }

    @DisplayName("2.2.4 Buying tour by credit card - invalid card - past month")
    @Test
    void creditBuyingPastMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        CreditPage creditPage = dashboardPage.getCreditPayment();
        creditPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "past",
                "current",
                "valid",
                "random")
        );
        creditPage.checkErrorMessageCardMonthField("Истёк срок действия карты");
        assertNull(new SQLHelper().getCreditId());
    }
}
