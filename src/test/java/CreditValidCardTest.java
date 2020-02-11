import data.Asserts;
import data.CardStatus;
import data.CreditCardPage;
import data.Initialisation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CreditValidCardTest {

    @BeforeAll
    public void setUp() {
        Initialisation.browserSettings();
    }

    private CreditCardPage creditCardPage = new CreditCardPage();
    private Asserts assertInstance = new Asserts();

    @Test
    @DisplayName("Проверка покупки в кредит по данным карты со статусом APPROVED")
    void first_successfulFormFilling() {
        creditCardPage.approvedPageFilling();
        assertTrue(assertInstance.isSuccessNotificationShown());
        assertTrue(assertInstance.isErrorNotificationNotShown());
    }

    @Test
    void second_dataBaseTest() throws SQLException {
        assertNotNull(data.SQL.orderRow());
        assertNotNull(data.SQL.creditRow());
        assertEquals(String.valueOf(CardStatus.APPROVED), String.valueOf(data.SQL.creditStatus()), "Credit status should be as");
        assertEquals(data.SQL.creditTransactionId(), data.SQL.orderCreditId(), "Credit and Order IDs are not equal");
        data.SQL.connection().close();
    }
}
