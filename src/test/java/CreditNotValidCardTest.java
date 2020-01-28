import com.codeborne.selenide.Condition;
import data.CardStatus;
import data.CreditCardPage;
import data.Initialisation;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import data.Credit;
import data.Order;

import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreditNotValidCardTest {

    @BeforeAll
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private CreditCardPage creditCardPage;

    @Test
    @DisplayName("Проверка покупки в кредит по данным карты со статусом DECLINED")
    void first_successfulFormFilling() {
        creditCardPage = new CreditCardPage();
        creditCardPage.declinedPageFilling();
        $(withText("Ошибка")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void second_dataBaseTest() throws SQLException {

        val orderSQLQuery = "SELECT * FROM order_entity WHERE created IN (SELECT max(created) FROM order_entity);";
        val creditSQLQuery = "SELECT * FROM credit_request_entity WHERE created IN (SELECT max(created) FROM credit_request_entity);";
        val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(data.SQL.url, "app", "pass");
        )
        {
            val orderRow = runner.query(conn, orderSQLQuery, new BeanHandler<>(Order.class));
            val creditRow = runner.query(conn, creditSQLQuery, new BeanHandler<>(Credit.class));
            assertNotNull(orderRow);
            assertNotNull(creditRow);
            String creditStatus = creditRow.getStatus();
            String creditTransactionId = creditRow.getBank_id();
            String orderTransactionId = orderRow.getPayment_id();
            assertEquals(CardStatus.DECLINED, creditStatus, "data.Credit status should be as");
            assertEquals(creditTransactionId, orderTransactionId, "data.Credit and data.Order IDs are not equal");
        }
    }
}