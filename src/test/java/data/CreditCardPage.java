package data;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static data.DebitCardPage.formFilling;

public class CreditCardPage {
    public CreditCardPage declinedPageFilling() {
        open("http://localhost:8080");
        $("button:last-of-type").click();
        formFilling(DataGeneration.declinedCard());
        $(withText("Продолжить")).click();
        return new CreditCardPage();
    }

    public CreditCardPage approvedPageFilling() {
        open("http://localhost:8080");
        $("button:last-of-type").click();
        formFilling(DataGeneration.approvedCard());
        $(withText("Продолжить")).click();
        return new CreditCardPage();
    }

    public CreditCardPage withoutOutCVCFilling() {
        open("http://localhost:8080");
        $("button:last-of-type").click();
        formFilling(DataGeneration.approvedCard());
        $(byText("CVC/CVV")).parent().$(byCssSelector(".input__control")).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $(withText("Продолжить")).click();
        return new CreditCardPage();
    }

    public CreditCardPage wrongCardFilling() {
        open("http://localhost:8080");
        $("button:last-of-type").click();
        formFilling(DataGeneration.wrongCard());
        $(withText("Продолжить")).click();
        return new CreditCardPage();
    }
}