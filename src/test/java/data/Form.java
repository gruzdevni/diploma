package data;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class Form {

    DataGeneration data = new DataGeneration();

    public void formFillingFull(String card) {
        $(byText("Номер карты")).parent().$(byCssSelector(".input__control")).setValue(card);
        $(byText("Месяц")).parent().$(byCssSelector(".input__control")).setValue(data.getMonth());
        $(byText("Год")).parent().$(byCssSelector(".input__control")).setValue(data.getYear());
        $(byText("Владелец")).parent().$(byCssSelector(".input__control")).setValue(data.getName());
        $(byText("CVC/CVV")).parent().$(byCssSelector(".input__control")).setValue(data.getCVC());
    }

    public void formFillingWithoutCVC(String card) {
        $(byText("Номер карты")).parent().$(byCssSelector(".input__control")).setValue(card);
        $(byText("Месяц")).parent().$(byCssSelector(".input__control")).setValue(data.getMonth());
        $(byText("Год")).parent().$(byCssSelector(".input__control")).setValue(data.getYear());
        $(byText("Владелец")).parent().$(byCssSelector(".input__control")).setValue(data.getName());
    }

    public void pushCreditButton() {
        $("button:last-of-type").click();
    }

    public void pushBuyButton() {
        $("button:first-of-type").click();
    }

    public void pushContinueButton() {
        $(withText("Продолжить")).click();
    }
}
