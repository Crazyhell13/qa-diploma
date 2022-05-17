package ru.netology.web.test.SQLTest;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositiveTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
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
    //TODO
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
  //TODO
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
        //TODO
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
        //TODO
    }
}
