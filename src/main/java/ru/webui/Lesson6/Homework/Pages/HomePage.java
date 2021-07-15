package ru.webui.Lesson6.Homework.Pages;

import org.openqa.selenium.WebDriver;
import ru.webui.Lesson6.Homework.Base.BaseView;
import ru.webui.Lesson6.Homework.views.NavigationBar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage extends BaseView {

    private NavigationBar navigationBar;

    public HomePage(WebDriver driver) {
        super(driver);
        this.navigationBar = new NavigationBar(driver);
    }

    public void checkUrl(String url) {
        assertEquals(driver.getCurrentUrl(), url);
    }

    public NavigationBar getPageNavigation() {
        return this.navigationBar;
    }

}
