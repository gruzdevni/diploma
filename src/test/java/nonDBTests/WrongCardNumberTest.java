package nonDBTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WrongCardNumberTest {
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
    void WrongCardNumber() {
        open("http://localhost:8080");
        $("button:last-of-type").click();
        $("fieldset .input__control:first-of-type").setValue("44444444").sendKeys(Keys.chord(Keys.TAB));
        $(".input__control:focus").setValue("12").sendKeys(Keys.chord(Keys.TAB));
        $(".input__control:focus").setValue("20").sendKeys(Keys.chord(Keys.TAB));
        $(".input__control:focus").setValue("Viktor Yudin").sendKeys(Keys.chord(Keys.TAB));
        $(".input__control:focus").setValue("333");
        $(withText("Продолжить")).click();
        $(withText("Неверный формат")).shouldBe(Condition.appear);
    }
}
