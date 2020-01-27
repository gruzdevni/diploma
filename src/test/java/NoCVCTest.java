import data.CreditCardPage;
import data.Initialisation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class NoCVCTest {

    @BeforeAll
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private CreditCardPage creditCardPage;

    @Test
    void NoCVC() {
        creditCardPage = new CreditCardPage();
        creditCardPage.withoutOutCVCFilling();
    }
}