package data;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DebitCardPage {
        public DebitCardPage declinedPageFilling() {
            open("http://localhost:8080");
            $("button:first-of-type").click();
            Form.formFilling(DataGeneration.declinedCard());
            $(withText("Продолжить")).click();
            return new DebitCardPage();
        }

        public DebitCardPage approvedPageFilling() {
            open("http://localhost:8080");
            $("button:first-of-type").click();
            Form.formFilling(DataGeneration.approvedCard());
            $(withText("Продолжить")).click();
            return new DebitCardPage();
        }

}
