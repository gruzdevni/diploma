package data;

import com.codeborne.selenide.Configuration;

public class Initialisation {
    public static void browserSettings() {
        System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = Long.parseLong("4000");
        Configuration.startMaximized = true;
    }
}
