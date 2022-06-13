package ru.netology.web.test.Credit;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.CardInfo;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.netology.web.data.SQLHelper.cleanData;

public class CreditCardPositiveTest {

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

    @DisplayName("Buying tour by credit card - APPROVED")
    @Test
    void creditBuyingByApprovedCard() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "valid",
                "random")
        );
        paymentPage.checkSuccessNotification();
        assertEquals("APPROVED", new SQLHelper().getCreditRequestStatus());
        assertNotNull(new SQLHelper().getCreditId());
    }

    @DisplayName("Buying tour by credit card - APPROVED - current month and year")
    @Test
    void creditBuyingByApprovedCardCurrentMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "current",
                "current",
                "valid",
                "random")
        );
        paymentPage.checkSuccessNotification();
        assertEquals("APPROVED", new SQLHelper().getCreditRequestStatus());
        assertNotNull(new SQLHelper().getCreditId());
    }

    @DisplayName("Buying tour by credit card - DECLINED")
    @Test
    void creditBuyingByDeclinedCard() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "DECLINED",
                "future",
                "future",
                "valid",
                "random")
        );
        paymentPage.checkErrorNotification();
        assertEquals("DECLINED", new SQLHelper().getCreditRequestStatus());
        assertNotNull(new SQLHelper().getCreditId());
    }

    @DisplayName("Buying tour by credit card - DECLINED - current month and year")
    @Test
    void creditBuyingByDeclinedCardCurrentMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCreditPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "DECLINED",
                "current",
                "current",
                "valid",
                "random")
        );
        paymentPage.checkErrorNotification();
        assertEquals("DECLINED", new SQLHelper().getCreditRequestStatus());
        assertNotNull(new SQLHelper().getCreditId());
    }
}

