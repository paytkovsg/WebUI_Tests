package ru.webui.Lesson6.Homework.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseView {
    protected WebDriver driver;
    protected WebDriverWait wait10;
    protected WebDriverWait wait30;

    public BaseView(WebDriver driver) {
        this.driver = driver;
        this.wait10 = new WebDriverWait(driver, 10);
        this.wait30 = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }
}
