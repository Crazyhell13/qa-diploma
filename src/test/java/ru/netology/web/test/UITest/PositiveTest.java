package ru.netology.web.test.UITest;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.SQLHelper.cleanData;

public class PositiveTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    public void cleanTables() {
        cleanData();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @DisplayName("Buying tour by debit card - APPROVED")
    @Test
    void debitBuyingByApprovedCard() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "future",
                "future",
                "valid",
                "random"
        );
        paymentPage.checkSuccessNotification();
    }

    @DisplayName("Buying tour by debit card - APPROVED - current month and year")
    @Test
    void debitBuyingByApprovedCardCurrentMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "APPROVED",
                "en",
                "current",
                "current",
                "valid",
                "random"
        );
        paymentPage.checkSuccessNotification();
    }

    @DisplayName("Buying tour by debit card - DECLINED")
    @Test
    void debitBuyingByDeclinedCard() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "DECLINED",
                "en",
                "future",
                "future",
                "valid",
                "random"
        );
        paymentPage.checkErrorNotification();
    }
    @DisplayName("Buying tour by debit card - DECLINED - current month and year")
    @Test
    void debitBuyingByDeclinedCardCurrentMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = new PaymentPage();
        dashboardPage.getCardPayment().fillPaymentForm(
                "DECLINED",
                "en",
                "current",
                "current",
                "valid",
                "random"
        );
        paymentPage.checkErrorNotification();
    }
}

