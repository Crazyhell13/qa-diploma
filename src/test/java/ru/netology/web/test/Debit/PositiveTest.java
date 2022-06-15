package ru.netology.web.test.Debit;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.*;
import ru.netology.web.page.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.web.data.SQLHelper.*;

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
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "future",
                "future",
                "valid",
                "random")
        );
        paymentPage.checkSuccessNotification();
        assertEquals("APPROVED", new SQLHelper().getPaymentStatus());
        assertEquals(4500000, new SQLHelper().getPaymentAmount());
        assertNotNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("Buying tour by debit card - APPROVED - current month and year")
    @Test
    void debitBuyingByApprovedCardCurrentMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "APPROVED",
                "current",
                "current",
                "valid",
                "random")
        );
        paymentPage.checkSuccessNotification();
        assertEquals("APPROVED", new SQLHelper().getPaymentStatus());
        assertEquals(4500000, new SQLHelper().getPaymentAmount());
        assertNotNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("Buying tour by debit card - DECLINED")
    @Test
    void debitBuyingByDeclinedCard() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "DECLINED",
                "future",
                "future",
                "valid",
                "random")
        );
        paymentPage.checkErrorNotification();
        assertEquals("DECLINED", new SQLHelper().getPaymentStatus());
        assertNotNull(new SQLHelper().getPaymentId());
    }

    @DisplayName("Buying tour by debit card - DECLINED - current month and year")
    @Test
    void debitBuyingByDeclinedCardCurrentMonth() {
        DashboardPage dashboardPage = new DashboardPage();
        PaymentPage paymentPage = dashboardPage.getCardPayment();
        paymentPage.fillPaymentForm(CardInfo.generateCardInfo(
                "DECLINED",
                "current",
                "current",
                "valid",
                "random")
        );
        paymentPage.checkErrorNotification();
        assertEquals("DECLINED", new SQLHelper().getPaymentStatus());
        assertNotNull(new SQLHelper().getPaymentId());
    }
}

