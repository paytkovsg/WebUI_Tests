package ru.webui.Lesson6.Homework.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.webui.Lesson6.Homework.Base.BaseView;
import ru.webui.Lesson6.Homework.views.NavigationBar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage extends BaseView {

    private NavigationBar navigationBar;

    public HomePage(WebDriver driver) {
        super(driver);
        this.navigationBar = new NavigationBar(driver);
    }

    @Step(value = "Пользователь перешел на страницу:  {url}")
    public HomePage checkUrl(String url) {
        wait10.until(ExpectedConditions.urlToBe(url));
        assertEquals(driver.getCurrentUrl(), url);
        return this;
    }

    public NavigationBar getPageNavigation() {
        return this.navigationBar;
    }

}
