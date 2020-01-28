package data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SQL {

    public static void dbAsserts(String orderSQLQuery, String paymentSQLQuery, QueryRunner runner, Connection conn) throws SQLException {
        val orderRow = runner.query(conn, latestOrderQuery(), new BeanHandler<>(Order.class));
        val paymentRow = runner.query(conn, latestPaymentQuery(), new BeanHandler<>(Payment.class));
        String paymentStatus = paymentRow.getStatus();
        int transactionAmount = paymentRow.getAmount();
        String paymentTransactionId = paymentRow.getTransaction_id();
        String orderTransactionId = orderRow.getPayment_id();
        assertNotNull(orderRow);
        assertNotNull(paymentRow);
        assertEquals("APPROVED", paymentStatus, "Transaction status should be as");
        assertEquals(45000, transactionAmount, "Transaction amount should be as");
        assertEquals(paymentTransactionId, orderTransactionId, "data.Payment and data.Order IDs are not equal");
    }

    public static String latestOrderQuery() {
        return "SELECT * FROM order_entity WHERE created IN (SELECT max(created) FROM order_entity);";
    }

    public static String latestPaymentQuery() {
        return "SELECT * FROM payment_entity WHERE created IN (SELECT max(created) FROM payment_entity);";
    }

    public static final String url = System.getProperty("url");

}

// "jdbc:mysql://localhost:3306/app?useUnicode=true&serverTimezone=UTC"
// "jdbc:postgresql://localhost:5432/app"