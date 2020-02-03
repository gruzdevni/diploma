package data;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DebitCardPage {
    public DebitCardPage declinedPageFilling() {
        Initialisation.loadPage();
        Form.buyButton();
        Form.formFilling(DataGeneration.declinedCard());
        Form.continueButton();
        return new DebitCardPage();
    }

    public DebitCardPage approvedPageFilling() {
        Initialisation.loadPage();
        Form.buyButton();
        Form.formFilling(DataGeneration.approvedCard());
        Form.continueButton();
        return new DebitCardPage();
    }

}
