package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder = '08']");
    private final SelenideElement yearField = $("[placeholder = '22']");
    private final SelenideElement holderField = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvcField = $("[placeholder='999']");
    private final SelenideElement continueButton = $$(".button").find(Condition.exactText("Продолжить"));
    //уведомления
    private final SelenideElement successNotification = $(byText("Операция одобрена Банком."));
    private final SelenideElement errorNotification = $(byText("Ошибка! Банк отказал в проведении операции."));
    //сообщения об ошибках
    private final SelenideElement wrongFormat = $(byText("Неверный формат"));
    private final SelenideElement invalidCardExpirationDate = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpired = $(byText("Истёк срок действия карты"));
    private final SelenideElement emptyField = $(byText("Поле обязательно для заполнения"));

    public void fillPaymentForm(String cardStatus, String requiredLocale, String monthStatus, String yearStatus, String holderStatus, String cvcStatus) {
        cardNumberField.setValue(DataHelper.getCardInfo(cardStatus, requiredLocale, monthStatus, yearStatus, holderStatus, cvcStatus)
                .getCardNumber());
        monthField.setValue(DataHelper.getCardInfo(cardStatus, requiredLocale, monthStatus, yearStatus, holderStatus, cvcStatus)
                .getMonth());
        yearField.setValue(DataHelper.getCardInfo(cardStatus, requiredLocale, monthStatus, yearStatus, holderStatus, cvcStatus)
                .getYear());
        holderField.setValue(DataHelper.getCardInfo(cardStatus, requiredLocale, monthStatus, yearStatus, holderStatus, cvcStatus)
                .getCardHolder());
        cvcField.setValue(DataHelper.getCardInfo(cardStatus, requiredLocale, monthStatus, yearStatus, holderStatus, cvcStatus)
                .getCvc());
        continueButton.click();
    }

    public void checkSuccessNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkErrorNotification() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkWrongFormat() {
        wrongFormat.shouldBe(Condition.visible);
    }

    public void checkInvalidCardExpirationDate() {
        invalidCardExpirationDate.shouldBe(Condition.visible);
    }

    public void verifyCardExpired() {
        cardExpired.shouldBe(Condition.visible);
    }

    public void verifyEmptyField() {
        emptyField.shouldBe(Condition.visible);
    }

}
