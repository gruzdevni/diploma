package data;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.open;

public class Initialisation {

    public static void browserSettings() {
        Configuration.headless = true;
        Configuration.timeout = Long.parseLong("4000");
        Configuration.startMaximized = true;
    }

    public void load() {
        open("http://localhost:8080");
    }
}
