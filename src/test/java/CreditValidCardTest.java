import data.CardStatus;
import data.CreditCardPage;
import data.Initialisation;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import data.Credit;
import data.Order;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreditValidCardTest {

    @BeforeAll
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private CreditCardPage creditCardPage;

    @Test
    void first_successfulFormFilling() {
        creditCardPage = new CreditCardPage();
        creditCardPage.approvedPageFilling();
    }

    @Test
    void second_dataBaseTest() throws SQLException {

        val orderSQLQuery = "SELECT * FROM order_entity WHERE created IN (SELECT max(created) FROM order_entity);";
        val creditSQLQuery = "SELECT * FROM credit_request_entity WHERE created IN (SELECT max(created) FROM credit_request_entity);";
        val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app?useUnicode=true&serverTimezone=UTC", "app", "pass"
                );
        ) {
            val orderRow = runner.query(conn, orderSQLQuery, new BeanHandler<>(Order.class));
            val creditRow = runner.query(conn, creditSQLQuery, new BeanHandler<>(Credit.class));
            assertNotNull(orderRow);
            assertNotNull(creditRow);
            String creditStatus = creditRow.getStatus();
            String creditTransactionId = creditRow.getBank_id();
            String orderTransactionId = orderRow.getPayment_id();
            assertEquals(CardStatus.APPROVED, creditStatus, "data.Credit status should be as");
            assertEquals(creditTransactionId, orderTransactionId, "data.Credit and data.Order IDs are not equal");
        }
    }
}
