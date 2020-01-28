import com.codeborne.selenide.Condition;
import data.DebitCardPage;
import data.Initialisation;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class DebitValidCardTest {

    @BeforeAll
    public static void setUp() {
        Initialisation.browserSettings();
    }

    private DebitCardPage debitCardPage;

    @Test
    @DisplayName("Проверка покупки с помощью дебетовой карты со статусом APPROVED")
    void first_successfulFormFilling() {
        debitCardPage = new DebitCardPage();
        debitCardPage.approvedPageFilling();
        $(withText("Успешно")).waitUntil(Condition.visible, 15000);
        $(withText("Ошибка")).shouldNotBe(Condition.appear);
    }

    @Test
    void second_dataBaseTest() throws SQLException {
        data.SQL.latestOrderQuery();
        data.SQL.latestPaymentQuery();
        val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(data.SQL.url, "app", "pass");
        ) {
            data.SQL.dbAsserts(data.SQL.latestOrderQuery(), data.SQL.latestPaymentQuery(), runner, conn);
        }
    }
}