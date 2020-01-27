package data;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreditCardPage {
    public CreditCardPage declinedPageFilling() {
        open("http://localhost:8080");
        $("button:last-of-type").click();
        $(byText("Номер карты")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.declinedCard());
        $(byText("Месяц")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getMonth());
        $(byText("Год")).parent().$(byCssSelector(".input__control")).setValue("21");
        $(byText("Владелец")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getName());
        $(byText("CVC/CVV")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getCVC());
        $(withText("Продолжить")).click();
        $(withText("Ошибка")).waitUntil(Condition.visible, 15000);
        return new CreditCardPage();
    }

    public CreditCardPage approvedPageFilling() {
        open("http://localhost:8080");
        $("button:last-of-type").click();
        $(byText("Номер карты")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.approvedCard());
        $(byText("Месяц")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getMonth());
        $(byText("Год")).parent().$(byCssSelector(".input__control")).setValue("21");
        $(byText("Владелец")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getName());
        $(byText("CVC/CVV")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getCVC());
        $(withText("Продолжить")).click();
        $(withText("Успешно")).waitUntil(Condition.visible, 15000);
        $(withText("Ошибка")).shouldNotBe(Condition.appear);
        return new CreditCardPage();
    }

    public CreditCardPage withoutOutCVCFilling() {
        open("http://localhost:8080");
        $("button:last-of-type").click();
        $(byText("Номер карты")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.approvedCard());
        $(byText("Месяц")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getMonth());
        $(byText("Год")).parent().$(byCssSelector(".input__control")).setValue("21");
        $(byText("Владелец")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getName());
        $(withText("Продолжить")).click();
        $(withText("Продолжить")).click();
        $(withText("Неверный формат")).shouldBe(Condition.appear);
        return new CreditCardPage();
    }

    public CreditCardPage wrongCardFilling() {
        open("http://localhost:8080");
        $("button:last-of-type").click();
        $(byText("Номер карты")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.wrongCard());
        $(byText("Месяц")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getMonth());
        $(byText("Год")).parent().$(byCssSelector(".input__control")).setValue("21");
        $(byText("Владелец")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getName());
        $(byText("CVC/CVV")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getCVC());
        $(withText("Продолжить")).click();
        $(withText("Неверный формат")).shouldBe(Condition.appear);
        return new CreditCardPage();
    }
}