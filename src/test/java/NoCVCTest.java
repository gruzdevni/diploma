import data.Asserts;
import data.CreditCardPage;
import data.Initialisation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class NoCVCTest {

    @BeforeAll
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private CreditCardPage creditCardPage = new CreditCardPage();
    private Asserts assertInstance = new Asserts();

    @Test
    @DisplayName("Проверка покупки, если поле CVC не заполнено.")
    void NoCVC() {
        creditCardPage.withoutOutCVCFilling();
        assertTrue(assertInstance.isWrongFormatWarningShown());
    }
}