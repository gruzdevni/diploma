import data.Asserts;
import data.CardStatus;
import data.CreditCardPage;
import data.Initialisation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CreditNotValidCardTest {

    @BeforeAll
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private CreditCardPage creditCardPage = new CreditCardPage();
    private Asserts assertInstance = new Asserts();

    @Test
    @DisplayName("Проверка фронтенда на покупку в кредит по данным карты со статусом DECLINED")
    void first_frontendTest() {
        creditCardPage.declinedPageFilling();
        assertTrue(assertInstance.isErrorNotificationShown());
    }

    @Test
    @DisplayName("Проверка базы данных на покупку в кредит по данным карты со статусом DECLINED")
    void second_dataBaseTest() throws SQLException {
        creditCardPage.declinedPageFilling();
        data.SQL.connection();
        assertNotNull(data.SQL.orderRow());
        assertNotNull(data.SQL.creditRow());
        assertEquals(String.valueOf(CardStatus.DECLINED), String.valueOf(data.SQL.creditStatus()), "Credit status should be as");
        assertEquals(data.SQL.creditTransactionId(), data.SQL.orderCreditId(), "Credit and Order IDs are not equal");
        data.SQL.connection().close();
    }
}