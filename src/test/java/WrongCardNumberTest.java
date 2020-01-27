import data.CreditCardPage;
import data.Initialisation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class WrongCardNumberTest {

    @BeforeAll
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private CreditCardPage creditCardPage;

    @Test
    void WrongCardNumber() {
        creditCardPage = new CreditCardPage();
        creditCardPage.wrongCardFilling();
    }
}
