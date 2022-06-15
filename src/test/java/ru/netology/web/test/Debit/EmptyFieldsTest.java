package ru.netology.web.test.Debit;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.*;
import ru.netology.web.page.*;
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
        paymentPage.checkErrorMessageCardNumberField("Неверный формат");
        paymentPage.checkErrorMessageCardMonthField("Неверный формат");
        paymentPage.checkErrorMessageCardYearField("Неверный формат");
        paymentPage.checkErrorMessageCardHolderField("Поле обязательно для заполнения");
        paymentPage.checkErrorMessageCardCvcField("Неверный формат");
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
        paymentPage.checkErrorMessageCardNumberField("Неверный формат");
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("1.5 Buying tour by debit card - empty month field")
    @Test
    void debitBuyingEmptyMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "empty",
                "future",
                "valid",
                "random")
        );
        paymentPage.checkErrorMessageCardMonthField("Неверный формат");
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("1.6 Buying tour by debit card - empty year field")
    @Test
    void debitBuyingEmptyYear() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "empty",
                "valid",
                "random")
        );
        paymentPage.checkErrorMessageCardYearField("Неверный формат");
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("1.7 Buying tour by debit card - empty card holder name")
    @Test
    void debitBuyingEmptyCardHolder() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "empty",
                "random")
        );
        paymentPage.checkErrorMessageCardHolderField("Поле обязательно для заполнения");
        assertNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("1.8 Buying tour by debit card - empty CVV field")
    @Test
    void debitBuyingEmptyCVVField() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "valid",
                "empty")
        );
        paymentPage.checkErrorMessageCardCvcField("Неверный формат");
        assertNull(new SQLHelper().getPaymentId());
    }
}
