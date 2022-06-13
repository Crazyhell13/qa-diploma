package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private final SelenideElement cardNumberField = $(".input [placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $(".input [placeholder='08']");
    private final SelenideElement yearField = $(".input [placeholder='22']");
    private final SelenideElement holderField = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvcField = $(".input [placeholder='999']");
    private final SelenideElement continueButton = $$(".button").find(Condition.exactText("Продолжить"));
    //уведомления
    private final SelenideElement successNotification = $(byText("Операция одобрена Банком."));
    private final SelenideElement errorNotification = $(byText("Ошибка! Банк отказал в проведении операции."));

    public void fillPaymentForm(CardInfo card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getCardMonth());
        yearField.setValue(card.getCardYear());
        holderField.setValue(card.getCardOwner());
        cvcField.setValue(card.getCVV());
        continueButton.click();
    }

    public void checkSuccessNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkErrorNotification() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}

