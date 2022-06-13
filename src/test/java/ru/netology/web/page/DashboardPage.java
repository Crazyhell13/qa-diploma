package ru.netology.web.page;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement paymentGate = $(byText("Купить"));
    private SelenideElement creditGate = $(byText("Купить в кредит"));

    public PaymentPage getCardPayment() {
        paymentGate.click();
        return new PaymentPage();
    }
    public PaymentPage getCreditPayment(){
        creditGate.click();
        return new PaymentPage();
    }
}
