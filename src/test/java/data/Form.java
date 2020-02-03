package data;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class Form {
    public void formFillingFull(String card) {
        $(byText("Номер карты")).parent().$(byCssSelector(".input__control")).setValue(card);
        $(byText("Месяц")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getMonth());
        $(byText("Год")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getYear());
        $(byText("Владелец")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getName());
        $(byText("CVC/CVV")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getCVC());
    }

    public void formFillingWithoutCVC(String card) {
        $(byText("Номер карты")).parent().$(byCssSelector(".input__control")).setValue(card);
        $(byText("Месяц")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getMonth());
        $(byText("Год")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getYear());
        $(byText("Владелец")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getName());
    }

    public void creditButton() {
        $("button:last-of-type").click();
    }

    public void buyButton() {
        $("button:first-of-type").click();
    }

    public void continueButton() {
        $(withText("Продолжить")).click();
    }
}
