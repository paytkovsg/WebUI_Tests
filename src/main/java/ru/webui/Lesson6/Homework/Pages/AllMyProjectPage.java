package ru.webui.Lesson6.Homework.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.webui.Lesson6.Homework.Base.BaseView;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllMyProjectPage extends BaseView {

    @FindBy(xpath = "//a[@title='Создать проект']")
    private WebElement buttonCrProject;

    public AllMyProjectPage(WebDriver driver) {
        super(driver);
    }
    public NewProjectPage clickOnCreateProject(){
        buttonCrProject.click();
        return new NewProjectPage(driver);

    }
    public AllMyProjectPage checkNewProjectPopUp() {
        String message = wait10.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                "div[class='message']"))).getText();
        assertTrue(message.contains("Проект сохранен"));
        return this;
    }
}
