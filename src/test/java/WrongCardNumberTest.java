import com.codeborne.selenide.Condition;
import data.CreditCardPage;
import data.Initialisation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class WrongCardNumberTest {

    @BeforeAll
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private CreditCardPage creditCardPage;

    @Test
    @DisplayName("Проверка покупки, если поле Карта заполнено некорректно.")
    void WrongCardNumber() {
        creditCardPage = new CreditCardPage();
        creditCardPage.wrongCardFilling();
        $(withText("Неверный формат")).shouldBe(Condition.appear);
    }
}
