import data.Asserts;
import data.CreditCardPage;
import data.Initialisation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WrongCardNumberTest {

    @BeforeAll
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private CreditCardPage creditCardPage = new CreditCardPage();
    private Asserts assertInstance = new Asserts();

    @Test
    @DisplayName("Проверка покупки, если поле Карта заполнено некорректно.")
    void ShouldNotificationShownIfWrongCardNumber() {
        creditCardPage.wrongCardFilling();
        assertTrue(assertInstance.isWrongFormatWarningShown());
    }
}
