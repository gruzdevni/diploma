package data;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DebitCardPage {
        public DebitCardPage declinedPageFilling() {
            open("http://localhost:8080");
            $("button:first-of-type").click();
            formFilling(DataGeneration.declinedCard());
            $(withText("Продолжить")).click();
            return new DebitCardPage();
        }

        public DebitCardPage approvedPageFilling() {
            open("http://localhost:8080");
            $("button:first-of-type").click();
            formFilling(DataGeneration.approvedCard());
            $(withText("Продолжить")).click();
            return new DebitCardPage();
        }

    public static void formFilling(String card) {
        $(byText("Номер карты")).parent().$(byCssSelector(".input__control")).setValue(card);
        $(byText("Месяц")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getMonth());
        $(byText("Год")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getYear());
        $(byText("Владелец")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getName());
        $(byText("CVC/CVV")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getCVC());
    }
}
