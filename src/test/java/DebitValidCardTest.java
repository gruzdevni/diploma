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
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private DebitCardPage debitCardPage = new DebitCardPage();
    private Asserts assertInstance = new Asserts();

    @Test
    @DisplayName("Проверка фронтенда на покупку с помощью дебетовой карты со статусом APPROVED")
    void first_frontendTest() {
        debitCardPage.approvedPageFilling();
        assertTrue(assertInstance.isSuccessNotificationShown());
        assertTrue(assertInstance.isErrorNotificationNotShown());
    }

    @Test
    @DisplayName("Проверка базы данных на покупку с помощью дебетовой карты со статусом APPROVED")
    void second_dataBaseTest() throws SQLException {
        debitCardPage.approvedPageFilling();
        data.SQL.connection();
        assertNotNull(data.SQL.orderRow(), "Запись в таблицу заказов базы данных не произведена.");
        assertNotNull(data.SQL.paymentRow(), "Запись в таблицу оплат базы данных не произведена.");
        assertEquals(String.valueOf(CardStatus.APPROVED), String.valueOf(data.SQL.paymentStatus()), "Transaction status should be as");
        assertEquals(data.SQL.paymentTransactionId(), data.SQL.orderPaymentId(), "Transaction and Order IDs are not equal");
        assertEquals(45000, data.SQL.transactionAmount(), "Transaction amount should be as");
        data.SQL.connection().close();
    }
}