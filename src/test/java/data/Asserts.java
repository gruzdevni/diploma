package data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class Asserts {

    private SelenideElement SuccessNotification = $(withText("Успешно"));
    private SelenideElement ErrorNotification = $(withText("Ошибка"));
    private SelenideElement WrongFormatWarning = $(withText("Неверный формат"));


    public boolean isSuccessNotificationShown() {
        SuccessNotification.waitUntil(Condition.visible, 15000);
        return true;
    }

    public boolean isErrorNotificationShown() {
        ErrorNotification.waitUntil(Condition.visible, 15000);
        return true;
    }

    public boolean isErrorNotificationNotShown() {
        ErrorNotification.shouldNotBe(Condition.appear);
        return true;
    }

    public boolean isWrongFormatWarningShown() {
        WrongFormatWarning.shouldBe(Condition.appear);
        return true;
    }
}


