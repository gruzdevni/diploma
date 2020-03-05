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
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private CreditCardPage creditCardPage = new CreditCardPage();
    private Asserts assertInstance = new Asserts();

    @Test
    @DisplayName("Проверка фронтенда на покупку в кредит по данным карты со статусом APPROVED")
    void ShouldAcceptCreditWithApprovedCard() {
        creditCardPage.approvedPageFilling();
        assertTrue(assertInstance.isSuccessNotificationShown());
        assertTrue(assertInstance.isErrorNotificationNotShown());
    }

    @Test
    @DisplayName("Проверка базы данных на покупку в кредит по данным карты со статусом APPROVED")
    void ShouldDatabaseRecordCorrectForApprovedCreditCard() throws SQLException {
        creditCardPage.approvedPageFilling();
        data.SQL.connection();
        assertNotNull(data.SQL.orderRow());
        assertNotNull(data.SQL.creditRow());
        assertEquals(String.valueOf(CardStatus.APPROVED), String.valueOf(data.SQL.creditStatus()), "Credit status should be as");
        assertEquals(data.SQL.creditTransactionId(), data.SQL.orderCreditId(), "Credit and Order IDs are not equal");
        data.SQL.connection().close();
    }
}
