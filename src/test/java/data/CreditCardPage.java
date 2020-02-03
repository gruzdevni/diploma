package data;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static data.Form.formFilling;

public class CreditCardPage {
    public CreditCardPage declinedPageFilling() {
        Initialisation.loadPage();
        Form.creditButton();
        Form.formFilling(DataGeneration.declinedCard());
        Form.continueButton();
        return new CreditCardPage();
    }

    public CreditCardPage approvedPageFilling() {
        Initialisation.loadPage();
        Form.creditButton();
        Form.formFilling(DataGeneration.approvedCard());
        Form.continueButton();
        return new CreditCardPage();
    }

    public CreditCardPage withoutOutCVCFilling() {
        Initialisation.loadPage();
        Form.creditButton();
        Form.formFillingWithoutCVC(DataGeneration.approvedCard());
        Form.continueButton();
        return new CreditCardPage();
    }

    public CreditCardPage wrongCardFilling() {
        Initialisation.loadPage();
        Form.creditButton();
        Form.formFilling(DataGeneration.wrongCard());
        Form.continueButton();
        return new CreditCardPage();
    }
}