package data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class Asserts {

    private static SelenideElement SuccessNotification = $(withText("Успешно"));
    private static SelenideElement ErrorNotification = $(withText("Ошибка"));
    private static SelenideElement WrongFormatWarning = $(withText("Неверный формат"));


    public static boolean isSuccessNotificationShown() {
        SuccessNotification.waitUntil(Condition.visible, 15000);
        return true;
    }

    public static boolean isErrorNotificationShown() {
        ErrorNotification.waitUntil(Condition.visible, 15000);
        return true;
    }

    public static boolean isErrorNotificationNotShown() {
        ErrorNotification.shouldNotBe(Condition.appear);
        return true;
    }

    public static boolean isWrongFormatWarningShown() {
        WrongFormatWarning.shouldBe(Condition.appear);
        return true;
    }
}


