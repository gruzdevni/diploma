package data;

import com.codeborne.selenide.Configuration;

public class Initialisation {
    public static void browserSettings() {
        Configuration.headless = false;
        Configuration.timeout = Long.parseLong("4000");
        Configuration.startMaximized = true;
    }
}
