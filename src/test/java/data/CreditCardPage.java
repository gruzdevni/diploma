package data;

public class CreditCardPage {

    private Initialisation homePage = new Initialisation();
    private Form creditButton = new Form();
    private Form formInstance = new Form();
    private DataGeneration card = new DataGeneration();
    private Form continueButton = new Form();

    public CreditCardPage declinedPageFilling() {
        homePage.load();
        creditButton.pushCreditButton();
        formInstance.formFillingFull(card.declinedCard());
        continueButton.pushContinueButton();
        return new CreditCardPage();
    }

    public CreditCardPage approvedPageFilling() {
        homePage.load();
        creditButton.pushCreditButton();
        formInstance.formFillingFull(card.approvedCard());
        continueButton.pushContinueButton();
        return new CreditCardPage();
    }

    public CreditCardPage withoutOutCVCFilling() {
        homePage.load();
        creditButton.pushCreditButton();
        formInstance.formFillingWithoutCVC(card.approvedCard());
        continueButton.pushContinueButton();
        return new CreditCardPage();
    }

    public CreditCardPage wrongCardFilling() {
        homePage.load();
        creditButton.pushCreditButton();
        formInstance.formFillingFull(card.wrongCard());
        continueButton.pushContinueButton();
        return new CreditCardPage();
    }
}