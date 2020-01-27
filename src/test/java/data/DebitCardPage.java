package data;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DebitCardPage {
        public DebitCardPage declinedPageFilling() {
            open("http://localhost:8080");
            $("button:first-of-type").click();
            $(byText("Номер карты")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.declinedCard());
            $(byText("Месяц")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getMonth());
            $(byText("Год")).parent().$(byCssSelector(".input__control")).setValue("21");
            $(byText("Владелец")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getName());
            $(byText("CVC/CVV")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getCVC());
            $(withText("Продолжить")).click();
            $(withText("Ошибка")).waitUntil(Condition.visible, 15000);
            return new DebitCardPage();
        }

        public DebitCardPage approvedPageFilling() {
            open("http://localhost:8080");
            $("button:first-of-type").click();
            $(byText("Номер карты")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.approvedCard());
            $(byText("Месяц")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getMonth());
            $(byText("Год")).parent().$(byCssSelector(".input__control")).setValue("21");
            $(byText("Владелец")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getName());
            $(byText("CVC/CVV")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getCVC());
            $(withText("Продолжить")).click();
            $(withText("Успешно")).waitUntil(Condition.visible, 15000);
            $(withText("Ошибка")).shouldNotBe(Condition.appear);
            return new DebitCardPage();
        }
}
