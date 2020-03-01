package data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {

    public static String getUrl() {
        return System.getProperty("url");
    }

    public static String latestOrderQuery() {
        return "SELECT * FROM order_entity WHERE created IN (SELECT max(created) FROM order_entity);";
    }

    public static String latestPaymentQuery() {
        return "SELECT * FROM payment_entity WHERE created IN (SELECT max(created) FROM payment_entity);";
    }

    public static String latestCreditQuery() {
        return "SELECT * FROM credit_request_entity WHERE created IN (SELECT max(created) FROM credit_request_entity);";
    }

    public static Order orderRow() throws SQLException {
        val orderRow = runner().query(connection(), latestOrderQuery(), new BeanHandler<>(Order.class));
        return (Order) orderRow;
    }

    public static Payment paymentRow() throws SQLException {
        val paymentRow = runner().query(connection(), latestPaymentQuery(), new BeanHandler<>(Payment.class));
        return (Payment) paymentRow;
    }

    public static Credit creditRow() throws SQLException {
        val creditRow = runner().query(connection(), latestCreditQuery(), new BeanHandler<>(Credit.class));
        return (Credit) creditRow;
    }

    public static Connection connection() throws SQLException {
        val conn = DriverManager.getConnection("jdbc:postgresql://192.168.99.100:5432/app", "app", "pass");
        return conn;
    }

    public static QueryRunner runner() throws SQLException {
        val runner = new QueryRunner();
        return runner;
    }

    public static String creditStatus() throws SQLException {
        return creditRow().getStatus();
    }

    public static String creditTransactionId() throws SQLException {
        return creditRow().getBank_id();
    }

    public static String orderCreditId() throws SQLException {
        return orderRow().getCredit_id();
    }

    public static String orderPaymentId() throws SQLException {
        return orderRow().getPayment_id();
    }

    public static String paymentStatus() throws SQLException {
        return paymentRow().getStatus();
    }

    public static String paymentTransactionId() throws SQLException {
        return paymentRow().getTransaction_id();
    }

    public static int transactionAmount() throws SQLException {
        return paymentRow().getAmount();
    }
}

// "jdbc:mysql://192.168.99.100:3306/app?useUnicode=true&serverTimezone=UTC"
// "jdbc:postgresql://192.168.99.100:5432/app"