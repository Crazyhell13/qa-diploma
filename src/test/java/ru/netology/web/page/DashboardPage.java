package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private final SelenideElement paymentGate = $$(".button").first().shouldHave(Condition.exactText("Купить"));
    private final SelenideElement creditGate = $$(".button").last().shouldHave(Condition.exactText("Купить в кредит"));

    public PaymentPage getCardPayment() {
        paymentGate.click();
        return new PaymentPage();
    }
    public PaymentPage getCreditPayment(){
        creditGate.click();
        return new PaymentPage();
    }
}
