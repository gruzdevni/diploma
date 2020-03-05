import data.Asserts;
import data.CardStatus;
import data.DebitCardPage;
import data.Initialisation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DebitNotValidCardTest {

    @BeforeAll
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private DebitCardPage debitCardPage = new DebitCardPage();
    private Asserts assertInstance = new Asserts();

    @Test
    @DisplayName("Проверка фронтенда на покупку с помощью дебетовой карты со статусом DECLINED")
    void ShouldRejectPaymentWithDeclinedDebitCard() throws SQLException {
        debitCardPage.declinedPageFilling();
        assertTrue(assertInstance.isErrorNotificationShown());
    }

    @Test
    @DisplayName("Проверка базы данных на покупку с помощью дебетовой карты со статусом DECLINED")
    void ShouldDatabaseRecordCorrectForDeclinedDebitCard() throws SQLException {
        debitCardPage.declinedPageFilling();
        data.SQL.connection();
        assertNotNull(data.SQL.orderRow(), "No information is written to Data Base");
        assertNotNull(data.SQL.paymentRow(), "No information is written to Data Base");
        assertEquals(String.valueOf(CardStatus.DECLINED), String.valueOf(data.SQL.paymentStatus()), "Transaction status should be as");
        assertNull(data.SQL.transactionAmount(), "Transaction amount should as");
        data.SQL.connection().close();
        }
    }
