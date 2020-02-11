import data.Asserts;
import data.CardStatus;
import data.DebitCardPage;
import data.Initialisation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DebitValidCardTest {

    @BeforeAll
    public void setUp() {
        Initialisation.browserSettings();
    }

    private DebitCardPage debitCardPage = new DebitCardPage();
    private Asserts assertInstance = new Asserts();

    @Test
    @DisplayName("Проверка покупки с помощью дебетовой карты со статусом APPROVED")
    void first_successfulFormFilling() {
        debitCardPage.approvedPageFilling();
        assertTrue(assertInstance.isSuccessNotificationShown());
        assertTrue(assertInstance.isErrorNotificationNotShown());
    }

    @Test
    void second_dataBaseTest() throws SQLException {
        data.SQL.connection();
        assertNotNull(data.SQL.orderRow());
        assertNotNull(data.SQL.paymentRow());
        assertEquals(String.valueOf(CardStatus.APPROVED), String.valueOf(data.SQL.paymentStatus()), "Transaction status should be as");
        assertEquals(data.SQL.paymentTransactionId(), data.SQL.orderPaymentId(), "Transaction and Order IDs are not equal");
        assertEquals(45000, data.SQL.transactionAmount(), "Transaction amount should be as");
        data.SQL.connection().close();
    }
}