package data;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.open;

public class Initialisation {
    public void browserSettings() {
        Configuration.headless = true;
        Configuration.timeout = Long.parseLong("4000");
        Configuration.startMaximized = true;
    }

    public void loadPage() {
        open("http://localhost:8080");
    }
}
