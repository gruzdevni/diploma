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
    public void setUp() {
        Initialisation.browserSettings();
    }

    private DebitCardPage debitCardPage = new DebitCardPage();
    private Asserts assertInstance = new Asserts();

    @Test
    @DisplayName("Проверка покупки с помощью дебетовой карты со статусом DECLINED")
    void second_dataBaseTest() throws SQLException {
        data.SQL.connection();
        debitCardPage.declinedPageFilling();
        assertTrue(assertInstance.isErrorNotificationShown());
        assertNotNull(data.SQL.orderRow());
        assertNotNull(data.SQL.paymentRow());
        assertEquals(String.valueOf(CardStatus.DECLINED), String.valueOf(data.SQL.paymentStatus()), "Transaction status should be as");
        assertNull(data.SQL.transactionAmount(), "Transaction amount should as");
        data.SQL.connection().close();
        }
    }
