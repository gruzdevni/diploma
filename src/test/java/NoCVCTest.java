import com.codeborne.selenide.Condition;
import data.CreditCardPage;
import data.Initialisation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;


public class NoCVCTest {

    @BeforeAll
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private CreditCardPage creditCardPage;

    @Test
    @DisplayName("Проверка покупки, если поле CVC не заполнено.")
    void NoCVC() {
        creditCardPage = new CreditCardPage();
        creditCardPage.withoutOutCVCFilling();
        $(withText("Неверный формат")).shouldBe(Condition.appear);
    }
}