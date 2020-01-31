package data;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Form {
    public static void formFilling(String card) {
        $(byText("Номер карты")).parent().$(byCssSelector(".input__control")).setValue(card);
        $(byText("Месяц")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getMonth());
        $(byText("Год")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getYear());
        $(byText("Владелец")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getName());
        $(byText("CVC/CVV")).parent().$(byCssSelector(".input__control")).setValue(DataGeneration.getCVC());
    }
}
