import com.codeborne.selenide.Condition;
import data.CardStatus;
import data.DebitCardPage;
import data.Initialisation;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import data.Order;
import data.Payment;

import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DebitNotValidCardTest {

    @BeforeAll
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private DebitCardPage debitCardPage;

    @Test
    @DisplayName("Проверка покупки с помощью дебетовой карты со статусом DECLINED")
    void first_successfulFormFilling() {
        debitCardPage = new DebitCardPage();
        debitCardPage.declinedPageFilling();
        $(withText("Ошибка")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void second_dataBaseTest() throws SQLException {
        val orderSQLQuery = "SELECT * FROM order_entity WHERE created IN (SELECT max(created) FROM order_entity);";
        val paymentSQLQuery = "SELECT * FROM payment_entity WHERE created IN (SELECT max(created) FROM payment_entity);";
        val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(data.SQL.url, "app", "pass");
        ) {
            val orderRow = runner.query(conn, orderSQLQuery, new BeanHandler<>(Order.class));
            val paymentRow = runner.query(conn, paymentSQLQuery, new BeanHandler<>(Payment.class));
            assertNotNull(orderRow);
            assertNotNull(paymentRow);
            String paymentStatus = paymentRow.getStatus();
            int transactionAmount = paymentRow.getAmount();
            String paymentTransactionId = paymentRow.getTransaction_id();
            String orderTransactionId = orderRow.getPayment_id();
            assertEquals(CardStatus.DECLINED, paymentStatus, "Transaction status should be as");
            assertEquals(45000, transactionAmount, "Transaction amount should be as");
            assertEquals(paymentTransactionId, orderTransactionId, "data.Payment and data.Order IDs are not equal");
        }
    }
}
