package data;

public class DebitCardPage {

    private Initialisation homePage = new Initialisation();
    private Form buyButton = new Form();
    private Form formInstance = new Form();
    private DataGeneration card = new DataGeneration();
    private Form continueButton = new Form();

    public DebitCardPage declinedPageFilling() {
        homePage.load();
        buyButton.pushBuyButton();
        formInstance.formFillingFull(card.declinedCard());
        continueButton.pushContinueButton();
        return new DebitCardPage();
    }

    public DebitCardPage approvedPageFilling() {
        homePage.load();
        buyButton.pushBuyButton();
        formInstance.formFillingFull(card.approvedCard());
        continueButton.pushContinueButton();
        return new DebitCardPage();
    }

}
