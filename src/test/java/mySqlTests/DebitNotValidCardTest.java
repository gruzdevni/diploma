package mySqlTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import postgreSqlTests.Order;
import postgreSqlTests.Payment;

import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DebitNotValidCardTest {

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = Long.parseLong("4000");
        Configuration.startMaximized = true;
    }

    @Test
    void first_successfulFormFilling() {
        open("http://localhost:8080");
        $("button:first-of-type").click();
        $("fieldset .input__control:first-of-type").setValue("4444444444444442").sendKeys(Keys.chord(Keys.TAB));
        $(".input__control:focus").setValue("12").sendKeys(Keys.chord(Keys.TAB));
        $(".input__control:focus").setValue("20").sendKeys(Keys.chord(Keys.TAB));
        $(".input__control:focus").setValue("Viktor Yudin").sendKeys(Keys.chord(Keys.TAB));
        $(".input__control:focus").setValue("333");
        $(withText("Продолжить")).click();
        $(withText("Ошибка")).waitUntil(Condition.visible, 15000);
        $(withText("Успешно")).shouldNotBe(Condition.appear);
    }

    @Test
    void second_dataBaseTest() throws SQLException {
        val orderSQLQuery = "SELECT * FROM order_entity WHERE created IN (SELECT max(created) FROM order_entity);";
        val paymentSQLQuery = "SELECT * FROM payment_entity WHERE created IN (SELECT max(created) FROM payment_entity);";
        val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app?useUnicode=true&serverTimezone=UTC", "app", "pass"
                );
        ) {
            val orderRow = runner.query(conn, orderSQLQuery, new BeanHandler<>(Order.class));
            val paymentRow = runner.query(conn, paymentSQLQuery, new BeanHandler<>(Payment.class));
            String paymentStatus = paymentRow.getStatus();
            int transactionAmount = paymentRow.getAmount();
            String paymentTransactionId = paymentRow.getTransaction_id();
            String orderTransactionId = orderRow.getPayment_id();
            assertNotNull(orderRow);
            assertNotNull(paymentRow);
            assertEquals("DECLINED", paymentStatus, "Transaction status should be as");
            assertEquals(45000, transactionAmount, "Transaction amount should be as");
            assertEquals(paymentTransactionId, orderTransactionId, "Payment and Order IDs are not equal");
        }
    }
}
